<!DOCTYPE html>
<html>
<head>
    <title>需求商管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="name"
                           class="layui-input
					searchVal" placeholder="需求商名称" />
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>

    <#-- 在页面放置一个元素 <table id="demo"></table>，然后通过 table.render() 方法指定该容器 -->
    <table id="customersList" class="layui-table"  lay-filter="customers"></table>

    <#-- 头部工具栏 -->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-radius layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe654;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-radius layui-btn-danger delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe640;</i>
                删除
            </a>
        </div>
    </script>


    <!-- 行工具栏 -->
    <script id="customersListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/customers/customers.js"></script>

</body>
</html>