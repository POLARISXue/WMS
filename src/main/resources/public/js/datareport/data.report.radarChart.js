
$.ajax({
    type:'get',
    url:ctx+"/dataReport/loadRadarChart",
    dataType:"json",
    success:function (result){
        if (result.code==200){
            var x = [];
            x=result.result.typeNameList;
            var y1 = result.result.wareHouseList;
            var y2 = result.result.outWarehouseList;
            indicator=[];
            for (var i=0;i<x.length;i++){
                indicator.push({name:x[i],max:3000});
            }
            loadRadarChart(indicator,y1,y2)
        }
    }
})




function loadRadarChart(indicator,y1,y2) {
    var chartDom = document.getElementById('radarChart');
    var myChart = echarts.init(chartDom);
    var option;


    option = {
        title: {
            text: 'Radar Chart'
        },
        legend: {
            data: ['销售', '库存']
        },
        radar: {
            // shape: 'circle',
            indicator: indicator
        },
        series: [
            {
                name: 'Budget vs spending',
                type: 'radar',
                data: [
                    {
                        value: y1,
                        name: '库存'
                    },
                    {
                        value: y2,
                        name: '销售'
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