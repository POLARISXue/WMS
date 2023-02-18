layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

       var tableIns = table.render({
              elem: '#roleList'
              ,id:'roleListTable'
              //容器高多  full-差值
              ,height: "full-125"
              ,cellMinWidth: 95
              //访问数据的url，对应后端的数据接口
              ,url: ctx+'/role/list' //数据接口
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
                     ,{field: 'roleName', title: '角色名称', align:'center'}
                     ,{field: 'roleRemark', title: '角色备注', align:'center'}
                     ,{field: 'createDate', title: '创建时间', align:'center'}
                     ,{field: 'updateDate', title: '更新时间', align:'center'}
                     ,{title: '操作',templet:'#roleListBar',fixed: 'right',minWidth:150}
              ]]
       });



       $(".search_btn").click(function (){
              tableIns.reload({
                     where: { //设定异步数据接口的额外参数，任意设
                            roleName: $("input[name='roleName']").val(), // 客户名
                     }
                     ,page: {
                            curr: 1 //重新从第 1 页开始
                     }
              });
       });



       /**
        * 数据表格头工具栏监听器
        */
       table.on('toolbar(roles)',function (data) {
              switch (data.event){
                     case 'add':{
                            openAddOrUpdateRolePage();
                            break;
                     }
                     case 'grant':{
                            openGrantPage(table.checkStatus(data.config.id).data);
                            break;
                     }
              }
       })

       /**
        * 监听行内工具栏
        */
       table.on('tool(roles)',function (data){
              if (data.event == 'edit'){
                     //编辑操作
                     openAddOrUpdateRolePage(data.data.id);
              }else if (data.event == 'del'){
                     //删除操作
                     deleteRole(data.data)
              }
       })


       /**
        * 打开添加或更新窗口
        * @param id
        */
       function openAddOrUpdateRolePage(id){
              if(id != null && id != ''){
                     layui.layer.open({
                            title:"<h3>角色管理-修改</h3>",
                            type: 2,
                            content: ctx+"/role/openAddOrUpdateRolePage?id="+id,
                            area:["650px","400px"],
                            maxmin:true
                     })
              }else {
                     layui.layer.open({
                            title:"<h3>角色管理-添加</h3>",
                            type: 2,
                            content: ctx+"/role/openAddOrUpdateRolePage",
                            area:["650px","400px"],
                            maxmin:true
                     })
              }

       }


       /**
        * 删除记录
        * @param data
        */
       function deleteRole(data){
              //弹出确认框，询问用户是否删除
              layer.confirm('确定要删除该角色记录吗？',{icon:3,title:"角色管理"},function (index){
                     layer.close(index);
                     console.log(data);
                     $.ajax({
                            type:"post",
                            url: ctx+"/role/delete",
                            data:{
                                   ids:data.id,
                            },
                            dataType:"json",
                            success:function (result) {
                                   if (result.code == 200){
                                          layer.msg("删除成功",{icon:6});
                                          tableIns.reload({page:{curr:1}});
                                   }else {
                                          layer.msg(result.msg,{icon: 5});
                                   }
                            },
                     })
              });
       }

       function openGrantPage(data){
              if (data.length < 1){
                     layer.msg("请选择要删除的记录!",{icon:5});
                     return;
              }else if (data.length>1){
                     layer.msg("暂不支持批量角色授权",{icon:5});
                     return;
              }

              var title = "<h3>角色管理 - 角色授权</h3>"
              var url = ctx+"/module/openGrantPage?roleId="+data[0].id;

              layui.layer.open({
                     title:title,
                     type: 2,
                     content: url,
                     area:["500px","620px"],
                     maxmin:true
              })


       }





});
