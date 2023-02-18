layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#saleChanceList'
        ,id:'saleChanceListTable1'
        //容器高多  full-差值
        ,height: "full-125"
        ,cellMinWidth: 95
        //访问数据的url，对应后端的数据接口
        ,url: ctx+'/sale_chance/list?flag=1' //数据接口
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
            ,{field: 'isValid', title: '签名', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{field: 'devResult', title: '开发状态', align:'center',templet: function (d) { return formatterDevResult(d.devResult);}}
            ,{title: '操作',templet:'#op',fixed: 'right',minWidth:150}
        ]]
    });

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
                devResult:$("#devResult").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    table.on('tool(saleChances)',function (data){
        if (data.event == 'dev'){
            //编辑操作
            openCusDevPlanDialog("计划项-数据开发",data.data.id);
        }else if (data.event == 'info'){
            //删除操作
            openCusDevPlanDialog("计划项-数据维护",data.data.id);
     ;
        }
    })

    /**
     * 打开计划项开发页面
     * @param title
     * @param id
     */
    function openCusDevPlanDialog(title,id){
        layui.layer.open({
            title:"<h2>"+title+"</h2>",
            type: 2,
            content: ctx+"/cus_dev_plan/toCusDevPlanPage?id="+id,
            area:["1000px","750px"],
            maxmin:true
        })
    }




});
