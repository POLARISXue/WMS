<!DOCTYPE html>
<html>
<head>
	<title>营销机会管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="goodsName"
						   class="layui-input
					searchVal" placeholder="商品名" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="supplier" class="layui-input
					searchVal" placeholder="供应商" />
				</div>
				<div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="" >分配状态</option>
                        <option value="0">未入库</option>
                        <option value="1" >已入库</option>
                    </select>
				</div>
				<a class="layui-btn search_btn layui-btn layui-btn-normal" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>
	<table id="intoWarehouseList" class="layui-table"  lay-filter="intoWarehouse"></table>


	<!-- 头部工具栏 -->
	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-radius layui-btn-normal delNews_btn" lay-event="getCheckData">
				<i class="layui-icon">&#xe63c;</i>
				获取选中行数据
			</a>
			<a class="layui-btn layui-btn-radius layui-btn-normal delNews_btn" lay-event="getCheckLength">
				<i class="layui-icon">&#xe615;</i>
				获取选中数目
			</a>
			<a class="layui-btn layui-btn-radius layui-btn-normal delNews_btn" lay-event="isAll">
				<i class="layui-icon">&#xe615;</i>
				验证是否全选
			</a>
			<a class="layui-btn layui-btn-radius layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加
			</a>
			<a class="layui-btn layui-btn-radius layui-btn-danger delNews_btn" lay-event="del">
				<i class="layui-icon">&#xe67e;</i>
				删除
			</a>
		</div>
	</script>


	<!--行工具栏-->
	<!--操作-->
	<script id="intoWarehouseListBar" type="text/html">
		<a class="layui-btn layui-btn-xs layui-btn layui-btn-normal" id="edit" lay-event="edit">
			<i class="layui-icon">&#xe642;</i>
			编辑
		</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
			<i class="layui-icon">&#xe67e;</i>
			删除
		</a>
	</script>

</form>
<script type="text/javascript" src="${ctx}/js/intowarehouse/intowarehouse.js"></script>

</body>
</html>