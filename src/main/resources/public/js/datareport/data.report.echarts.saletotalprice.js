
$.ajax({
    type:'get',
    url:ctx+"/dataReport/dailySalesExpenses",
    dataType:"json",
    success:function (result){
        if (result.code==200){
            var x1 = result.result.saleGroupNameList;
            var y1 = result.result.salePriceList;
            var x2 = result.result.expensesGroupNameList;
            var y2 = result.result.expensesPriceList;
            loadsaleTotalPriceByDay(x1,y1)
            loadExpensesTotalPriceByDay(x2,y2)
        }
    }
})

function loadsaleTotalPriceByDay(x,y) {
    var chartDom = document.getElementById('saleTotalPriceByDay');
    var myChart = echarts.init(chartDom);
    var option;
    option = {
        title: {
            text: 'Daily sales/expenses\n日销售/支出'
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
                name: 'sales volume',
                type: 'line',
                data: y,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' },
                        [
                            {
                                symbol: 'none',
                                x: '90%',
                                yAxis: 'max'
                            },
                            {
                                symbol: 'circle',
                                label: {
                                    position: 'start',
                                    formatter: 'Max'
                                },
                                type: 'max',
                                name: '最高点'
                            }
                        ]],

                }
            }
        ]
    };
    option && myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}


function loadExpensesTotalPriceByDay(x,y) {
    var chartDom = document.getElementById('expensesTotalPriceByDay');
    var myChart = echarts.init(chartDom);
    var option;
    option = {
        title: {
            text: 'Daily expenses\n日支出'
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
                name: 'Expenditure',
                type: 'line',
                data: y,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                lineStyle: { // 设置线条的style等
                    normal: {
                        color: 'green', // 折线线条颜色:红色
                    },
                },
                itemStyle: {
                    // 设置线条上点的颜色（和图例的颜色）
                    normal: {
                        color: 'green',
                    },
                },
                markLine: {
                    color: 'green',
                    data: [
                        { type: 'average', name: 'Avg' },
                        [
                            {
                                symbol: 'none',
                                x: '90%',
                                yAxis: 'max'
                            },
                            {
                                symbol: 'circle',
                                label: {
                                    position: 'start',
                                    formatter: 'Max'
                                },
                                type: 'max',
                                name: '最高点'
                            }
                        ]
                    ]
                }
            }
        ]
    };
    option && myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}


