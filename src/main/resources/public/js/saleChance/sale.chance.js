layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#saleChanceList'
        ,id:'saleChanceListTable'
        //容器高多  full-差值
        ,height: "full-125"
        ,cellMinWidth: 95
        //访问数据的url，对应后端的数据接口
        ,url: ctx+'/sale_chance/list' //数据接口
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
            ,{field: 'chanceSource', title: '机会来源', align:'center'}
            ,{field: 'customerName', title: '客户姓名', align:'center'}
            ,{field: 'cgjl', title: '成功几率', align:'center'}
            ,{field: 'overview', title: '概要', align:'center'}
            ,{field: 'linkMan', title: '联系人',align:'center'}
            ,{field: 'linkPhone', title: '联系人电话', align:'center'}
            ,{field: 'description', title: '描述', align:'center'}
            ,{field: 'createMan', title: '创始人', align:'center'}
            ,{field: 'uName', title: '分配人', align:'center'}
            ,{field: 'assignTime', title: '分配时间', align:'center'}
            ,{field: 'isValid', title: '签名', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{field: 'state', title: '分配状态', align:'center' , templet: function (d) { return formatterState(d.state);}}
            ,{field: 'devResult', title: '开发状态', align:'center',templet: function (d) { return formatterDevResult(d.devResult);}}
            ,{title: '操作',templet:'#saleChanceListBar',fixed: 'right',minWidth:150}
        ]]
    });

    function formatterState(state){
        if(state==0) {
            return "<div style='color: #FF9933'>未分配</div>";
        } else if(state==1) {
            return "<div style='color: green'>已分配</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }

    function formatterDevResult(value){
        if(value == 0) {
            return "<div style='color: #FF9933'>未开发</div>";
        } else if(value==1) {
            return "<div style='color: #00FF00;'>开发中</div>";
        } else if(value==2) {
            return "<div style='color: #00B83F'>开发成功</div>";
        } else if(value==3) {
            return "<div style='color: red'>开发失败</div>";
        } else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }

    $(".search_btn").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                customerName: $("input[name='customerName']").val(), // 客户名
                createMan: $("input[name='createMan']").val(), // 创建⼈
                state: $("#state").val() // 状态
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    table.on('toolbar(saleChances)', function(obj){
        switch(obj.event){
            case 'add':
                openAddOrUpdateSaleChanceDialog();
                break;
            case 'del':
                deleteSaleChances(table.checkStatus(obj.config.id));
                break;

        };
    });





    table.on('tool(saleChances)',function (data){
        if (data.event == 'edit'){
            //编辑操作
            openAddOrUpdateSaleChanceDialog(data.data.id);
        }else if (data.event == 'del'){
            //删除操作
            deleteSaleChance(data.data);
        }
    })

    /**
     * 表格头工具栏删除
     */
    function deleteSaleChances(checkStatus){
        //获取被选中的对应数据
        var saleChanceData = checkStatus.data;
        //判断用户是否选择了记录
        if (saleChanceData.length < 1){
            layer.msg("请选择要删除的记录!",{icon:5});
            return;
        }

        layer.confirm("是否确认删除选择的记录",{icon:3,title:"营销机会管理"},function (index){
            //关闭确认框
            layer.close(index);
            var ids= "ids=";
            for (var i = 0;i<saleChanceData.length;i++){
                if (i<saleChanceData.length-1){
                    ids=ids+saleChanceData[i].id+"&ids=";
                }else {
                    ids=ids+saleChanceData[i].id;
                }
            }
            $.ajax({
                type:"post",
                url: ctx+"/sale_chance/delete",
                data:ids,
                dataType:"json",
                success:function (result){
                    if (result.code==200){
                        layer.msg("删除成功",{icon:6})
                        tableIns.reload({page:{curr:1}});
                    }else {
                        layer.msg(result.msg,{icon:5});
                    }
                }
            });
        });
    }

    /**
     * 表格行工具栏删除单个记录
     * @param data
     */
    function deleteSaleChance(data){
        //弹出确认框，询问用户是否删除
        layer.confirm('确定要删除该记录吗？',{icon:3,title:"营销机会管理"},function (index){
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx+"/sale_chance/delete",
                data:{
                    ids:data.id
                },
                dataType:"json",
                success:function (result) {
                    if (result.code == 200){
                        tableIns.reload({page:{curr:1}});
                    }else {
                        layer.msg(result.msg,{icon: 5});
                    }
                }
            })
        });
    }

    /**
     * 打开添加或更新界面
     * @param saleChanceId
     */
    function openAddOrUpdateSaleChanceDialog(saleChanceId){

        if (saleChanceId == '' || saleChanceId == null){
            layui.layer.open({
                title:"<h2>营销机会管理-添加</h2>",
                type: 2,
                content: ctx+"/sale_chance/addOrUpdateSaleChancePage",
                area:["500px","620px"],
                maxmin:true
            })
        }else {
            layui.layer.open({
                title:"<h2>营销机会管理-编辑</h2>",
                type: 2,
                content: ctx+"/sale_chance/addOrUpdateSaleChancePage?id="+saleChanceId,
                area:["500px","620px"],
                maxmin:true
            })
        }

    }


});
