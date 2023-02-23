package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.PermissionMapper;
import com.xy.wms.vo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission,Integer> {
    @Resource
    private PermissionMapper permissionMapper;
    public List<String> queryUserPermissions(Integer userId) {
        return permissionMapper.queryUserHasRoleHasPermissionByUserId(userId);
    }
}
