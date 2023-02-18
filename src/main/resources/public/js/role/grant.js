$(function (){
    loadModuleData();
});

var zTreeObj;

console.log("123");


function loadModuleData(){
    var setting={
        check:{
            enable:true
        },
        data:{
            simpleData:{
                enable: true
            }
        },
        callback:{
            onCheck:zTreeOnCheck
        }
    }
    var roleId = $("[name='roleId']").val();

    $.ajax({
        type:"get",
        url:ctx+"/module/treelist",
        data:{
          roleId:roleId
        },
        dataType:"json",
        success:function (data){
            zTreeObj = $.fn.zTree.init($("#test01"),setting,data);
        }
    })
}

function zTreeOnCheck(event,treeId,treeNode){
    var nodes =zTreeObj.getCheckedNodes(true);

    if (nodes.length>0){
        var mIds = "mIds=";
        for (var i = 0;i<nodes.length;i++){
            if (i<nodes.length-1){
                mIds += nodes[i].id+"&mIds=";
            }else {
                mIds += nodes[i].id;
            }
        }
    }

    var roleId = $("[name='roleId']").val();


    $.ajax({
        type: 'post',
        dataType: 'json',
        data:mIds+"&roleId="+roleId,
        url:ctx+"/role/addGrant",
        success:function (result){
            console.log(result);
        }
    })
}