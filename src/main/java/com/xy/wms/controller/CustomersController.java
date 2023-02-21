package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customers")
public class CustomersController extends BaseController {

    @Autowired
    private CustomersService customersService;

    @RequestMapping("/queryAllCustomers")
    @ResponseBody
    public List<Map<String,Object>> queryAllCustomers(){
        return customersService.queryAllCustomers();
    }
}
