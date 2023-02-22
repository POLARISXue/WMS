<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <input type="hidden" name="id" id="outWarehouseId" value="${(outWarehouse.id)!}">
            <input type="hidden"  id="outWarehousegoodsId" value="${(outWarehouse.goodsId)!}">
            <input type="hidden"  id="outWarehousecustomersId" value="${(outWarehouse.customersId)!}">
            <input type="hidden"  id="outWarehouseStateId" value="${(outWarehouse.outWarehouseState)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">物品</label>
                <div class="layui-input-block">
                    <select name="goodsId" id="goodsId" lay-filter="addOrUpdateIntoWarehouse">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">需求商</label>
                <div class="layui-input-block" id="supplierSelect">
                    <select name="customersId" id="customersId">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">物品数量</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="goodsNumber"  lay-verify="required"  value="${(outWarehouse.goodsNumber)!}" placeholder="请输入物品数量">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">分配状态</label>
                <div class="layui-input-block">
                    <select name="outWarehouseState" id="state">
                        <option value="">请选择</option>
                        <option value="0">未出库</option>
                        <option value="1">已出库</option>
                    </select>
                </div>
            </div>


            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                            name="remarks" value="${(outWarehouse.remarks)!}" id="remarks" placeholder="请输入备注">
                </div>
            </div>

            <br/>

            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="updateOutWarehouse">确认 </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>

        </form>
    <script src="${ctx}/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/outwarehouse/update.js"></script>


    </body>

</html>