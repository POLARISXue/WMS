package com.xy.wms.controller;

import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.OutWarehouseQuery;
import com.xy.wms.service.OutWarehouseService;
import com.xy.wms.vo.IntoWarehouse;
import com.xy.wms.vo.OutWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/outwarehouse")
public class OutWarehouseController extends BaseController {

    @Autowired
    private OutWarehouseService outWarehouseService;
    @RequiredPermission(code = "3010")
    @RequestMapping("/index")
    public String index(){
        return "outwarehouse/outwarehouse";
    }

    // 多条件分页查询出库列表
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(OutWarehouseQuery query){

        return outWarehouseService.queryByParamsForTable(query);
    }

    //添加出库记录
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addOutWarehouse(OutWarehouse outWarehouse){
        outWarehouseService.addOutWarehouse(outWarehouse);
        return success("出库记录添加成功！");
    }

    //更新出库记录
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateOutWarehouse(OutWarehouse outWarehouse){
        outWarehouseService.updateOutWarehouse(outWarehouse);
        return success("出库记录更新成功！");
    }

    @RequestMapping("/addOrUpdateOutWarehousePage")
    public String addOrUpdateOutWarehousePage(Integer id, HttpServletRequest request){
        if (id != null){
            OutWarehouse outWarehouse = outWarehouseService.selectByPrimaryKey(id);
            request.setAttribute("outWarehouse",outWarehouse);
            return "outwarehouse/update";
        }
        return "outwarehouse/add";
    }

    //删除出库记录
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteOutWarehouse(Integer[] ids){
        outWarehouseService.deleteBatch(ids);
        return success("出库记录删除成功！");
    }

}
