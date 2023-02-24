<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- ID -->
    <input name="id" type="hidden" value="${(supplier.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供应商名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="name" id="name"  value="${(supplier.name)!}" placeholder="请输入供应商名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="contact" id="contact" value="${(supplier.contact)!}" placeholder="请输入联系人">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系号码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="linkPhone" name="linkPhone" value="${(supplier.linkPhone)!}" id="linkPhone" placeholder="请输入联系号码">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="address" name="address" value="${(supplier.address)!}"
                   id="address"
                   placeholder="请输入联系地址">
        </div>
    </div>

    <div class="layui-form-item layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">供应商品</label>
        <div class="layui-input-block">
            <select name="goodsIds" xm-select="selectId">
            </select>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateSupplier">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/supplier/add.update.js"></script>
</body>
</html>