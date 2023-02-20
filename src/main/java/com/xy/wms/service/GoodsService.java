package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.GoodsMapper;
import com.xy.wms.vo.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService extends BaseService<Goods,Integer> {

    @Resource
    private GoodsMapper goodsMapper;

    public List<Map<String,Object>> queryAllGoods(){
        return goodsMapper.queryAllGoods();
    }

}
