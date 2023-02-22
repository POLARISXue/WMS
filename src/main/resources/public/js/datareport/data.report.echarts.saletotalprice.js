
$.ajax({
    type:'get',
    url:ctx+"/dataReport/saleTotalPriceByDay",
    dataType:"json",
    success:function (result){
        if (result.code==200){
            var x = result.result.groupNameList;
            var y = result.result.salePriceList;
            loadsaleTotalPriceByDay(x,y)
        }
    }
})

function loadsaleTotalPriceByDay(x,y) {
    var chartDom = document.getElementById('saleTotalPriceByDay');
    var myChart = echarts.init(chartDom);
    var option;
    option = {
        title: {
            text: 'Daily sales\n日销售'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {},
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: { readOnly: false },
                magicType: { type: ['line', 'bar'] },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: x
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} ¥'
            }
        },
        series: [
            {
                name: 'Highest',
                type: 'line',
                data: y,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            }
        ]
    };
    option && myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}


