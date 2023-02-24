package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.SupplierQuery;
import com.xy.wms.service.SupplierService;
import com.xy.wms.vo.Supplier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {
    @Resource
    private SupplierService supplierService;

    /**
     * 进入供应商管理主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "supplier/supplier";
    }

    /**
     * 查询供应商列表
     * @param supplierQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(SupplierQuery supplierQuery){
        return supplierService.queryByParamsForTable(supplierQuery);
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addSupplier(Supplier supplier){
        supplierService.addSupplier(supplier);
        return success("供应商添加成功！");
    }

    /**
     * 更新供应商
     * @param supplier
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return success("供应商更新成功！");
    }

    /**
     * 进入添加/修改供应商页面
     * @param supplierId
     * @param request
     * @return
     */
    @RequestMapping("toSupplierPage")
    public String toSupplierPage(Integer supplierId,HttpServletRequest request){
        //判断供应商Id是否为空，不为空表示更新操作，查询供应商
        if (supplierId != null){
            //通过ID查询供应商数据
            Supplier supplier = supplierService.selectByPrimaryKey(supplierId);
            //将数据设置到请求域中
            request.setAttribute("supplier",supplier);
        }
        return "supplier/add_update";
    }
    /**
     * 删除供应商
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSupplier(Integer[] ids){
        supplierService.deleteSupplier(ids);
        return success("供应商删除成功！");
    }

}
