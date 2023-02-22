package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    Integer countPermissionByModuleId(Integer id);

    void deletePermissionByModuleId(Integer id);

    List<Integer> selectRoleHasModuleIdsByRoleId(Integer roleId);

    Integer countPermissionByRoleId(Integer roleId);

    void deletePermissionByRoleId(Integer roleId);
}