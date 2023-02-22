layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    form.on('submit(addOrUpdateIntoWarehouse)', function(data){
        var loading = layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        }); //0代表加载的风格，支持0-2

        var url =ctx

        url +=  "/intowarehouse/update";

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
    var selectedId = $("#intoWarehouseStateId").val();
    if (selectedId==0){
        $("#state").find("option[value='0']").attr("selected",true);
    }else if (selectedId==1){
        $("#state").find("option[value='1']").attr("selected",true);
    }




    $.ajax({
        type:"get",
        url: ctx+"/goods/queryAllGoods",
        data:{},
        success:function (data) {
            if (data != null){
                var selectedId = $("#intoWarehousegoodsId").val();
                for (var i = 0; i <data.length;i++){
                    var option;
                    if (selectedId == data[i].id){
                        option = "<option value='"+data[i].id+"' selected>"+data[i].goods_name+"</option>";
                    }else {
                        option = "<option value='"+data[i].id+"'>"+data[i].goods_name+"</option>";
                    }

                    //设置下拉选项

                    $("#goodsId").append(option);
                }
            }
            var goodsId=$("select[name='goodsId']").val();

            $.ajax({
                type:"get",
                url: ctx+"/goodssupplier/queryAllSupplierByGoodsId?goodsId="+goodsId,
                success:function (data) {
                    if (data != null) {
                        var selectedId = $("#intoWarehousesupplierId").val();
                        if (data.length!=0){
                            $("#supplierId").empty();
                            $("#supplierId").append("<option value=\"\">请选择</option>\n")
                            for (var i = 0; i < data.length; i++) {
                                var option;
                                if (selectedId == data[i].id) {
                                    option = "<option value='" + data[i].id + "' selected>" + data[i].name + "</option>";
                                } else {
                                    option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                //设置下拉选项
                                $("#supplierId").append(option);
                            }
                        }else {
                            $("#supplierId").empty();
                            $("#supplierId").append("<option value=\"\">请选择</option>\n")
                        }

                    }
                    //重新渲染下拉框
                    layui.form.render("select");

                }
            })


            //重新渲染下拉框
            layui.form.render("select");
        }
    })


    form.on('select(addOrUpdateIntoWarehouse)',function (data) {
        var goodsId=$("select[name='goodsId']").val();



        $.ajax({
            type:"get",
            url: ctx+"/goodssupplier/queryAllSupplierByGoodsId?goodsId="+goodsId,
            success:function (data) {
                if (data != null) {
                    var selectedId = $("#supplierId").val();
                    if (data.length!=0){
                        $("#supplierId").empty();
                        $("#supplierId").append(
                            "<option value=\"\">请选择</option>\n"
                        )
                        for (var i = 0; i < data.length; i++) {
                            var option;
                            if (selectedId == data[i].id) {
                                option = "<option value='" + data[i].id + "' selected>" + data[i].name + "</option>";
                            } else {
                                option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }

                            //设置下拉选项



                            $("#supplierId").append(option);
                        }
                    }else {
                        $("#supplierId").empty();
                        $("#supplierId").append(
                            "<option value=\"\">请选择</option>\n"
                        )
                    }

                }
                //重新渲染下拉框
                layui.form.render("select");
            }
        })


    })



    
});



