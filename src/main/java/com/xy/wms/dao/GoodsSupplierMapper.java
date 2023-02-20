package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.GoodsSupplier;

import java.util.List;
import java.util.Map;

public interface GoodsSupplierMapper extends BaseMapper<GoodsSupplier,Integer> {

    List<Map<String,Object>> queryAllSupplierByGoodsId(Integer goodsId);

}