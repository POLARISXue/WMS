package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index(){ return "index"; }

    @RequestMapping("/welcome")
    public String welcome(){ return "welcome";}

    @RequestMapping("/main")
    public String main(){ return "main"; }

}
