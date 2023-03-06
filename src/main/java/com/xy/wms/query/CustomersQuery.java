package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

public class CustomersQuery extends BaseQuery {
    //分页参数

    //需求商管理  条件查询
    private String name;//需求商名称
    private String linkPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Override
    public StringBuffer getRedisKey(){
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (StringUtils.isNotBlank(name)){
            key.append(":name:"+name);
        }
        if (StringUtils.isNotBlank(linkPhone)){
            key.append(":linkPhone:"+linkPhone);
        }
        return key;
    }
}
