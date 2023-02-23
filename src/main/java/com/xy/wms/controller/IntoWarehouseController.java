package com.xy.wms.controller;

import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.IntoWarehouseQuery;
import com.xy.wms.service.IntoWarehouseService;
import com.xy.wms.vo.IntoWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/intowarehouse")
public class IntoWarehouseController extends BaseController {

    @Autowired
    private IntoWarehouseService intoWarehouseService;
    @RequiredPermission(code = "1010")
    @RequestMapping("/index")
    public String index(){
        return "intowarehouse/intowarehouse";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> queryIntoWarehouseListByParams(IntoWarehouseQuery intoWarehouseQuery){
        return intoWarehouseService.queryByParamsForTable(intoWarehouseQuery);
    }

    @RequestMapping("/addOrUpdateIntoWarehousePage")
    public String addOrUpdateIntoWarehousePage(Integer id, HttpServletRequest request){
        if (id != null){
           IntoWarehouse intoWarehouse = intoWarehouseService.selectByPrimaryKey(id);
            request.setAttribute("intoWarehouse",intoWarehouse);
            return "intowarehouse/update";
        }
        return "intowarehouse/add_update";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResultInfo add(IntoWarehouse intoWarehouse){
        intoWarehouseService.add(intoWarehouse);
        return success("入库记录添加成功");
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultInfo update(IntoWarehouse intoWarehouse){
        intoWarehouseService.update(intoWarehouse);
        return success("入库记录修改成功");
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        intoWarehouseService.deleteBatch(ids);
        return success("入库记录删除成功");
    }

}
