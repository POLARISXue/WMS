package com.xy.wms.query;


import com.xy.wms.base.BaseQuery;

public class UserQuery extends BaseQuery {

    private String userName;//用户名
    private String email;//邮箱
    private String phone;//手机号

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
