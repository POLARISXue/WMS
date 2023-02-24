package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.GoodsSupplierMapper;
import com.xy.wms.vo.GoodsSupplier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GoodsSupplierService extends BaseService<GoodsSupplier,Integer> {

    @Resource
    private GoodsSupplierMapper goodsSupplierMapper;

    public List<Map<String,Object>> queryAllSupplierByGoodsId(Integer goodsId){
        return goodsSupplierMapper.queryAllSupplierByGoodsId(goodsId);
    }

    public List<Map<String, Object>> queryAllGoodsSupplier(Integer supplierId) {
        return goodsSupplierMapper.queryAllGoodsSupplier(supplierId);
    }
}
