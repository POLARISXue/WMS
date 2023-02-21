<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#-- 用户ID -->
            <input name="id" type="hidden" value="${(userInfo.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input goodsName"
                           lay-verify="required" name="goodsName" id="goodsName"  value="${(userInfo.goodsName)!}" placeholder="请输入商品名称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">商品类型编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="goodsTypeId" id="goodsTypeId" value="${(userInfo.goodsTypeId)!}" placeholder="请输入商品类型编号">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">进价</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="required" name="goodsCostPrice" value="${(userInfo.goodsCostPrice)!}"
                           id="goodsCostPrice"
                           placeholder="进价">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">售价</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="required" name="goodsSalePrice" value="${(userInfo.goodsSalePrice)!}" id="goodsSalePrice" placeholder="请输入售价">
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateGoods">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/goods/add.update.js"></script>
    </body>
</html>