package com.xy.wms.model;


public class UserLoginModel {

    private String userIdStr;
    private String userName;

    public UserLoginModel(String userIdStr, String userName) {
        this.userIdStr = userIdStr;
        this.userName = userName;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
