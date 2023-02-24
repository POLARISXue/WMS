<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WMS后台管理-登录</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/index.css" media="all">
    <link rel="stylesheet" href="${ctx}/css/layout.css?v=0.2">
</head>
<body>
<div class="preloader">
    <div class="spinner">
        <span class="sk-inner-circle"></span>
    </div>
</div>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div id="indexTitle" style="height: 100px;width: 100%" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" name="username" lay-verify="required|account" placeholder="用户名或者邮箱" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" >
                </div>
                <#-- 记住我 -->
                <div class="layui-form-item">
                    <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary"
                           title="记住密码">
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/js/jquery-2.1.3.min.js"></script>
<script src="${ctx}/js/index.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/echarts.js"></script>
<script type="text/javascript" src="https://fastly.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/index.Title.js"></script>

</body>
</html>