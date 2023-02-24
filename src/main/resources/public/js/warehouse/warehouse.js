layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 营销机会列表展示
     */
    var tableIns = table.render({
        elem: '#warehouseList', // 表格绑定的ID
        url: ctx + '/warehouse/list', // 访问数据的地址
        cellMinWidth: 95,
        page: true, // 开启分⻚
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "warehouseListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '编号', fixed: "true"},
            {field: 'goodsId', title: '货物编号', align: "center"},
            {field: 'goodsNumber', title: '货物数量', align: 'center'},
            {field: 'createDate', title: '创建时间', align: 'center'},
            {field: 'updateDate', title: '更新时间', align: 'center'},
            {field: 'goodsName', title: '货物名称', align: 'center'},
            {field: 'goodsCostPrice', title: '货物进价', align: 'center'},
            {field: 'goodsSalePrice', title: '货物售价', align: 'center'},
            {field: 'typeName', title: '类型名称', align: 'center'},

            {
                title: '操作', templet: '#warehouseListBar', fixed: "right", align: "center",
                minWidth: 150
            }
        ]]
    });
    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        table.reload('warehouseListTable', {
            where: { //设定异步数据接⼝的额外参数，任意设
                goodsId: $("input[name='goodsId']").val(), // 货物编号
                goodsName: $("input[name='goodsName']").val(), // 货物数量
            }
            ,page: {
                curr: 1 // 重新从第 1 ⻚开始
            }
        }); // 只重载数据
    });
    /**
     * 监听头部工具栏事件
     *  格式：
     *      table.on('toolbar(数据表格的lay-filter属性值)', function (data) {
     *
            })
     */
    table.on('toolbar(warehouse)', function (data) {
        // data.event：对应的元素上设置的lay-event属性值
        console.log(data);
        // 判断对应的事件类型
        if (data.event == "add") {
            // 添加操作
            openWarehouseDialog();

        } else if (data.event == "del") {
            // 删除操作
            deleteWarehouse(table.checkStatus(data.config.id));
        }
    })


    /**
     * 打开添加/修改营销机会数据的窗口
     *      如果营销机会ID为空，则为添加操作
     *      如果营销机会ID不为空，则为修改操作
     */
    function openWarehouseDialog() {
        // 弹出层的标题
        var title = "<h3>库存管理 - 添加库存</h3>";
        var url = ctx + "/warehouse/toWarehousePage";

        /*// 判断营销机会ID是否为空
        if (warehouseId != null && warehouseId != 'warehouseId') {
            // 更新操作
            title  = "<h3>库存管理 - 更新库存</h3>";
            // 请求地址传递营销机会的ID
            url += '?warehouseId=' + warehouseId;
        }*/

        // iframe层
        layui.layer.open({
            // 类型
            type: 2,
            // 标题
            title: title,
            // 宽高
            area: ['750px', '240px'],
            // url地址
            content: url,
            // 可以最大化与最小化
            maxmin:true
        });
    }


    function openWarehouseDialog1(warehouseId) {
        // 弹出层的标题
        var title = "<h3>库存管理 - 修改库存</h3>";
        var url = ctx + "/warehouse/toWarehousePage1?warehouseId="+warehouseId;

        /*// 判断营销机会ID是否为空
        if (warehouseId != null && warehouseId != 'warehouseId') {
            // 更新操作
            title  = "<h3>库存管理 - 更新库存</h3>";
            // 请求地址传递营销机会的ID

        }*/

        // iframe层
        layui.layer.open({
            // 类型
            type: 2,
            // 标题
            title: title,
            // 宽高
            area: ['750px', '240px'],
            // url地址
            content: url,
            // 可以最大化与最小化
            maxmin:true
        });
    }



    /**
     * 删除营销机会（删除多条记录）
     * @param data
     */
    function deleteWarehouse(checkStatus) {
        //获取被选中的对应数据
        var warehouseData = checkStatus.data;

        // 判断用户是否选择的记录 (选中行的数量大于0)
        if (warehouseData.length < 1) {
            layer.msg("请选择要删除的记录！",{icon:5});
            return;
        }

        // 询问用户是否确认删除
        layer.confirm('您确定要删除选中的记录吗？',{icon:3, title:'库存管理'}, function (index) {
            // 关闭确认框
            layer.close(index);
            // 传递的参数是数组   ids=1&ids=2&ids=3
            var ids = "ids=";
            // 循环选中的行记录的数据
            for(var i = 0; i < warehouseData.length; i++) {
                if(i < warehouseData.length -1) {
                    ids = ids + warehouseData[i].id + "&ids="
                } else {
                    ids = ids + warehouseData[i].id;
                }
            }
            // console.log(ids);

            // 发送ajax请求，执行删除营销机会
            $.ajax({
                type:"post",
                url:ctx + "/warehouse/delete",
                data:ids, // 传递的参数是数组 ids=1&ids=2&ids=3
                success:function (result) {
                    // 判断删除结果
                    if (result.code == 200) {
                        // 提示成功
                        layer.msg("删除成功！",{icon:6});
                        // 刷新表格
                        tableIns.reload();
                    } else {
                        // 提示失败
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });

    }

    /**
     * 行工具栏监听事件
     table.on('tool(数据表格的lay-filter属性值)', function (data) {

         });
     */
    table.on('tool(warehouse)', function (data) {
        // 判断类型
        if (data.event == "edit") { // 编辑操作

            // 得到营销机会的ID
            var warehouseId = data.data.id;
            // 打开修改营销机会数据的窗口
            openWarehouseDialog1(warehouseId)

        } else if (data.event == "del") { // 删除操作
            // 弹出确认框，询问用户是否确认删除
            layer.confirm('确定要删除该记录吗？',{icon:3, title:"库存管理"}, function (index) {
                // 关闭确认框
                layer.close(index);

                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url:ctx + "/warehouse/delete",
                    data:{
                        ids:data.data.id
                    },
                    success:function (result) {
                        // 判断删除结果
                        if (result.code == 200) {
                            // 提示成功
                            layer.msg("删除成功！",{icon:6});
                            // 刷新表格
                            tableIns.reload();
                        } else {
                            // 提示失败
                            layer.msg(result.msg, {icon:5});
                        }
                    }
                });
            });
        }
    });
});