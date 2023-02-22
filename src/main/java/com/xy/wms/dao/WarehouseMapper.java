package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.report.InventoryOverviewQuery;
import com.xy.wms.vo.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarehouseMapper extends BaseMapper<Warehouse,Integer> {

    List<Map<String,Object>> queryAllGoods();

    Warehouse queryGoodsByName(String goodsName);

    String newQuerygoodsByName(Integer goodsId);

    Warehouse selectByGoodsId(Integer goodsId);

    Integer updateByGoodsId(@Param("goodsId") Integer goodsId,@Param("sum") Integer sum);

    List<InventoryOverviewQuery> inventoryOverview();


}