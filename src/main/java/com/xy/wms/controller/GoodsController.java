package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/queryAllGoods")
    @ResponseBody
    public List<Map<String,Object>> queryAllGoods(){
        return goodsService.queryAllGoods();
    }

}
