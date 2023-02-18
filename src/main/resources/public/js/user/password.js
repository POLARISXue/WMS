layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(saveBtn)', function(data){

        $.ajax({
            type:"post",
            url:ctx+"/user/updatePassword",
            data:{
              oldPassword:data.field.old_password,
              newPassword:data.field.new_password,
              confirmPassword:data.field.again_password
            },
            dataType:"json",
            success:function (result){
                if (result.code == 200){
                    layer.msg("修改成功！2秒后请重新登录",function (){
                        //清空cookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/wms"});
                        $.removeCookie("userName",{domain:"localhost",path:"/wms"});
                        window.parent.location.href=ctx+"/index";
                    });
                }else {
                    layer.msg(result.msg,{icon:5});
                }
            }
        })
    })





})