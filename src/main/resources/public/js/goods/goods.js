layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 绑定搜索按键，执行条件搜索
     */
    $(".search_btn").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                goodsName: $("input[name='goodsName']").val(), // 产品名
                goodsTypeId: $("input[name='goodsTypeId']").val(), // 商品类型
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    var tableIns = table.render({
        elem: '#goodsList'
        ,id:'goodsTable'
        //容器高多  full-差值
        ,height: "full-125"
        ,cellMinWidth: 95
        //访问数据的url，对应后端的数据接口
        ,url: ctx+"/goods/list"//数据接口
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
            ,{field: 'goodsName', title: '商品名称', align:'center'}
            ,{field: 'goodsType', title: '商品类型', align:'center'}
            ,{field: 'goodsCostPrice', title: '进价', align:'center'}
            ,{field: 'goodsSalePrice', title: "售价",align:'center'}
            ,{field: 'createDate', title: '创建时间',align:'center'}
            ,{field: 'updateDate', title: '更新时间',align:'center'}
            ,{title: '操作',templet:'#goodsListBar',fixed: 'right',minWidth:150}
        ]]
    });


    /**
     * 数据表格头工具栏监听器
     */
    table.on('toolbar(goods)',function (data) {
        switch (data.event){
            case 'add':{
                openAddOrUpdateGoods();
                break;
            }
            case 'del':{
                deleteGoods(table.checkStatus(data.config.id).data);
                break;
            }
        }
    })

    /**
     * 监听行内工具栏
     */
    table.on('tool(goods)',function (data){
        if (data.event == 'edit'){
            //编辑操作
            openAddOrUpdateGoods(data.data.id);
        }else if (data.event == 'del'){
            //删除操作
            deleteGood(data.data)
        }
    })

    /**
     * 打开添加或更新窗口
     * @param id
     */
    function openAddOrUpdateGoods(id){
        if(id != null && id != ''){
            layui.layer.open({
                title:"<h3>商品-修改</h3>",
                type: 2,
                content: ctx+"/goods/openAddOrUpdateGoodsPage?id="+id,
                area:["650px","400px"],
                maxmin:true
            })
        }else {
            layui.layer.open({
                title:"<h3>商品-添加</h3>",
                type: 2,
                content: ctx+"/goods/openAddOrUpdateGoodsPage",
                area:["650px","400px"],
                maxmin:true
            })
        }

    }


    /**
     *
     */
    function deleteGood(data){
        //弹出确认框，询问用户是否删除
        layer.confirm('确定要删除该记录吗？',{icon:3,title:"用户管理"},function (index){
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx+"/goods/delete",
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

    function deleteGoods(goodsData){
        if (goodsData.length < 1){
            layer.msg("请选择要删除的记录!",{icon:5});
            return;
        }

        layer.confirm("是否确认删除选择的记录",{icon:3,title:"用户管理"},function (index){
            //关闭确认框
            layer.close(index);
            var ids= "ids=";
            for (var i = 0;i<goodsData.length;i++){
                if (i<goodsData.length-1){
                    ids=ids+goodsData[i].id+"&ids=";
                }else {
                    ids=ids+goodsData[i].id;
                }
            }
            $.ajax({
                type:"post",
                url: ctx+"/goods/delete",
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
        })
    }


});