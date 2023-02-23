package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.service.DataReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/dataReport")
public class DataReportController extends BaseController {

    @Autowired
    private DataReportService dataReportService;


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

}
