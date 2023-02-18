layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })


    console.log();

    form.on('submit(addOrUpdateCusDevPlan)',function (data){



        var loading = top.layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });

        //得到所有的表单元素的值
        var url=ctx+"/cus_dev_plan/add";
        if ($('[name=id]').val()){
            url=ctx+"/cus_dev_plan/update";
        }

        $.post(url,data.field,function(result){
            if (result.code==200){
                    // 关闭弹出层（返回值为index的弹出层）
                    top.layer.close(loading);
                    top.layer.msg("操作成功！",{icon:6});
                    // 关闭所有ifream层
                    layer.closeAll("iframe");
                    // 刷新⽗⻚⾯
                    parent.location.reload();
            }else {
                layer.msg(result.msg,{icon:5});
            }
        })
        return false;
    })

})