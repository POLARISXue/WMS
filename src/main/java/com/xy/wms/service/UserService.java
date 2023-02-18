package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.dao.UserMapper;
import com.xy.wms.model.UserLoginModel;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.utils.Md5Util;
import com.xy.wms.utils.UserIDBase64;
import com.xy.wms.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     * @param userName
     * @param userPwd
     * @return
     */
    public ResultInfo login(String userName,String userPwd){
        checkLoginParams(userName,userPwd);
        User user = userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(user == null,"该用户不存在");
        checkLoginPwd(userPwd,user.getUserPwd());
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResult(new UserLoginModel(UserIDBase64.encoderUserID(user.getId()),user.getUserName()));
        return resultInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultInfo updateUserPassword(Integer id,String oldPassword,String newPassword,String confirmPassword){
        ResultInfo resultInfo = new ResultInfo();
        //参数校验
        checkUpdateUserPasswordParams(oldPassword,newPassword,confirmPassword);
        //根据id获取用户对象
        User user = userMapper.selectByPrimaryKey(id);
        //检查用户是否存在
        AssertUtil.isTrue(user == null ,"修改用户已不存在");
        //检查原始密码是否正确
        checkLoginPwd(oldPassword,user.getUserPwd());
        user.setUserPwd(Md5Util.encode(newPassword));
        int row = userMapper.updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(row < 1,"密码更新失败");
        return resultInfo;
    }

    /**
     * 登录参数校验
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName,String userPwd){
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空");
    }

    /**
     * 登录密码校验
     * @param inputPwd
     * @param truePwd
     */
    private void checkLoginPwd(String inputPwd,String truePwd){
        inputPwd = Md5Util.encode(inputPwd);
        AssertUtil.isTrue(!inputPwd.equals(truePwd),"密码不正确");
    }

    /**
     * 修改密码校验
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkUpdateUserPasswordParams(String oldPassword,String newPassword,String confirmPassword){
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"新密码确认值为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"新密码两次输入不一致");
        AssertUtil.isTrue(newPassword.equals(oldPassword),"新密码与旧密码不能一致");
    }

}
