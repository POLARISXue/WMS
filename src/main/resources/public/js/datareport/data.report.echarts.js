
    var chartDom = document.getElementById('echarts-1');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
    title:{
    left: "center",
    text:'Inventory overview',
    subtext: "X-Item Name/物品 名称\nY-Item Quantity/物品 数量",
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
    toolbox:{
    feature:{
    saveAsImage:{},
    dataView:{},
    restore:{},
    dataZoom:{},
    magicType:{type:['bar','line']}
}
},
    grid: {
    left: '5%',
    right: '5%',
    bottom: '3%',
    containLabel: true
},
    xAxis: {
    name:'物品名称',
    type: 'category',
    data: [150, 230, 224, 218, 135, 147, 260]
},
    yAxis: {
    type: 'value'
},
    series: [
{
    name:'数量',
    data: [150, 230, 224, 218, 135, 147, 260],
    type: 'bar',
    markPoint:{
    data:[
{
    type:'max',name:'最大值'
},
{
    type: 'min',name: '最小值'
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

