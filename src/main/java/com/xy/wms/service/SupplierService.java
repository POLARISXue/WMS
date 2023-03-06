package com.xy.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.wms.base.BaseService;
import com.xy.wms.dao.GoodsSupplierMapper;
import com.xy.wms.dao.SupplierMapper;
import com.xy.wms.query.SupplierQuery;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.utils.PhoneUtil;
import com.xy.wms.vo.GoodsSupplier;
import com.xy.wms.vo.Supplier;
import com.xy.wms.vo.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SupplierService extends BaseService<Supplier,Integer> {
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private GoodsSupplierMapper goodsSupplierMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;



//    /**
//     * 分页查询
//     * @param supplierQuery
//     * @return
//     */
//    public Map<String, Object> selectByParams(SupplierQuery supplierQuery) {
//        Map<String,Object> map = new HashMap<>();
//        //开启分页
//        PageHelper.startPage(supplierQuery.getPage(),supplierQuery.getLimit());
//        //得到对应的分页对象
//        PageInfo<Supplier> pageInfo = new PageInfo<>(supplierMapper.selectByParams(supplierQuery));
//        //设置map对象
//        map.put("code",0);
//        map.put("msg","success");
//        map.put("count",pageInfo.getTotal());
//        //设置分页的列表
//        map.put("data",pageInfo.getList());
//        return map;
//    }
    /**
     * 添加供应商
     * @param supplier
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addSupplier(Supplier supplier){
        //参数校验
        checkSupplierParams(supplier.getName(),supplier.getContact(),supplier.getLinkPhone(),null);
        //设置相关参数的默认值
        //isValid是否有效  （0=无效，1=有效）
        supplier.setIsValid(1);
        supplier.setCreateDate(new Date());
        supplier.setUpdateDate(new Date());

        //执行添加操作，判断受影响的行数
        AssertUtil.isTrue(supplierMapper.insertSelective(supplier) != 1,"添加供应商失败！");

        relationUserRole(supplier.getId(),supplier.getGoodsIds());
        redisTemplate.delete(redisTemplate.keys("supplier:list*"));
    }

    /**
     * 更新供应商
     * @param supplier
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSupplier(Supplier supplier){
        // 判断用户ID是否为空，且数据存在
        AssertUtil.isTrue(null == supplier.getId(), "待更新记录不存在！");
        // 通过id查询数据
        Supplier temp = supplierMapper.selectByPrimaryKey(supplier.getId());
        // 判断是否存在
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");
        // 参数校验
        checkSupplierParams(supplier.getName(),supplier.getContact(),supplier.getLinkPhone(),supplier.getId());

        // 设置默认值
        supplier.setUpdateDate(new Date());

        // 执行更新操作，判断受影响的行数
        AssertUtil.isTrue(supplierMapper.updateByPrimaryKeySelective(supplier) != 1, "供应商更新失败！");

        relationUserRole(supplier.getId(),supplier.getGoodsIds());
        redisTemplate.delete(redisTemplate.keys("supplier:list*"));
    }


    private void relationUserRole(Integer supplierId, String goodsIds) {
        //通过用户ID查询角色记录
        Integer count = goodsSupplierMapper.countGoodsSupplierBySupplierId(supplierId);
        //判断角色记录是否存在
        if (count > 0){
            //如果角色记录存在，则删除该用户对应的角色记录
            AssertUtil.isTrue(goodsSupplierMapper.deleteGoodsSupplierBySupplierId(supplierId) != count,"用户角色分配失败！");
        }
        //判断角色ID是否存在，如果存在，则添加该用户对应的角色记录
        if (StringUtils.isNotBlank(goodsIds)){
            //将用户角色数据设置到集合中，执行批量添加
            List<GoodsSupplier> goodsSupplierList = new ArrayList<>();
            //将角色ID字符串转换成数组
            String[] goodsIdsArray = goodsIds.split(",");
            //遍历数组，得到对应的用户角色对象，并设置到集合中
            for (String goodsId:goodsIdsArray) {
                GoodsSupplier goodsSupplier = new GoodsSupplier();
                goodsSupplier.setGoodsId(Integer.parseInt(goodsId));
                goodsSupplier.setSupplierId(supplierId);
                goodsSupplier.setCreateDate(new Date());
                goodsSupplier.setUpdateDate(new Date());
                //设置到集合中
                goodsSupplierList.add(goodsSupplier);
            }
            //批量添加物品供应商记录
            AssertUtil.isTrue(goodsSupplierMapper.insertBatch(goodsSupplierList) != goodsSupplierList.size(),"供应物品分配失败！");
        }
    }
    /**
     * 删除供应商
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSupplier(Integer[] ids){
        //判断ID是否为空，长度是否大于0
        AssertUtil.isTrue(null == ids || ids.length < 1,"待删除记录不存在！");
        //执行删除操作，判断受影响的行数
        AssertUtil.isTrue(supplierMapper.deleteBatch(ids) != ids.length,"供应商删除失败！");

        for(Integer supplierId:ids){
            Integer count = goodsSupplierMapper.countGoodsSupplierBySupplierId(supplierId);
            if (count>0){
                AssertUtil.isTrue(goodsSupplierMapper.deleteGoodsSupplierBySupplierId(supplierId)!=count,"删除供应商数据失败");
            }
        }
        redisTemplate.delete(redisTemplate.keys("supplier:list*"));
    }
    /**
     * 参数校验
     * @param name
     * @param contact
     * @param linkPhone
     */
    private void checkSupplierParams(String name, String contact,String linkPhone,Integer supplierId) {
        //供应商名称是否为空
        AssertUtil.isTrue(StringUtils.isBlank(name),"供应商不能为空！");
        //非空且唯一
        Supplier temp = supplierMapper.querySupplierByName(name);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(supplierId)),"供应商已存在，请重新输入！");
        //联系人
        AssertUtil.isTrue(StringUtils.isBlank(contact),"联系人不能为空！");
        //联系号码
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"联系号码不能为空！");
        //号码格式
        AssertUtil.isTrue(!PhoneUtil.isMobile(linkPhone),"手机号码格式不正确");
    }

}
