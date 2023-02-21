<!DOCTYPE html>
<html>
    <head>
        <title>用户管理</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="goodsName" class="layui-input searchVal" placeholder="商品名称" />
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="goodsTypeId" class="layui-input searchVal" placeholder="商品类型编号" />
                        </div>
                        <a class="layui-btn search_btn" data-type="reload">
                            <i class="layui-icon">&#xe615;</i>
                            搜索
                        </a>
                    </div>
                </form>
            </blockquote>

            <table id="goodsList" class="layui-table"  lay-filter="goods"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-radius layui-btn-normal addNews_btn" lay-event="add">
                        <i class="layui-icon">&#xe608;</i>
                        添加商品
                    </a>
                    <a class="layui-btn layui-btn-radius layui-btn-danger delNews_btn" lay-event="del">
                        <i class="layui-icon">&#xe608;</i>
                        删除商品
                    </a>
                </div>
            </script>

            <!--操作-->
            <script id="goodsListBar" type="text/html">
                <a class="layui-btn layui-btn-sm layui-btn-warm " id="edit" lay-event="edit" >修改数据</a>
<#--                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>-->
            </script>
        </form>

    <script type="text/javascript" src="${ctx}/js/goods/goods.js"></script>
    </body>
</html>