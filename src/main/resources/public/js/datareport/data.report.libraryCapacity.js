


$.ajax({
    type:'get',
    url:ctx+"/dataReport/queryInventory",
    dataType:"json",
    success:function (result){
        if (result.code==200){
            var x = result.result.inventory;
            var y = result.result.Librarycapacity;
            loadInventory(x,y)
        }
    }
})

function loadInventory(x,y) {
    var chartDom = document.getElementById('libraryCapacity');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        series: [
            {
                name: 'Pressure',
                type: 'gauge',
                min:0,
                max:y,
                progress: {
                    show: true,
                },
                axisLine: {
                    lineStyle: {
                        width: 30,
                        color: [
                            [0.3, '#67e0e3'],
                            [0.7, '#428bcf'],
                            [1, '#fd666d']
                        ]
                    }
                },
                pointer: {
                    itemStyle: {
                        color: 'inherit'
                    }
                },
                splitLine: {
                    distance: -30,
                    length: 30,
                    lineStyle: {
                        color: '#fff',
                        width: 4
                    }
                },
                detail: {
                    valueAnimation: true,
                    formatter: '{value} Item',
                    color: 'inherit'
                },
                axisLabel: {
                    color: 'inherit',
                    distance: 30,
                    fontSize: 15
                },
                data: [
                    {
                        value: x,
                    }
                ]
            }
        ]
    };

    option && myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}

