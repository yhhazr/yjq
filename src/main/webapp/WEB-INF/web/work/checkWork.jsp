<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="static/css/demo_table_jui.css" rel="stylesheet">
<script type="text/javascript">
	
workTable = $('#workTable').dataTable({
		"bProcessing" : true,
		"bJQueryUI" : true,
		"bSort" : false,
		"bFilter": false,
		"sPaginationType" : "full_numbers",
		"sAjaxSource" : "listAllWork.action",
		"bServerSide" : true,
		"sDom":'<"H"lr>t<"F"ip>',
		"fnServerData" : retrieveData,
		"fnRowCallback": rowCallback,
		"aoColumns" : [{
			"mDataProp" : "id",
		},{
			"mDataProp" : "workName",
		},{
			"mDataProp" : "userName"
		}, {
			"mDataProp" : "progress"
		},{
			"mData" : null,
			"sClass": "center" 
		},{
			"mData" : null,
			"sClass": "center" 
		}],
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
	$('.dataTables_length').css("float","right");
	$('.dataTables_length').css("width","15%");
	function rowCallback(nRow, aData, iDisplayIndex){
		$('td:eq(4)', nRow).html("<button name='view' value="+aData.id+" class='btn btn-primary'><i class='icon-tasks icon-white'></i>工作详情</i></button>");
		$('td:eq(5)', nRow).html( "<button value="+aData.id+" class='btn btn-info'><i class='icon-edit icon-white'></i>编辑</i></button><button value="+aData.id+" class='btn btn-danger'><i class='icon-trash icon-white'></i>删除</i></button>");
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
	
	$("#newWork").click(function(){
		$.get("addWork.action",function(data){
			$("#myModal").html(data);
			$('#myModal').modal({
				backdrop:false
			});
		});
	});
	$(".btn-danger").die().live("click",function(){
		var id = $(this).val();
		function callback(){
			$.get("deleteWork.action?workId=" + id,function(data){
				if(data.result == "true"){
					workTable.fnPageChange( 'first' );
				}else{
					alert("失败！");
				}
			});
		}
		bootstrapConfirm("确定删除吗？",callback);
	});
	$(".btn-info").die().live("click",function(){
		var id = $(this).val();
		$.get("editWork.action?workId=" + id,function(data){
			$("#myModal").html(data);
			$('#myModal').modal({
				backdrop:false
			});
		});
	})
	$("button[name='view']").die().live("click",function(data){
		var id = $(this).val();
		changeContent("viewWork.action?workId="+ id);
	})
</script>
</head>
<body>
	<table id="workTable" width="100%" class="display">
		<thead>
			<tr>
				<th width="15%">工作编号</th>
				<th width="17%">工作名称</th>
				<th width="17%">员工姓名</th>
				<th width="17%">工作进度</th>
				<th width="17%">工作详情</th>
				<th width="17%">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>