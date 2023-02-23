package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.dao.WarehouseMapper;
import com.xy.wms.service.DataReportService;
import com.xy.wms.service.GoodsTypeService;
import com.xy.wms.service.OutWarehouseService;
import com.xy.wms.service.WarehouseService;
import com.xy.wms.vo.report.RadarChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/dataReport")
public class DataReportController extends BaseController {

    @Autowired
    private DataReportService dataReportService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private OutWarehouseService outWarehouseService;

    @Autowired
    private GoodsTypeService goodsTypeService;


    @RequestMapping("/index")
    public String index(){
        return "datareport/index";
    }

    @RequestMapping("/inventoryOverview")
    @ResponseBody
    public ResultInfo inventoryOverview(){
        return dataReportService.inventoryOverview();
    }

    @RequestMapping("/dailySalesExpenses")
    @ResponseBody
    public ResultInfo dailySalesExpenses(){
        return dataReportService.dailySalesExpenses();
    }

    @RequestMapping("/queryInventory")
    @ResponseBody
    public ResultInfo queryInventory(){
       return warehouseService.queryInventory();
    }

    @RequestMapping("/loadRadarChart")
    @ResponseBody
    public ResultInfo loadRadarChart(){
        ResultInfo resultInfo = new ResultInfo();
        Map<String,List> map = new HashMap<>();
        List<Integer> warehouseList = warehouseService.loadRadarChart();
        map.put("wareHouseList",warehouseList);
        List<Integer> outWarehouseList = outWarehouseService.loadRadarChart();
        map.put("outWarehouseList",outWarehouseList);
        List<Map<String,Integer>> typeNameList = goodsTypeService.loadRadarChart();
        map.put("typeNameList",typeNameList);
        resultInfo.setResult(map);
        return resultInfo;
    }

}
