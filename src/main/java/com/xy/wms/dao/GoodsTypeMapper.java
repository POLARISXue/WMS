package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.GoodsType;

import java.util.List;
import java.util.Map;

public interface GoodsTypeMapper extends BaseMapper<GoodsType,Integer> {

    List<Map<String,Object>> queryAllGoodsType();
    GoodsType selectByTypeName(String typeName);
}