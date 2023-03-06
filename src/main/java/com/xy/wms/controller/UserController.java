package com.xy.wms.controller;


import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.UserQuery;
import com.xy.wms.service.UserService;
import com.xy.wms.utils.LoginUserUtil;
import com.xy.wms.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(String userName, String password){
        return userService.login(userName,password);
    }

    @PostMapping ("/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        return userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request),oldPassword,newPassword,confirmPassword);
    }
    @RequiredPermission(code = "6010")
    @RequestMapping("/index")
    public String index() {
        return "user/user";
    }

    // 分页多条件查询用户列表
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> selectByParams(UserQuery userQuery){

        return userService.queryByParamsForTable(userQuery,"user:list");
    }

    //添加用户
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user){
        userService.addUser(user);
        return success("用户添加成功！");
    }

    //更新用户
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户更新成功！");
    }

    //打开添加或修改用户的页面
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id,HttpServletRequest request){
        //判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null){
            //通过id查询用户对象
            User user = userService.selectByPrimaryKey(id);
            //将数据设置到请求域中
            request.setAttribute("userInfo",user);
        }
        return "user/add_update";
    }

    //用户删除
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteByIds(ids);
        return success("用户删除成功！");
    }

}
