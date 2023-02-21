layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#outWarehouseList'
        ,id:'outWarehouseListTable'
        //容器高多  full-差值
        ,height: "full-125"
        ,cellMinWidth: 95
        //访问数据的url，对应后端的数据接口
        ,url: ctx+'/outwarehouse/list' //数据接口
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
            ,{field: 'goodsName', title: '物品名称', align:'center'}
            ,{field: 'goodsNumber', title: '物品数量', align:'center'}
            ,{field: 'saleTotalPrice', title: '物品总销售额', align:'center'}
            ,{field: 'customersName', title: '需求商', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{field: 'remarks', title: '备注', align:'center'}
            ,{field: 'outWarehouseState', title: '分配状态', align:'center' , templet: function (d) { return formatterState(d.state);}}
            ,{title: '操作',templet:'#outWarehouseListBar',fixed: 'right',minWidth:150}
        ]]
    });

    function formatterState(state){
        if(state==0) {
            return "<div style='color: #FF9933'>未出库</div>";
        } else if(state==1) {
            return "<div style='color: green'>已出库</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }


    $(".search_btn").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                goodsName: $("input[name='goodsName']").val(), // 物品名
                customersName: $("input[name='customers']").val(), // 需求商人
                state: $("#state").val() // 状态
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    table.on('toolbar(outWarehouse)', function(obj){
        switch(obj.event){
            case 'add':
                openAddOrUpdateIntoWarehouseDialog();
                break;
            case 'del':
                deleteIntoWarehouses(table.checkStatus(obj.config.id));
                break;

        };
    });





    table.on('tool(outWarehouse)',function (data){
        if (data.event == 'edit'){
            //编辑操作
            openAddOrUpdateIntoWarehouseDialog(data.data.id);
        }else if (data.event == 'del'){
            //删除操作
            deleteIntoWarehouse(data.data);
        }
    })

    /**
     * 表格头工具栏删除
     */
    function deleteIntoWarehouses(checkStatus){
        //获取被选中的对应数据
        var IntoWarehouseData = checkStatus.data;
        //判断用户是否选择了记录
        if (IntoWarehouseData.length < 1){
            layer.msg("请选择要删除的记录!",{icon:5});
            return;
        }

        layer.confirm("是否确认删除选择的记录",{icon:3,title:"出库记录管理"},function (index){
            //关闭确认框
            layer.close(index);
            var ids= "ids=";
            for (var i = 0;i<IntoWarehouseData.length;i++){
                if (i<IntoWarehouseData.length-1){
                    ids=ids+IntoWarehouseData[i].id+"&ids=";
                }else {
                    ids=ids+IntoWarehouseData[i].id;
                }
            }
            $.ajax({
                type:"post",
                url: ctx+"/outwarehouse/delete",
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
    function deleteIntoWarehouse(data){
        //弹出确认框，询问用户是否删除
        layer.confirm('确定要删除该记录吗？',{icon:3,title:"入库记录管理"},function (index){
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx+"/outwarehouse/delete",
                data:{
                    ids:data.id
                },
                dataType:"json",
                success:function (result) {
                    if (result.code == 200){
                        layer.msg("删除成功",{icon:6})
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
    function openAddOrUpdateIntoWarehouseDialog(Id){

        if (Id == '' || Id == null){
            layui.layer.open({
                title:"<h2>销售计划管理-添加</h2>",
                type: 2,
                content: ctx+"/outwarehouse/addOrUpdateIntoWarehousePage",
                area:["500px","400px"],
                maxmin:true
            })
        }else {
            layui.layer.open({
                title:"<h2>销售计划管理-编辑</h2>",
                type: 2,
                content: ctx+"/outwarehouse/addOrUpdateIntoWarehousePage?id="+Id,
                area:["500px","620px"],
                maxmin:true
            })
        }

    }


});
