<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#-- 用户ID -->
            <input name="id" type="hidden" value="${(goodsTypeInfo.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label" style="width: 120px"  >商品类型名称</label>
                <div class="layui-input-block" >
                    <input type="text" class="layui-input typeName"
                           lay-verify="required" name="typeName" id="typeName"  value="${(goodsTypeInfo.typeName)!}" placeholder="请输入商品名称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label" style="width: 120px"  >商品类型编号</label>
                <div class="layui-input-block" >
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="id" id="id" value="${(goodsTypeInfo.id)!}" readonly>
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateGoodsType">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/goods_type/add.update.js"></script>
    </body>
</html>