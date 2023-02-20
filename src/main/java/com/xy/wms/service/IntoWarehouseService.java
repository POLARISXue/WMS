package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.GoodsMapper;
import com.xy.wms.dao.IntoWarehouseMapper;
import com.xy.wms.dao.WarehouseMapper;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.IntoWarehouse;
import com.xy.wms.vo.Warehouse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class IntoWarehouseService extends BaseService<IntoWarehouse,Integer> {

    @Resource
    private IntoWarehouseMapper intoWarehouseMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private WarehouseMapper warehouseMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    public void add(IntoWarehouse intoWarehouse) {
        checkIntoWarehouseParams(intoWarehouse);

        //判断入库状态，如果已入库成功，添加数据到库存表
        if (intoWarehouse.getState()==1){
            Warehouse wareHouse = warehouseMapper.selectByGoodsId(intoWarehouse.getGoodsId());
            if (wareHouse==null){
                Warehouse temp = new Warehouse();
                temp.setGoodsId(intoWarehouse.getGoodsId());
                temp.setGoodsNumber(intoWarehouse.getGoodsNumber());
                temp.setCreateDate(new Date());
                temp.setUpdateDate(new Date());
                temp.setIsValid(1);
                warehouseMapper.insertSelective(temp);
            }else {
                int sum = wareHouse.getGoodsNumber()+intoWarehouse.getGoodsNumber();
                warehouseMapper.updateByGoodsId(intoWarehouse.getGoodsId(),sum);
            }
        }

        //根据物品id查询物品单价，计算总成本
        intoWarehouse.setTotalPrice(goodsMapper.selectByPrimaryKey(intoWarehouse.getGoodsId()).getGoodsCostPrice()*(intoWarehouse.getGoodsNumber()));
        intoWarehouse.setIsValid(1);
        intoWarehouse.setCreateDate(new Date());
        intoWarehouse.setUpdateDate(new Date());
        intoWarehouseMapper.insertSelective(intoWarehouse);

    }

    private void checkIntoWarehouseParams(IntoWarehouse intoWarehouse) {
        AssertUtil.isTrue(intoWarehouse.getGoodsId()==null,"物品名称不能为空");
        AssertUtil.isTrue(intoWarehouse.getSupplierId()==null,"供应商不能为空");
        AssertUtil.isTrue(intoWarehouse.getGoodsNumber()==null || intoWarehouse.getGoodsNumber()<1,"物品数量异常");
        AssertUtil.isTrue(intoWarehouse.getState()==null,"采购状态不能为空");

    }
}
