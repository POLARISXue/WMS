<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会ID的隐藏域 -->
    <input type="hidden" name="id" value="${(warehouse.id)!}">
    <#--设置货物名称我隐藏域-->
    <input type="hidden" id="goodsNameId" value="${(warehouse.goodsName)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">选择货物</label>
        <div class="layui-input-block">
            <select name="goodsId" id="goodsId">
                <option value="">请选择</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">货物数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="goodsNumber" value="${(warehouse.goodsNumber)!}" id="goodsNumber" placeholder="请输⼊货物数量">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateWarehouse">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id ="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/warehouse/add_update.js"></script>
</body>
</html>