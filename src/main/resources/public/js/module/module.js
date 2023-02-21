layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    // 渲染表格
    var tableIns = treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx+'/module/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,

        cols: [[
            {type: 'numbers'},
            {field: 'moduleName', minWidth: 90, title: '菜单名称'},
            {field: 'optValue', title: '权限码'},
            {field: 'url', title: '菜单url'},
            {field: 'createDate', title: '创建时间'},
            {field: 'updateDate', title: '更新时间'},
            {
                field: 'grade', width: 80, align: 'center', templet: function (d) {
                    if (d.grade == 0) {
                        return '<span class="layui-badge layui-bg-green">目录</span>';
                    }
                    if(d.grade==1){
                        return '<span class="layui-badge layui-bg-orange">菜单</span>';
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-blue">按钮</span>';
                    }
                }, title: '类型'
            },
            {templet: '#auth-state', width: 280, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });


    table.on("toolbar(munu-table)",function (data){
        if (data.event == "expand"){
            treeTable.expandAll("#munu-table");
        }else if(data.event == "fold"){
            treeTable.foldAll("#munu-table");
        }else if (data.event == "add"){
            //添加一级菜单
            openAddModulePage(0,-1);
        }
    });


    table.on("tool(munu-table)",function (data){
        if (data.event == "add"){

            if (data.data.grade == 2){
                layer.msg("暂不支持添加四级菜单",{icon:5});
                return;
            }else{
                openAddModulePage(data.data.grade+1,data.data.id);
                return;
            }

        }else if (data.event == "edit"){
            openUpdateModulePage(data.data.id);
            return;
        }else if (data.event=="del"){
            deleteModule(data.data.id);
        }
    })


    function openAddModulePage(grade,parentId){

        var title = "<h3>资源管理 - 资源添加</h3>"
        var url = ctx+"/module/openAddModulePage?grade="+grade+"&parentId="+parentId;

        layui.layer.open({
            title:title,
            type: 2,
            content: url,
            area:["700px","600px"],
            maxmin:true
        })
    }


    function openUpdateModulePage(id){
        var title = "<h3>资源管理 - 资源更新</h3>"
        var url = ctx+"/module/openUpdateModulePage?id="+id;

        layui.layer.open({
            title:title,
            type: 2,
            content: url,
            area:["700px","600px"],
            maxmin:true
        })
    }

    function deleteModule(id){
        layer.confirm("您确认要删除该记录吗",{icon:3,title:"资源管理 - 删除"},function (data){
            $.post(ctx+"/module/delete",{id:id},function (result){
                if (result.code==200){
                    layer.msg("删除成功",{icon:6});
                    window.location.reload();
                }else {
                    layer.msg(result.msg,{icon: 5});
                }
            })
        })
    }

    
});