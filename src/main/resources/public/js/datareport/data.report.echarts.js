

$.ajax({
    type:'get',
    url:ctx+"/dataReport/inventoryOverview",
    dataType:"json",
    success:function (result){
        if (result.code==200){
            var x = result.result.goodsNameList;
            var y = result.result.goodsNumberList;
            loadInventoryOverview(x,y)
        }
    }
})

function loadInventoryOverview(x,y) {
    var chartDom = document.getElementById('echarts-1');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        title: {
            text: 'Inventory Overview\n 仓库概览',
            textStyle: {
                fontSize: 20,
                fontStyle: "italic"
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {},
                dataView: {},
                restore: {},
                dataZoom: {},
                magicType: {type: ['bar', 'line']}
            }
        },
        grid: {
            left: '5%',
            right: '5%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            name: '物品名称',
            type: 'category',
            data: x,
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} item'
            }
        },
        series: [
            {
                name: '数量',
                data: y,
                type: 'bar',
                markPoint: {
                    data: [
                        {
                            type: 'max', name: '最大值'
                        },
                        {
                            type: 'min', name: '最小值'
                        }
                    ]
                },
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    };
    option && myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}





