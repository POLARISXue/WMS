package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

public class SupplierQuery extends BaseQuery {
    //分页参数

    //供应商管理  条件查询
    private String name;//供应商名称
    private String contact;//联系人
    private String linkPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Override
    public StringBuffer getRedisKey() {
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (StringUtils.isNotBlank(name)){
            key.append(":name:"+name);
        }
        if (StringUtils.isNotBlank(contact)){
            key.append(":contact:"+contact);
        }
        if (StringUtils.isNotBlank(linkPhone)){
            key.append(":linkPhone:"+linkPhone);
        }
        return key;
    }
}
