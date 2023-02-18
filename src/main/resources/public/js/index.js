layui.use(['form','jquery','jquery_cookie','carousel'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    // var carousel = layui.carousel;
    // //建造实例
    // carousel.render({
    //     elem: '#test1'
    //     ,width: '100%' //设置容器宽度
    //     ,height: "100%"  //按比例和浏览器可视页面宽度来获取高度
    //     ,arrow: 'always' //始终显示箭头
    //     //,anim: 'updown' //切换动画方式
    // });

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