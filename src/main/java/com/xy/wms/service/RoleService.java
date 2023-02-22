package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.ModuleMapper;
import com.xy.wms.dao.PermissionMapper;
import com.xy.wms.dao.RoleMapper;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.Permission;
import com.xy.wms.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role,Integer> {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ModuleMapper moduleMapper;

    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleMapper.queryAllRoles(userId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        //参数校验
        AssertUtil.isTrue(null == role,"待添加角色信息不存在");
        AssertUtil.isTrue(null == role.getRoleName(),"待添加角色名称不能为空");
        AssertUtil.isTrue(null != roleMapper.selectByRoleName(role.getRoleName()),"角色名称不能重复");
        //设置初始值
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        //执行添加操作，根据返回行数判断是否添加成功
        AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"角色添加失败");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        //参数校验
        AssertUtil.isTrue(null == role.getId(),"待更新不存在");
        //判断用户名查询到的用户是否存在
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue( null == temp ,"待更新记录不存在");
        //角色名称唯一非空判断
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名称不能为空");
        //通过用户名称查询角色记录
        temp = roleMapper.selectByRoleName(role.getRoleName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(role.getId())),"角色名称已存在，请重新输入");
        //设置默认值
        role.setUpdateDate(new Date());
        //执行添加操作
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"角色修改失败");

    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer roleId) {
        //参数校验
        AssertUtil.isTrue(null == roleId,"待删除不存在");
        //判断用户名查询到的用户是否存在
        Role temp = roleMapper.selectByPrimaryKey(roleId);
        AssertUtil.isTrue( null == temp ,"待删除记录不存在");
        //设置默认值
        temp.setIsValid(0);
        temp.setUpdateDate(new Date());
        //执行添加操作
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(temp)<1,"用户修改失败");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer roleId, Integer[] mIds) {
        //通过角色id查询对应的权限数据
        Integer count = permissionMapper.countPermissionByRoleId(roleId);
        //如果不为空，删除权限
        if (count != null){
            //删除权限
            permissionMapper.deletePermissionByRoleId(roleId);
        }
        //如果有选中记录添加权限
        if (mIds != null && mIds.length>0){
            List<Permission> permissionList = new ArrayList<>();
            for (Integer mId : mIds){
                Permission permission = new Permission();
                //设置参数
                permission.setModuleId(mId);
                permission.setRoleId(roleId);
                permission.setAclValue(moduleMapper.selectByPrimaryKey(mId).getOptValue());
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                //将对象添加到集合中
                permissionList.add(permission);
            }
            // 执行批量添加操作，判断受影响的行数
            AssertUtil.isTrue(permissionMapper.insertBatch(permissionList) != permissionList.size(), "角色授权失败！");

        }
    }
}
