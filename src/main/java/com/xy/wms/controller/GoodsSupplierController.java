package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.service.GoodsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goodssupplier")
public class GoodsSupplierController extends BaseController {

    @Autowired
    private GoodsSupplierService goodsSupplierService;


    @RequestMapping("/queryAllSupplierByGoodsId")
    @ResponseBody
    public List<Map<String,Object>>  queryAllSupplierByGoodsId(Integer goodsId){
        List<Map<String,Object>> list = goodsSupplierService.queryAllSupplierByGoodsId(goodsId);
        return list;
    }


}
