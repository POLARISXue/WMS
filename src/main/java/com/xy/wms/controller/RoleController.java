package com.xy.wms.controller;

import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.RoleQuery;
import com.xy.wms.service.RoleService;
import com.xy.wms.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role/role";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(RoleQuery roleQuery){
        return roleService.queryByParamsForTable(roleQuery);
    }
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("角色添加成功！");
    }
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("角色修改成功！");
    }
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo addRole(Integer roleId){
        roleService.deleteRole(roleId);
        return success("角色删除成功！");
    }
    @RequestMapping("toAddOrUpdateRolePage")
    public String openAddOrUpdateRolePage(Integer roleId, HttpServletRequest request){
        if (roleId != null){
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("role",role);
        }
        return "role/add_update";
    }
    @PostMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer roleId,Integer[] mIds){
        roleService.addGrant(roleId,mIds);
        return success("角色授权成功");

    }
}
