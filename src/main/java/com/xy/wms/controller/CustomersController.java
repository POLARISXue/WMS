package com.xy.wms.controller;

import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.CustomersQuery;
import com.xy.wms.service.CustomersService;
import com.xy.wms.utils.CookieUtil;
import com.xy.wms.vo.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 需求商管理主页
     * @return
     */
    @RequiredPermission(code = "4020")
    @RequestMapping("/index")
    public String index(){
        return "customers/customers";
    }

    /**
     * 需求商列表查询接口
     * @param customersQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(CustomersQuery customersQuery){
        return customersService.queryByParamsForTable(customersQuery);
    }
    /**
     * 添加需求商
     * @param customers
     * @param request
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCustomers(Customers customers, HttpServletRequest request){
        //获取数据
        String name = CookieUtil.getCookieValue(request,"name");
        customers.setName(name);
        //调用service
        customersService.addCustomers(customers);
        return success("需求商添加成功！");
    }

    /**
     * 更新需求商
     * @param customers
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCustomers(Customers customers){
        //调用service
        customersService.updateCustomers(customers);
        return success("需求商数据添加成功！");
    }

    /**
     * 进入添加/修改需求商数据页面
     * @param customersId
     * @param request
     * @return
     */
    @RequestMapping("toCustomersPage")
    public String toCustomersPage(Integer customersId, HttpServletRequest request){
        //判断customersId是否为空
        if (customersId != null){
            //通过ID查询需求商数据
            Customers customers = customersService.selectByPrimaryKey(customersId);
            //将数据设置到请求域中
            request.setAttribute("customers",customers);
        }
        return "customers/add_update";
    }
    /**
     * 删除需求商
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomers(Integer[] ids){
        //调用Service层的删除方法
        customersService.deleteCustomers(ids);
        return success("需求商数据删除成功！");
    }
}
