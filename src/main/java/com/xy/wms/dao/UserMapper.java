package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.User;

public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);
}