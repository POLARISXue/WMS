layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var formSelects = layui.formSelects;

    $("#closeBtn").click(function (){
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
        parent.layer.close(index);//再执行关闭
    });


    form.on("submit(addOrUpdateSupplier)", function (data) {
        //提交数据时的加载层
        var index = layer.msg('数据提交中，请稍候。。。', {
            //图标
            icon: 16,
            //不关闭
            time: false,
            //设置遮罩的透明度
            shade: 0.8
        });

        //发送ajax请求
        var url = ctx + "/supplier/add";//添加操作

        //通过供应商的ID来判断当前需要执行的是添加操作还修改操作
        //如果供应商的ID为空，则表示执行添加操作；如果ID不为空，则表示执行更新操作
        //通过获取隐藏域的ID


        if($("input[name='id']").val()){
            url=ctx + "/supplier/update";
        }
        $.post(url, data.field, function (result) {
            //判断操作是否执行成功  200=成功
            if (result.code == 200) {
                //成功
                //提示成功
                layer.msg("操作成功！",{icon:6});
                //关闭加载层
                layer.close(index);
                //关闭弹出层
                layer.closeAll("iframe");
                //刷新父窗口，重新加载数据
                parent.location.reload();
            } else {
                layer.msg(
                    result.msg, {icon: 5});
            }
        });
        //阻止表单提交
        return false;
    });
    /**
     * 关闭弹出层
     */
    var supplierId = $('[name=id]').val();
    formSelects.config("selectId",{
        type:"post",
        searchUrl:ctx+"/goodssupplier/queryAllGoodsSupplier?supplierId="+supplierId,
        keyName:"goodsName",
        keyVal: 'id'
    },true);


    //重新渲染下拉框的内容
    layui.form.render("select");
});