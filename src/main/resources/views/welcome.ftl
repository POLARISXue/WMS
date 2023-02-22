<!DOCTYPE html>
<html>
<head>
  <#include "common.ftl">
</head>


<body class="childrenBody">

<div class="layui-tab-item layui-show">
    <div class="layui-carousel" id="test10" align="center">
        <div carousel-item="">
            <div><img src="${ctx}/images/slide-4.png" style="width:100%"></div>
            <div><img src="${ctx}/images/slide-5.png" style="width:100%"></div>
            <div><img src="${ctx}/images/slide-6.png" style="width:100%"></div>
        </div>
    </div>
</div>

<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;


        //改变下时间间隔、动画类型、高度
        carousel.render({
            elem: '#test10'
            ,interval: 3000
            ,width: '100%'
            ,height: '750px'
            ,anim: 'default'
            ,arrow:'hover'

        });


    });
</script>
</body>
</html>
