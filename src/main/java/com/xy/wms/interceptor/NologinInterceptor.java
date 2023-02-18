package com.xy.wms.interceptor;

import com.xy.wms.dao.UserMapper;
import com.xy.wms.exceptions.NoLoginException;
import com.xy.wms.utils.LoginUserUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NologinInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户id，判断登录状态
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        if (null == userId || null==userMapper.selectByPrimaryKey(userId)){
            throw new NoLoginException();
        }


        return true;
    }
}
