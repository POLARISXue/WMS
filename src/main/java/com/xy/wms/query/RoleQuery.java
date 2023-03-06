package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

public class RoleQuery extends BaseQuery {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public StringBuffer getRedisKey() {
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (StringUtils.isNotBlank(roleName)){
            key.append(":roleName:"+roleName);
        }
        return key;
    }

}
