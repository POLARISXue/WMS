layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var formSelects = layui.formSelects;

    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    form.on('submit(addOrUpdateUser)',function (data){
        var loading = top.layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });


        var url=ctx+"/user/add";
        var info="用户添加成功！";

        if ($('[name=id]').val()){
            url=ctx+"/user/update";
            info="用户修改成功！";
        }
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

    var userId = $('[name=id]').val();
    /**
     * 加载角色下拉框
     */
    formSelects.config("selectId",{
        type:"post",
        searchUrl:ctx+"/role/queryAllRoles?userId="+userId,
        keyName:"roleName",
        keyVal: 'id'
    },true);
    
});