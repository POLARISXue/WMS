<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">物品</label>
                <div class="layui-input-block">
                    <select name="goodsId" id="goodsId" lay-filter="addOrUpdateIntoWarehouse">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">供应商</label>
                <div class="layui-input-block" id="supplierSelect">
                    <select name="supplierId" id="supplierId">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">物品数量</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="goodsNumber"  lay-verify="required"  value="${(saleChance.linkMan)!}" placeholder="请输入物品数量">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">分配状态</label>
                <div class="layui-input-block">
                    <select name="state" id="state">
                        <option value="">请选择</option>
                        <option value="0">未入库</option>
                        <option value="1">已入库</option>
                    </select>
                </div>
            </div>


            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                            name="remarks" value="${(saleChance.overview)!}" id="remarks" placeholder="请输入备注">
                </div>
            </div>

            <br/>

            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateIntoWarehouse">确认 </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>

        </form>
    <script src="${ctx}/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/intowarehouse/add.update.js"></script>


    </body>

</html>