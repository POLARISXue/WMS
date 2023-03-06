layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 加载数据表格
     */
    var  tableIns = table.render({
        //容器元素的ID属性值
        elem: '#supplierList',
        //访问数据的URL（后台的数据接口）
        url : ctx + '/supplier/list',//数据接口
        //单元格最小的宽度
        cellMinWidth : 95,
        //开启分页
        page : true,
        //容器的高度full-差值
        height : "full-125",
        //默认每页显示的数量
        limit : 10,
        //每页页数的可选项
        limits : [10,20,30,40,50],
        //开启头部工具栏
        toolbar: "#toolbarDemo",
        id : "supplierTable",
        //表头
        cols : [[
            //field:要求field属性值与返回的数据中对应的属性字段名一致
            //title:设置列的标题
            //sort:是否允许排序（默认：false）
            //fixed:固定列
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'name', title: '供应商名称',  align:'center'},
            {field: 'contact', title: '联系人', align:'center'},
            {field: 'linkPhone', title: '联系电话', align:'center'},
            {field: 'address', title: '联系地址', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '修改时间', align:'center'},
            {title: '操作', templet:'#supplierListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    /**
     * 搜索按钮的点击事件
     */
    $(".search_btn").click(function(){
        tableIns.reload({
            //设置需要传递给后端的参数
            where: {//设定异步数据接口的额外参数，任意设
                //通过文本框/下拉框的值，设置传递的参数
                name: $("input[name='name']").val(),  //供应商名称
                contact: $("input[name='contact']").val(),  //供应商名称
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    /**
     * 监听头部工具栏事件
     *  格式：
     *      table.on('toolbar(数据表格的lay_filter属性值)', function(data){
     *
     *     });
     */
    table.on('toolbar(supplier)', function(data){
        //data.event:对应的元素上设置的lay_event属性值
        if (data.event == "add"){
            //添加操作
            openSupplierDialog();
        }else if (data.event == "del"){
            //删除操作
            deleteSupplier(data);
        }
    });


    /**
     * 行工具栏监听事件
     *  格式：
     *      table.on('tool(数据表格的lay_filter属性值)', function(data){
     *
     *     });
     */
    table.on("tool(supplier)", function(data){
        //判断类型
        if(data.event =="edit") {//编辑操作
            //得到供应商的ID
            var supplierId = data.data.id;
            //打开修改供应商数据的窗口
            openSupplierDialog(supplierId)
        }else if(data.event == "del") {//删除操作
            //弹出确认框，询问用户是否确认删除
            layer.confirm('确定要删除该记录吗？',{icon:3,title:"供应商管理"},function (index) {
                //关闭确认框
                layer.close(index);
                //发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url:ctx + "/supplier/delete",
                    data:{
                        ids:data.data.id
                    },
                    success:function (result) {
                        //判断删除结果
                        if (result.code == 200){
                            //提示成功
                            layer.msg("删除成功！",{icon:6});
                            //删除表格
                            tableIns.reload({page:{curr:1}});
                        }else {
                            //提示失败
                            layer.msg(result.msg,{icon:5});
                        }

                    }
                });

            });
        }
    });


    /**
     * 打开添加/修改供应商数据窗口
     *      如果供应商ID为空，则为添加操作
     *      如果供应商会ID不为空，则为修改操作
     */
    function openSupplierDialog(supplierId){
        //弹出层的标题
        var title = "<h3>供应商管理-添加供应商</h3>";
        var url = ctx + "/supplier/toSupplierPage";
        //判断供应商ID是否为空
        if (supplierId){
            //更新操作
            title = "<h3>供应商管理-更改供应商</h3>";
            //请求地址传递供应商的ID
            url += '?supplierId=' + supplierId;
        }
        //iframe层
        layui.layer.open({
            //类型
            type: 2,
            //标题
            title: title,
            //宽高
            area: ["500px","620px"],
            //url地址
            content: url,
            //可以最大化与最小化
            maxmin:true

        });
    }

    /**
     * 删除供应商（删除多条记录）
     * @param data
     */
    function deleteSupplier(data) {
        //获取数据表格选中的行数据
        var checkStatus = table.checkStatus("supplierTable");

        console.log(checkStatus);

        //获取所有被选中的记录对应的数据
        var supplierData = checkStatus.data;

        //判断用户是否选择的记录（选中行的数量大于0）
        if (supplierData.length < 1){
            layer.msg("请选择要删除的记录！",{icon:5});
            return;
        }
        //询问用户是否确认删除
        layer.confirm('你确定要删除选中的记录吗？',{icon:3,title:'供应商管理'},function (index){
            //关闭确认框
            layer.close(index);
            //传递的参数是数组  ids=1&ids=2&ids=3
            var ids = "ids=";
            //循环选中的行记录的数据
            for (var i = 0;i < supplierData.length;i++){
                if (i < supplierData.length-1){
                    ids = ids + supplierData[i].id + "&ids="
                }else {
                    ids = ids + supplierData[i].id;
                }
            }

            //发送ajax请求，执行删除供应商
            $.ajax({
                type:"post",
                url:ctx+"/supplier/delete",
                data:ids,//传递的参数是数组  ids=1&ids=2&ids=3
                success:function (result) {
                    //判断删除结果
                    if (result.code == 200){
                        //提示成功
                        layer.msg("删除成功！",{icon:6});
                        //删除表格
                        tableIns.reload({page:{curr:1}});
                    }else {
                        //提示失败
                        layer.msg(result.msg,{icon:5});
                    }
                }
            });
        });

    }



});
