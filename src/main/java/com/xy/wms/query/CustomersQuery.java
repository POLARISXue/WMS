package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;

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
}
