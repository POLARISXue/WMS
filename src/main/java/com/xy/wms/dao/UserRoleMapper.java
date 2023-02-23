package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    //根据用户ID查询用户角色记录
    Integer countUserRoleByUserId(Integer userId);

    //根据用户ID删除用户角色记录
    Integer deleteUserRoleByUserId(Integer userId);
}