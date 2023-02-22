package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dataReport")
public class DataReportController extends BaseController {



    @RequestMapping("/index")
    public String index(){
        return "datareport/index";
    }

}
