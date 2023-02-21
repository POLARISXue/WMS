package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.CustomersMapper;
import com.xy.wms.dao.GoodsMapper;
import com.xy.wms.dao.OutWarehouseMapper;
import com.xy.wms.dao.WarehouseMapper;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OutWarehouseService extends BaseService<OutWarehouse,Integer> {

    @Resource
    private OutWarehouseMapper outWarehouseMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private CustomersMapper customersMapper;
    @Resource
    private WarehouseMapper warehouseMapper;


    /**
     * 添加出库记录
     * @param outWarehouse
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOutWarehouse(OutWarehouse outWarehouse){
        Goods goods = goodsMapper.selectByPrimaryKey(outWarehouse.getGoodsId());
        Customers customers = customersMapper.selectByPrimaryKey(outWarehouse.getCustomersId());
        AssertUtil.isTrue(null == goods,"为查询到对应的物品数据");
        AssertUtil.isTrue(null==customers,"为查询到对应的需求商数据");
        //1.参数校验
        checkAddParams(
                 goods.getGoodsName()
                ,customers.getName()
                ,outWarehouse.getGoodsNumber()
                ,outWarehouse.getGoodsId());

        //判断出库状态 ：
        if(outWarehouse.getOutWarehouseState()==1){
            updateWarehouse(outWarehouse);
        }


        // 2.设置相关参数默认值
        outWarehouse.setIsValid(1);
        outWarehouse.setSaleTotalPrice(goods.getGoodsSalePrice()*outWarehouse.getGoodsNumber());
        outWarehouse.setUpdateDate(new Date());
        outWarehouse.setCreateDate(new Date());
        //3.执⾏添加 判断结果
        AssertUtil.isTrue(outWarehouseMapper.insertSelective(outWarehouse)!=1,"出库记录添加失败！");
    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOutWarehouse(OutWarehouse outWarehouse){

        //校验基础参数
        Goods goods = goodsMapper.selectByPrimaryKey(outWarehouse.getGoodsId());
        Customers customers = customersMapper.selectByPrimaryKey(outWarehouse.getCustomersId());
        AssertUtil.isTrue(null == goods,"为查询到对应的物品数据");
        AssertUtil.isTrue(null==customers,"为查询到对应的需求商数据");
        //1.参数校验
        checkUpdateParams(outWarehouse.getId()
                ,goods.getGoodsName()
                ,customers.getName()
                ,outWarehouse.getGoodsNumber()
                ,outWarehouse.getGoodsId());
        //2.设置相关参数值
        outWarehouse.setUpdateDate(new Date());
        //执行更新操作，判断受影响行数，并判断出库状态是否从未出库修改成已出库，若修改，则对库存数量减去相应数量
        if(outWarehouseMapper.selectByPrimaryKey(outWarehouse.getId()).getOutWarehouseState()==0 &&outWarehouse.getOutWarehouseState()==1){
            updateWarehouse(outWarehouse);
        }
        AssertUtil.isTrue(outWarehouseMapper.updateByPrimaryKeySelective(outWarehouse)!=1,"出库记录更新失败！");

    }



    //    基本参数校验
    private void checkAddParams(String goodsName, String name, Integer goodsNumber,Integer id) {
        AssertUtil.isTrue(StringUtils.isBlank(goodsName),"未检测到物品名称！");
        AssertUtil.isTrue(StringUtils.isBlank(name),"未检测到需求商名称！");
        AssertUtil.isTrue(goodsNumber<=0,"出库数量不合法！");
        Integer warehouseNumber = warehouseMapper.selectByGoodsId(id).getGoodsNumber();
        AssertUtil.isTrue( warehouseNumber==0 || warehouseNumber==null || goodsNumber>warehouseNumber,"库存数量不足，无法出库，请重试！");
    }

    //    基本参数校验
    private void checkUpdateParams(Integer outWarehouseId,String goodsName, String name, Integer goodsNumber,Integer id) {
        //1.参数校验
        // 通过id查询记录
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null==outWarehouseId,"未获取到待更新记录Id！");
        //判断是否为空
        AssertUtil.isTrue(null==outWarehouseMapper.selectByPrimaryKey(outWarehouseId),"待更新记录不存在！");

        AssertUtil.isTrue(StringUtils.isBlank(goodsName),"未检测到物品名称！");
        AssertUtil.isTrue(StringUtils.isBlank(name),"未检测到需求商名称！");
        AssertUtil.isTrue(goodsNumber<=0,"出库数量不合法！");
        Warehouse old = warehouseMapper.selectByGoodsId(id);
        AssertUtil.isTrue(old==null,"未查询到库存");
        Integer warehouseNumber = old.getGoodsNumber();
        AssertUtil.isTrue( warehouseNumber==0 || warehouseNumber==null || goodsNumber>warehouseNumber,"当前库存数量已不足，无法出库，请重试！");
    }


    private void updateWarehouse(OutWarehouse outWarehouse){
        Warehouse wareHouse = warehouseMapper.selectByGoodsId(outWarehouse.getGoodsId());
        AssertUtil.isTrue(wareHouse==null,"未查询到对应物品的库存");
        int sum = wareHouse.getGoodsNumber()-outWarehouse.getGoodsNumber();
        AssertUtil.isTrue( warehouseMapper.updateByGoodsId(outWarehouse.getGoodsId(),sum)!=1,"修改库存失败");

    }

}
