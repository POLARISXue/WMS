layui.use(['form','jquery','jquery_cookie','carousel'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);



    form.on('submit(login)', function(data){

        var fieldData = data.field;

        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data:{
                userName:fieldData.username,
                password:fieldData.password
            },
            dataType:"json",
            success:function (result){
                if (result.code == 200){
                    layer.msg("登陆成功",function (){
                        // 如果⽤户选择"记住我"，则设置cookie的有效期为7天
                        if($("input[type='checkbox']").is(":checked")){
                            $.cookie("userIdStr", result.result.userIdStr, { expires: 7 });
                            $.cookie("userName", result.result.userName, { expires: 7 });
                        }else {
                            $.cookie("userIdStr",result.result.userIdStr);
                            $.cookie("userName",result.result.userName);
                        }
                        window.location.href=ctx+"/main";
                    })
                }else {
                    layer.msg(result.msg);
                }


            }
        })

        // 阻⽌表单跳转
        return false;

    });
    
    
});


jQuery(document).ready(function () {

    /* ------- Preloader ------ */
    $(window).load(function () {
        $('.preloader').delay(250).slideUp('slow'); // set duration in brackets
    });

});

