package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.WarehouseQuery;
import com.xy.wms.service.WarehouseService;
import com.xy.wms.vo.Warehouse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("warehouse")
public class WarehouseController extends BaseController {
    @Resource
    private WarehouseService warehouseService;
    /**
     * 多条件查询库存信息
     */
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> queryWarehouseByParams(WarehouseQuery warehouseQuery){
        return warehouseService.queryWarehouseByParams(warehouseQuery);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo saveWarehouse( Warehouse warehouse){
        warehouseService.saveWarehouse(warehouse);
        return success("货物添加成功");
    }
    /** 进入添加/修改库存管理页面
     * @param
     * @param
     * @return
     */
    @RequestMapping("toWarehousePage")
    public String toWarehousePage(Integer warehouseId, HttpServletRequest request) {
        // 判断saleChanceId是否为空
        if (warehouseId != null) {
            // 通过ID查询营销机会数据
            Warehouse warehouse = warehouseService.selectByPrimaryKey(warehouseId);
            // 将数据设置到请求域中
            request.setAttribute("warehouse",warehouse);
        }
        return "warehouse/add_update";
    }
    /**
    查询所有货物
     */
    @RequestMapping("/queryAllGoods")
    @ResponseBody
    public List<Map<String,Object>> queryAllGoods(){
        return warehouseService.queryAllGoods();
    }

    /**
     *进入库存管理界面
     */
    @RequestMapping("index")
    public String index(){
        return "warehouse/warehouse";
    }

    /**
     * 更新
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateWarehouse(Warehouse warehouse){
        warehouseService.updateWarehouse(warehouse);
        return success("货物更新成功");
    }
    /**
     * 删除
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteWarehouse(Integer[] ids){
        warehouseService.deleteWarehouse(ids);
        return success("库存删除成功");
    }
}