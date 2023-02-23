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
                userName: $("input[name='userName']").val(), // 客户名
                email: $("input[name='email']").val(), // 创建⼈
                phone: $("input[name='phone']").val() // 状态
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    var tableIns = table.render({
        // 与table标签的id属性值保持一致
        elem: '#userList'
        ,id:'userTable'
        //容器高多  full-差值
        ,height: "full-125"
        ,cellMinWidth: 95
        //访问数据的url，对应后端的数据接口
        ,url: ctx+"/user/list"//数据接口
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
            ,{field: 'userName', title: '用户姓名', align:'center'}
            ,{field: 'trueName', title: '真实姓名', align:'center'}
            ,{field: 'email', title: '邮件', align:'center'}
            ,{field: 'phone', title: "手机号",align:'center'}
            ,{field: 'createDate', title: '创建时间',align:'center'}
            ,{field: 'updateDate', title: '更新时间',align:'center'}
            ,{title: '操作',templet:'#userListBar',fixed: 'right',minWidth:150}
        ]]
    });


    /**
     * 数据表格头工具栏监听器
     */
    table.on('toolbar(users)',function (data) {
        switch (data.event){
            case 'add':{
                openAddOrUpdateUser();
                break;
            }
            case 'del':{
                deleteUsers(table.checkStatus(data.config.id).data);
                break;
            }
        }
    })

    /**
     * 监听行内工具栏
     */
    table.on('tool(users)',function (data){
        if (data.event == 'edit'){
            //编辑操作
            openAddOrUpdateUser(data.data.id);
        }else if (data.event == 'del'){
            //删除操作
            deleteUser(data.data)
        }
    })

    /**
     * 打开添加或更新窗口
     * @param id
     */
    function openAddOrUpdateUser(id){
        if(id != null && id != ''){
            layui.layer.open({
                title:"<h3>用户管理-修改</h3>",
                type: 2,
                content: ctx+"/user/toAddOrUpdateUserPage?id="+id,
                area:["650px","650px"],
                maxmin:true
            })
        }else {
            layui.layer.open({
                title:"<h3>用户管理-添加</h3>",
                type: 2,
                content: ctx+"/user/toAddOrUpdateUserPage",
                area:["650px","650px"],
                maxmin:true
            })
        }

    }


    /**
     * 删除用户
     */
    function deleteUser(data){
        //弹出确认框，询问用户是否删除
        layer.confirm('确定要删除该记录吗？',{icon:3,title:"用户管理"},function (index){
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx+"/user/delete",
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

    function deleteUsers(userData){
        if (userData.length < 1){
            layer.msg("请选择要删除的记录!",{icon:5});
            return;
        }

        layer.confirm("是否确认删除选择的记录",{icon:3,title:"用户管理"},function (index){
            //关闭确认框
            layer.close(index);
            var ids= "ids=";
            for (var i = 0;i<userData.length;i++){
                if (i<userData.length-1){
                    ids=ids+userData[i].id+"&ids=";
                }else {
                    ids=ids+userData[i].id;
                }
            }
            $.ajax({
                type:"post",
                url: ctx+"/user/delete",
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