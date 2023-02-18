layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    form.on('submit(addOrUpdateSaleChance)', function(data){
        var loading = layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        }); //0代表加载的风格，支持0-2

        var url =ctx
        if ($("input[name='id']").val()){
            url +=  "/sale_chance/update";
        }else {
            url +=  "/sale_chance/add";
        }

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
        url: ctx+"/user/queryAllSales",
        data:{},
        success:function (data) {
            if (data != null){
                var selectedId = $("#assignManId").val();
                for (var i = 0; i <data.length;i++){

                    var option;
                    if (selectedId == data[i].id){
                        option = "<option value='"+data[i].id+"' selected>"+data[i].user_name+"</option>";
                    }else {
                        option = "<option value='"+data[i].id+"'>"+data[i].user_name+"</option>";
                    }

                    //设置下拉选项

                    $("#assignMan").append(option);
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    })


    
});