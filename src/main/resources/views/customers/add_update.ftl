<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--设置需求商ID的隐藏域-->
    <input name="id" type="hidden" value="${(customers.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">需求商名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="name" id="name"  value="${(customers.name)!}" placeholder="请输入需求商名称">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="linkPhone"  lay-verify="phone"  value="${(customers.linkPhone)!}"
                   placeholder="请输入联系电话">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="address" value="${(customers.address)!}" id="address" placeholder="请输入联系地址">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="remarks" id="remarks" value="${(customers.remarks)!}" placeholder="请输入">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateCustomers">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/customers/add.update.js"></script>
</body>
</html>