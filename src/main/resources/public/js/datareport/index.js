layui.use(['util', 'laydate', 'layer'], function() {
    var util = layui.util
        , laydate = layui.laydate
        , $ = layui.$
        , layer = layui.layer;
    //固定块
    util.fixbar({
        bar2: true
        , css: {right: 50, bottom: 100}
        , bgcolor: '#393D49'
        , click: function (type) {
            if (type === 'bar2') {
                layer.msg('数据皆为实时数据');
            }
        }
    });

})