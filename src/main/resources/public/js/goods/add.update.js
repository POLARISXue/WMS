layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var formSelects = layui.formSelects;

    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    form.on('submit(addOrUpdateGoods)',function (data){
        var loading = top.layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });


        var url=ctx+"/goods/add";
        var info="商品添加成功！";

        if ($('[name=id]').val()){
            url=ctx+"/goods/update";
            info="商品修改成功！";
        }

        console.log(data.field);
        $.post(url,data.field,function(result){
            if (result.code==200){
                // 关闭弹出层（返回值为index的弹出层）
                top.layer.close(loading);
                top.layer.msg(info,{icon:6});
                // 关闭所有ifream层
                layer.closeAll("iframe");
                // 刷新⽗⻚⾯
                parent.location.reload();
            }else {
                layer.msg(result.msg,{icon:5});
            }
        })
        return false;
    });

    $.ajax({
        type:"get",
        url: ctx+"/goods/queryAllGoodsType",
        data:{},
        success:function (data) {
            var selected = $("#hiddenGoodsTypeId").val();

            if (data != null){
                for (var i = 0; i <data.length;i++){
                    if (data[i].id == selected){
                        var option = "<option selected value='"+data[i].id+"'>"+data[i].goods_type+"</option>";
                    }else {
                        var option = "<option value='"+data[i].id+"'>"+data[i].goods_type+"</option>";
                    }

                    //设置下拉选项

                    $("#goodsTypeId").append(option);
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    })
    
});