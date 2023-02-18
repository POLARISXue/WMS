layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var saleChanceId = $("#saleChanceId").val();

    var tableIns = table.render({
        elem: '#cusDevPlanList'
        ,id:'cusDevPlanListTable'
        //容器高多  full-差值
        ,height: "full-125"
        ,cellMinWidth: 95
        //访问数据的url，对应后端的数据接口
        ,url: ctx+"/cus_dev_plan/list?saleChanceId="+saleChanceId//数据接口
        ,page: true //开启分页
        ,limit: 10
        ,limits: [10,20,30,40,50]
        //头部工具栏
        ,toolbar: '#toolbarDemo'
        ,cols: [[ //表头
            //field:与返回的数据中对应属性的字段名一致
            //title:标题
            //sort:是否开启排序
            //fixed:固定列
            {type:'checkbox',fixed: 'center'}
            ,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'planItem', title: '项目', align:'center'}
            ,{field: 'planDate', title: '计划日期', align:'center'}
            ,{field: 'exeAffect', title: '执行效果', align:'center'}
            ,{field: 'createDate', title: '创建日期', align:'center'}
            ,{field: 'updateDate', title: '更新日期',align:'center'}
            ,{title: '操作',templet:'#cusDevPlanListBar',fixed: 'right',minWidth:150}
        ]]
    });

    /**
     * 头部工具栏监听器
     */
    table.on('toolbar(cusDevPlans)',function (data){
        if (data.event == 'add'){
            openAddOrUpdateCusDevPlans();
        }else if (data.event == 'success'){
            updateSaleChanceDevResult(2);
        }else if (data.event == 'failed'){
            updateSaleChanceDevResult(3);
        }
    })

    /**
     * 行内工具栏监听器
     */
    table.on('tool(cusDevPlans)',function (data){
        if (data.event == 'edit'){
            //编辑操作
            openAddOrUpdateCusDevPlans(data.data.id);
        }else if (data.event == 'del'){
            //删除操作
            deleteCusDevPlan(data.data.id);
        }
    })









    /**
     * 打开添加计划窗口
     */
    function openAddOrUpdateCusDevPlans(id){

        if (id != null && id != ''){
            layui.layer.open({
                title:"<h2>计划项管理-更新</h2>",
                type: 2,
                content: ctx+"/cus_dev_plan/addOrUpdateSaleChancePage?sId="+$("[name=id]").val()+"&id="+id,
                area:["500px","300px"],
                maxmin:true
            })
        }else{
            layui.layer.open({
                title:"<h2>计划项管理-添加</h2>",
                type: 2,
                content: ctx+"/cus_dev_plan/addOrUpdateSaleChancePage?sId="+$("[name=id]").val(),
                area:["500px","300px"],
                maxmin:true
            })
        }

    }

    /**
     * 更新营销机会开发状态
     * @param DevResult
     */
    function updateSaleChanceDevResult(DevResult){
        var title;
        if (DevResult == 2 ){
            title="您确认将开发状态修改为成功吗？";
        }else if (DevResult == 3){
            title="您确认将开发状态修改为失败吗？";
        }

        layer.confirm(title,{icon:3,title:"更新开发状态"},function (index){
            layer.close(index);
            var sId = $("[name=id]").val();
            $.ajax({
                type:"post",
                url:ctx+"/sale_chance/updateSaleChanceDevResult",
                data: {
                    id:sId,
                    DevResult:DevResult
                },
                dataType: "json",
                success:function (result){
                    if (result.code==200){
                        layer.msg("更新成功",{icon:6});
                        layer.closeAll("iframe");
                        parent.location.reload();
                    }else {
                        layer.msg(result.msg,{icon: 5});
                    }
                }
            })
        })


    }

    /**
     * 删除计划
     * 1.弹出确认框
     * 2.执行操作
     */
    function deleteCusDevPlan(id){
        //弹出确认框，询问用户是否删除
        layer.confirm('确定要删除该记录吗？',{icon:3,title:"删除计划项"},function (index){
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx+"/cus_dev_plan/delete",
                data:{
                    id:id
                },
                dataType:"json",
                success:function (result) {
                    if (result.code == 200){
                        layer.msg("删除成功",{icon:6});
                        tableIns.reload({page:{curr:1}});
                    }else {
                        layer.msg(result.msg,{icon: 5});
                    }
                }
            })
        });
    }






});
