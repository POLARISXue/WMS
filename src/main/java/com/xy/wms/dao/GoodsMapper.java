package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends BaseMapper<Goods,Integer> {

    List<Map<String,Object>> queryAllGoods();

    Goods selectByGoodsName(String goodsName);
}