<!DOCTYPE html>
<html>
<head>
  <#include "../common.ftl">
</head>


<body class="childrenBody">

<div class="layui-tab-item layui-show layui-bg-gray">
    <div class="" style="padding: 30px;">
        <div class="layui-row layui-col-space18">
            <div class="layui-bg-gray" style="padding: 15px;">
                <div class="layui-row layui-col-space15">

                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <div class="layui-card-header">仓库物品概览</div>
                            <div class="layui-card-body" id="echarts-1" style="width: 100%;height:700px">

                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="layui-bg-gray" style="padding: 15px;">
                <div class="layui-row layui-col-space18">
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header">支出额</div>
                            <div class="layui-card-body" id="expensesTotalPriceByDay" style="width: 100%;height:700px">

                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header">销售额</div>
                            <div class="layui-card-body" id="saleTotalPriceByDay" style="width: 100%;height:700px">

                            </div>
                        </div>
                    </div>
                </div>
            </div>



        </div>
    </div>
</div>


<script type="text/javascript" src="${ctx}/js/echarts.js"></script>
<script type="text/javascript" src="${ctx}/js/datareport/data.report.echarts.js"></script>
<script type="text/javascript" src="${ctx}/js/datareport/data.report.echarts.saletotalprice.js"></script>

</body>
</html>
