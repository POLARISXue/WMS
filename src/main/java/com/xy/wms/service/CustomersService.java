package com.xy.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.wms.base.BaseService;
import com.xy.wms.dao.CustomersMapper;
import com.xy.wms.query.CustomersQuery;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.utils.PhoneUtil;
import com.xy.wms.vo.Customers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomersService extends BaseService<Customers,Integer> {

    @Resource
    private CustomersMapper customersMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;



    public List<Map<String, Object>> queryAllCustomers() {
        return customersMapper.queryAllCustomers();
    }

    /**
     * 分页查询
     * @param customersQuery
     * @return
     */
    public Map<String, Object> selectByParams(CustomersQuery customersQuery) {
        //获取redis缓存key
        StringBuffer keyBuff = new StringBuffer("customers:list");
        keyBuff.append(customersQuery.getRedisKey());
        String key = keyBuff.toString();
        //依据key找值
        PageInfo<Customers> pageInfo = null;
        if (redisTemplate.hasKey(key)){
            pageInfo = (PageInfo<Customers>) valueOperations.get(key);
        }else {
            //开启分页
            PageHelper.startPage(customersQuery.getPage(),customersQuery.getLimit());
            //得到对应的分页对象
            pageInfo = new PageInfo<>(customersMapper.selectByParams(customersQuery));
            //如果数据存在，将结果存入缓存
            if (pageInfo.getTotal()>0){
                valueOperations.set(key,pageInfo);
            }
        }

        Map<String,Object> map = new HashMap<>();
        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页的列表
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加需求商
     * @param customers
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomers(Customers customers){
        //参数校验
        checkCustomersParams(customers.getName(),customers.getLinkPhone(),null);
        //设置相关参数的默认值
        //isValid是否有效  （0=无效，1=有效）
        customers.setIsValid(1);
        customers.setCreateDate(new Date());
        customers.setUpdateDate(new Date());

        //执行添加操作，判断受影响的行数
        AssertUtil.isTrue(customersMapper.insertSelective(customers) != 1,"添加需求商失败！");

        redisTemplate.delete(redisTemplate.keys("customers:list*"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomers(Customers customers){
        //参数校验
        AssertUtil.isTrue(null == customers.getId(),"待更新记录不存在！");
        //
        Customers temp = customersMapper.selectByPrimaryKey(customers.getId());
        //判断数据库中数据记录
        AssertUtil.isTrue(temp == null,"待更新记录不存在！");
        checkCustomersParams(customers.getName(),customers.getLinkPhone(),customers.getId());
        customers.setUpdateDate(new Date());

        //执行更新数据
        AssertUtil.isTrue(customersMapper.updateByPrimaryKeySelective(customers) != 1,"需求商更新失败！");
        redisTemplate.delete(redisTemplate.keys("customers:list*"));
    }
    /**
     * 删除需求商
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomers(Integer[] ids){
        //判断ID是否为空
        AssertUtil.isTrue(null == ids || ids.length < 1,"待删除记录不存在！");
        //执行删除（更新）操作，判断受影响的行数
        AssertUtil.isTrue(customersMapper.deleteBatch(ids) != ids.length,"需求商数据删除失败！");
        redisTemplate.delete(redisTemplate.keys("customers:list*"));
    }
    /**
     * 参数校验
     * @param name
     * @param linkPhone
     */
    private void checkCustomersParams(String name, String linkPhone,Integer customersId) {
        //供应商名称
        AssertUtil.isTrue(StringUtils.isBlank(name),"需求商不能为空！");
        Customers temp = customersMapper.queryCustomersByName(name);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(customersId)),"需求商已存在，请重新输入！");
        //联系号码
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"联系号码不能为空！");
        //号码格式
        AssertUtil.isTrue(!PhoneUtil.isMobile(linkPhone),"手机号码格式不正确");
    }
}
