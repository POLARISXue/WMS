package com.xy.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.wms.base.BaseService;
import com.xy.wms.dao.WarehouseMapper;
import com.xy.wms.query.WarehouseQuery;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.Warehouse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseService extends BaseService<Warehouse,Integer> {
    @Resource
    private WarehouseMapper warehouseMapper;

    /**
     * 多条件分页查询物品信息
     */
    public Map<String,Object> queryWarehouseByParams(WarehouseQuery warehouseQuery){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(warehouseQuery.getPage(),warehouseQuery.getLimit());
        PageInfo<Warehouse> pageInfo = new PageInfo<>(warehouseMapper.selectByParams(warehouseQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加
     * @param warehouse
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveWarehouse(Warehouse warehouse){
        checkWarehouseParams(warehouse.getGoodsName(),warehouse.getTypeName());
        Warehouse temp =warehouseMapper.queryGoodsByName(warehouseMapper.newQuerygoodsByName(warehouse.getGoodsId()));



        if (temp==null){
            warehouse.setIsValid(1);
            warehouse.setCreateDate(new Date());
            warehouse.setUpdateDate(new Date());
            AssertUtil.isTrue(warehouseMapper.insertSelective(warehouse)<1,"添加货物失败！");

        }else {
            temp.setGoodsNumber(temp.getGoodsNumber()+warehouse.getGoodsNumber());
            warehouseMapper.updateByPrimaryKeySelective(temp);
//            updateWarehouse(warehouse);
        }
}

    private void checkWarehouseParams(String goodsName, String typeName) {
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateWarehouse(Warehouse warehouse){
        /**
         * 更新
         */
        Warehouse num = selectByPrimaryKey(warehouse.getId());
        AssertUtil.isTrue(null == num,"待更新记录不存在！");
        checkWarehouseParams(warehouse.getGoodsName(),warehouse.getTypeName());
        warehouse.setUpdateDate(new Date());
        if (StringUtils.isBlank(num.getGoodsName())&&StringUtils.isNotBlank(warehouse.getGoodsName())){
            warehouse.setChooseTime(new Date());
        }else{
            warehouse.setChooseTime(null);
        }
        AssertUtil.isTrue(updateByPrimaryKeySelective(warehouse)<1,"库存更新失败");
    }
    /**
     * 查询所有的货物名称
     */
    public List<Map<String,Object>> queryAllGoods(){
        return warehouseMapper.queryAllGoods();
    }

    /**
     * 删除
     */
    public void deleteWarehouse(Integer[] ids){
        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除记录！");
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"记录删除失败");
    }
}