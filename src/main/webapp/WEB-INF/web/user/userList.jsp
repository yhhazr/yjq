<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="static/css/demo_table_jui.css" rel="stylesheet">
<script type="text/javascript">
	
<%
	Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
	if(isAdmin.booleanValue()){
		out.print("var isAdmin = true");
	}else{
		out.print("var isAdmin = false");
	}
%>
	oTable = $('#meterDataExp').dataTable({
		"bProcessing" : true,
		"bJQueryUI" : true,
		"bSort" : false,
		"bFilter" : false,
		"sPaginationType" : "full_numbers",
		"sAjaxSource" : "listUser.action",
		"bServerSide" : true,
		"sDom" : '<"H"lr>t<"F"ip>',
		"fnServerData" : retrieveData,
		"fnRowCallback" : rowCallback,
		"aoColumns" : [ {
			"mDataProp" : "id",
			"bVisible" : false
		}, {
			"mDataProp" : "name"
		}, {
			"mDataProp" : "phone"
		}, {
			"mDataProp" : "email"
		}, {
			"mDataProp" : "remark"
		}, {
			"mData" : null,
			"sClass" : "center",
			"bVisible" : isAdmin
		} ],
		"oLanguage" : {
			"oPaginate" : {
				"sFirst" : "首页",
				"sLast" : "末页",
				"sNext" : "下页",
				"sPrevious" : "上页",
			},
			"sInfo" : "显示_START_到_END_条，共_TOTAL_条",
			"sEmptyTable" : "没有数据",
			"sInfoEmpty" : "没有数据",
			"sLengthMenu" : "每页显示 _MENU_ 条",
			"sLoadingRecords" : "正在加载...",
			"sProcessing" : "正在加载...",
			"sSearch" : "搜索"
		}
	});
	$('.dataTables_length').css("float", "right");
	$('.dataTables_length').css("width", "15%");
	if(isAdmin){
		$('.dataTables_length').parent().append("<div style='float:left;'><button class='btn btn-primary' id='newUser'><i class='icon-user icon-white'></i>新增用户</button></div>");
	}
	function rowCallback(nRow, aData, iDisplayIndex) {
		$('td:eq(4)', nRow).html("<button value="+aData.id+" class='btn btn-info'><i class='icon-edit icon-white'></i>编辑</i></button><button value="+aData.id+" class='btn btn-danger'><i class='icon-trash icon-white'></i>删除</i></button>");
	}
	function retrieveData(sSource, aoData, fnCallback) {
		var pageSize = fnGetKey(aoData, "iDisplayLength");
		var startRow = fnGetKey(aoData, "iDisplayStart");
		var echo = fnGetKey(aoData, "sEcho");
		$.ajax({
			"type" : "POST",
			"url" : sSource,
			"dataType" : "json",
			"data" : {
				pageSize : pageSize,
				startRow : startRow,
				echo : echo
			},
			"success" : function(resp) {
				fnCallback($.parseJSON(resp.result));
			}
		});
	}
	function fnGetKey(aoData, sKey) {
		for ( var i = 0, iLen = aoData.length; i < iLen; i++) {
			if (aoData[i].name == sKey) {
				return aoData[i].value;
			}
		}
		return null;
	}

	$("#newUser").click(function() {
		$.get("addUser.action", function(data) {
			$("#myModal").html(data);
			$('#myModal').modal({
				backdrop : false
			});
		});
	});
	$(".btn-danger").die().live("click", function() {
		var id = $(this).val();
		function callback() {
			$.get("deleteUser.action?userId=" + id, function(data) {
				if (data.result == "true") {
					oTable.fnPageChange('first');
				} else {
					alert("失败！");
				}
			});
		}
		bootstrapConfirm("确定删除吗？", callback);
	})
	$(".btn-info").die().live("click", function() {
		var id = $(this).val();
		$.get("editUser.action?userId=" + id, function(data) {
			$("#myModal").html(data);
			$('#myModal').modal({
				backdrop : false
			});
		});
	})
</script>
</head>
<body>
	<table id="meterDataExp" width="100%" class="display">
		<thead>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>电话</th>
				<th>邮箱</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>