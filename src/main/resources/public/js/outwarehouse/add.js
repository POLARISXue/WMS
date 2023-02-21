layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    form.on('submit(addOutWarehouse)', function(data){
        var loading = layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        }); //0代表加载的风格，支持0-2

        var url =ctx+"/outwarehouse/add";


        console.log(data.field)
        $.post(url,data.field,function(result){
            if (result.code==200){
                layer.msg("操作成功")
                layer.close(loading);
                layer.closeAll("iframe");
                parent.location.reload();
            }else {
                layer.msg(result.msg,{icon:5});
            }
        })
        return false;
    })




    $.ajax({
        type:"get",
        url: ctx+"/goods/queryAllGoods",
        data:{},
        success:function (data) {
            if (data != null){

                for (var i = 0; i <data.length;i++){
                    var option = "<option value='"+data[i].id+"'>"+data[i].goods_name+"</option>";
                    //设置下拉选项

                    $("#goodsId").append(option);
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    })

    $.ajax({
        type:"get",
        url: ctx+"/customers/queryAllCustomers",
        data:{},
        success:function (data) {
            if (data != null){
                for (var i = 0; i <data.length;i++){
                    var option = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                    //设置下拉选项

                    $("#customersId").append(option);
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    })









    
});



