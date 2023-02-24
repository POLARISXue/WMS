package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.dao.UserMapper;
import com.xy.wms.dao.UserRoleMapper;
import com.xy.wms.model.UserLoginModel;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.utils.Md5Util;
import com.xy.wms.utils.PhoneUtil;
import com.xy.wms.utils.UserIDBase64;
import com.xy.wms.vo.User;
import com.xy.wms.vo.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

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

    /**
     * 添加用户
     *  1.参数校验
     *      用户名userName     非空，唯一性
     *      邮箱email          非空
     *      手机号phone        非空，格式正确
     *  2.设置参数的默认值
     *      isValid           1
     *      createDate        系统当前时间
     *      updateDate        系统当前时间
     *      默认密码            123456-> md5加密
     *  3.执行添加操作，判断受影响的行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user){
        //1.参数校验
        checkUserParams(user.getUserName(),user.getEmail(),user.getPhone(),null);
        //2.设置参数的默认值
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //设置默认密码
        user.setUserPwd(Md5Util.encode("123456"));

        //3.执行添加操作，判断受影响的行数
        AssertUtil.isTrue(userMapper.insertSelective(user) <1,"用户添加失败！");

        /* 用户角色关联  */
         relationUserRole(user.getId(),user.getRoleIds());
    }


    private void relationUserRole(Integer userId, String roleIds) {
        //通过用户ID查询角色记录
        Integer count = userRoleMapper.countUserRoleByUserId(userId);
        //判断角色记录是否存在
        if (count > 0){
            //如果角色记录存在，则删除该用户对应的角色记录
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count,"用户角色分配失败！");
        }
        //判断角色ID是否存在，如果存在，则添加该用户对应的角色记录
        if (StringUtils.isNotBlank(roleIds)){
            //将用户角色数据设置到集合中，执行批量添加
            List<UserRole> userRoleList = new ArrayList<>();
            //将角色ID字符串转换成数组
            String[] roleIdsArray = roleIds.split(",");
            //遍历数组，得到对应的用户角色对象，并设置到集合中
            for (String roleId:roleIdsArray) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(userId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                //设置到集合中
                userRoleList.add(userRole);
            }
            //批量添加用户角色记录
            AssertUtil.isTrue(userRoleMapper.insertBatch(userRoleList) != userRoleList.size(),"用户角色分配失败！");
        }
    }




    /**
     * 更新用户
     *  1.参数校验
     *  判断用户ID是否为空，且数据存在
     *      用户名userName     非空，唯一性
     *      邮箱email          非空
     *      手机号phone        非空，格式正确
     *  2.设置参数的默认值
     *      updateDate        系统当前时间
     *  3.执行添加操作，判断受影响的行数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user){
        //判断用户ID是否为空，且数据存在
        AssertUtil.isTrue(null ==user.getId(),"待更新记录不存在！");
        //通过ID查询数据
        User temp = userMapper.selectByPrimaryKey(user.getId());
        //判断是否存在
        AssertUtil.isTrue(null == temp,"待更新记录不存在！");
        //1.参数校验
        checkUserParams(user.getUserName(),user.getEmail(),user.getPhone(),user.getId());
        //2.设置参默认值
        user.setUpdateDate(new Date());
        //3.执行添加操作，判断受影响的行数
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) !=1,"用户更新失败！");

        /* 用户角色关联  */
         relationUserRole(user.getId(),user.getRoleIds());
    }

    private void checkUserParams(String userName, String email, String phone,Integer userId) {
        //判断用户名是否为空
        //通过用户名查询用户对象
        User temp = userMapper.queryUserByUserName(userName);
        //如果用户对象为空，则表示用户名可用；如果用户对象不为空，则表示用户名不可用
        // 如果是添加操作，数据库中无数据，只要通过名称查到数据，则表示用户名被占用
        // 如果是修改操作，数据库中有对应的记录，通过用户名查到数据，可能是当前记录本身，也可能是别的记录
        // 如果用户名存在，且与当前修改记录不是同一个，则表示其他记录占用了该用户名，不可用
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)),"用户名已存在，请重新输入！");

        //邮箱非空
        AssertUtil.isTrue(StringUtils.isBlank(email),"用户邮箱不能为空！");

        //手机号非空
        AssertUtil.isTrue(StringUtils.isBlank(phone),"用户手机号不能为空！");

        //手机号 格式正确
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号格式不正确！");
    }

    //用户删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        // 判断ids是否为空，长度是否大于0
        AssertUtil.isTrue(ids == null && ids.length == 0,"待删除记录不存在！");
        // 执行删除操作，判断受影响的行数
        AssertUtil.isTrue(userMapper.deleteBatch(ids) !=ids.length,"用户删除失败！");

        //遍历用户ID的数组
        for (Integer userId : ids) {
            //通过用户ID查询对应的用户角色记录
            Integer count = userRoleMapper.countUserRoleByUserId(userId);
            //判断用户角色记录是否存在
            if (count > 0){
                //通过用户ID删除对应的用户角色记录
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) !=count,"删除用户失败！");
            }
        }
    }

}
