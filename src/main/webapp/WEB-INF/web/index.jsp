<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


<link href="static/css/bootstrap.css" rel="stylesheet">
<link href="static/css/demo_table_jui.css" rel="stylesheet">
<link href="static/css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
<link href="static/css/loading.css" rel="stylesheet">
<link href="static/css/cb_style.css" rel="stylesheet">
<link href="static/css/uploadify.css" rel="stylesheet">
<script type="text/javascript" src="static/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="static/js/bootstrap.js"></script>
<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="static/js/validate.js"></script>
<script type="text/javascript" src="static/js/loading.js"></script>
<script type="text/javascript" src="static/js/clearbox.js"></script>
<script type="text/javascript" src="static/js/jquery.uploadify.js"></script>
<script type="text/javascript" charset="utf-8">
	
	function changeContent(url) {
		showOverLay();
		$.ajax({
			url:url,
			type:"GET",
			async: false,
			success:function(data){
				$(".tableContent").empty().html(data);
				CB_Init();
				closeOverLay();
			}
		})
		
	}
</script>
<style type="text/css" media="screen">
.dataTables_wrapper label select {
	display: inline;
}

.dataTables_wrapper select,.dataTables_wrapper input {
	margin-bottom: 0;
	width: auto;
}

body {
	padding-top: 60px;
	padding-bottom: 40px;
	background-image: url("static/images/bg.png");
}

.accordion-heading,.nav.nav-tabs.myTab li a {
	background: #575757;
	background: -moz-linear-gradient(top, #575757 0%, #3D3D3D 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #575757),
		color-stop(100%, #3D3D3D) );
	background: -webkit-linear-gradient(top, #575757 0%, #3D3D3D 100%);
	background: -o-linear-gradient(top, #575757 0%, #3D3D3D 100%);
	background: -ms-linear-gradient(top, #575757 0%, #3D3D3D 100%);
	background: linear-gradient(top, #575757 0%, #3D3D3D 100%);
}

.blue {
	background: #5BC0DE;
	background: -moz-linear-gradient(center top, #5BC0DE, #2F96B4);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #5BC0DE),
		color-stop(100%, #2F96B4) );
	background: -webkit-linear-gradient(top, #5BC0DE 0%, #2F96B4 100%);
	background: -o-linear-gradient(top, #5BC0DE 0%, #2F96B4 100%);
	background: -ms-linear-gradient(top, #5BC0DE 0%, #2F96B4 100%);
	background: linear-gradient(top, #5BC0DE 0%, #2F96B4 100%);
}

.green {
	background: #51A351;
	background: -moz-linear-gradient(center top, #62C462, #51A351);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #62C462),
		color-stop(100%, #51A351) );
	background: -webkit-linear-gradient(top, #62C462 0%, #51A351 100%);
	background: -o-linear-gradient(top, #62C462 0%, #51A351 100%);
	background: -ms-linear-gradient(top, #62C462 0%, #51A351 100%);
	background: linear-gradient(top, #62C462 0%, #51A351 100%);
}

.orenge {
	background: #F89406;
	background: -moz-linear-gradient(center top, #FBB450, #F89406);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FBB450),
		color-stop(100%, #F89406) );
	background: -webkit-linear-gradient(top, #FBB450 0%, #F89406 100%);
	background: -o-linear-gradient(top, #FBB450 0%, #F89406 100%);
	background: -ms-linear-gradient(top, #FBB450 0%, #F89406 100%);
	background: linear-gradient(top, #FBB450 0%, #F89406 100%);
}

.red {
	background: #BD362F;
	background: -moz-linear-gradient(center top, #EE5F5B, #BD362F);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #EE5F5B),
		color-stop(100%, #BD362F) );
	background: -webkit-linear-gradient(top, #EE5F5B 0%, #BD362F 100%);
	background: -o-linear-gradient(top, #EE5F5B 0%, #BD362F 100%);
	background: -ms-linear-gradient(top, #EE5F5B 0%, #BD362F 100%);
	background: linear-gradient(top, #EE5F5B 0%, #BD362F 100%);
}

.accordion-inner {
	background: #E5E5E5;
}

.accordion-group {
	border: 0;
}

.accordion-heading {
	width: 100%;
	margin: auto;
	border-radius: 4px 4px 4px 4px;
}

.accordion .accordion-heading a {
	display: block;
	position: relative; #
	padding: 10px 10px 10px 40px;
	color: #FDFDFD;
	font: bold 12px/18px Arial, sans-serif;
	text-decoration: none;
}

#content {
	height: auto;
	background-color: white;
	border-radius: 4px 4px 4px 4px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05) inset;
	margin-bottom: 20px;
	min-height: 20px;
}

.tableContent {
	min-height:550px;
	padding: 20px;
}
.wrapper {
			position:absolute;
			top:50%;
			left:50%;
		}
</style>
<!--[if IE 6]>
    <link href="static/css/ie6.min.css" rel="stylesheet">
<![endif]-->
</head>
<body>
<div id="iframe-overlay"></div>
<div id="iframe-loading-overlay" style="display: none;"></div>
	<!-- 顶部 -->
	<div id="header" class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="brand" href="#">xxx系统</a>
				<div class="btn-group pull-right">
					<button id="user" class="btn btn-inverse dropdown-toggle"
						data-toggle="dropdown" href="#">
						<i class="icon-user icon-white"></i> ${sessionScope.name} <span
							class="caret"></span>
					</button>
					<input type="hidden" value="${sessionScope.id}" id="userId"/>
					<ul class="dropdown-menu">
						<li><a href="javascript:editInfo();">修改资料</a></li>
						<li class="divider"></li>
						<li><a href="logout.action">退出</a></li>
					</ul>
				</div>
				<div class="nav-collapse">
					<ul class="nav">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 背景 <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#" id="nonebg">无</a></li>
								<li><a href="#" id="bg1">背景一</a></li>
								<li><a href="#" id="bg2">背景二</a></li>
								<li><a href="#" id="bg3">背景三</a></li>
								<li><a href="#" id="bg4">背景四</a></li>
								<li><a href="#" id="bg5">背景五</a></li>
								<li><a href="#" id="bg6">背景六</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 主题 <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#" id="black">黑色</a></li>
								<li><a href="#" id="blue">蓝色</a></li>
								<li><a href="#" id="green">绿色</a></li>
								<li><a href="#" id="orenge">橙色</a></li>
								<li><a href="#" id="red">红色</a></li>
							</ul></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>


	<div class="container-fluid">
		<div class="row-fluid">
			<!-- 侧边栏 -->
			<div id="sidebar" class="span2">
				<div class="accordion" id="accordionSB">
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordionSB" href="#pagesb"> <i
								class="icon-large icon-th-large head_icon"></i>公告管理
							</a>
						</div>
						<div id="pagesb" class="accordion-body  collapse"
							style="height: 0px;">
							<div class="accordion-inner">
								<ul class="nav nav-list">
									<li><a href="javascript:changeContent('viewBulletin.action');"><i class="icon-th-list"></i>查看公告</a></li>
									<s:if test="#session.isAdmin">
									<li><a href="javascript:changeContent('bulletinManage.action');"><i class="icon-th-list"></i>公告管理</a></li>
									</s:if>
								</ul>
							</div>
						</div>
					</div>

					<div class="accordion-group ">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordionSB" href="#dashboardsb"> <i
								class="icon-th-large"></i> 用户管理
							</a>
						</div>
						<div id="dashboardsb" class="accordion-body collapse"
							style="height: 0px;">
							<div class="accordion-inner">
								<ul class="nav nav-list">
									<li><a
										href="javascript:changeContent('userManage.action')"><i
											class="icon-th-list"></i> 用户管理</a></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordionSB" href="#articlesb"> <i
								class="icon-large icon-th-large head_icon"></i> 工作管理
							</a>
						</div>
						<div id="articlesb" class="accordion-body collapse"
							style="height: 0px;">
							<div class="accordion-inner">
								<ul class="nav nav-list">
									<li><a
										href="javascript:changeContent('workManage.action')"><i
											class="icon-th-list"></i> 我的工作</a></li>
									<s:if test="#session.isAdmin">
									<li><a href="javascript:changeContent('checkWork.action')"><i
											class="icon-th-list"></i> 查看工作</a></li>
									</s:if>
								</ul>
							</div>
						</div>
					</div>


					

				</div>
			</div>

			<!-- 右侧内容 -->
			<div id="content" class="span10">
				<div class="navbar-inner">
					<div class="container-fluid"></div>
				</div>
				<div class="tableContent">
				</div>
			</div>
		</div>
	</div>
	<div class="modal hide fade" id="myModal"></div>

	<script type="text/javascript">
		$("#blue").click(function() {
			$(".navbar-inner").attr("class", "navbar-inner");
			$(".navbar-inner").addClass("blue");
			$(".navbar .nav li a").css("color", "#ffffff");
			$(".navbar .nav li ul li a").css("color", "#333333");
			$(".brand").css("color", "#ffffff");
			$("#user").attr('class', 'btn btn-info dropdown-toggle');
			$(".accordion-heading").attr("class", "accordion-heading");
			$(".accordion-heading").addClass("blue");
		});
		$("#green").click(function() {
			$(".navbar-inner").attr("class", "navbar-inner");
			$(".navbar-inner").addClass("green");
			$(".navbar .nav li a").css("color", "#ffffff");
			$(".navbar .nav li ul li a").css("color", "#333333");
			$(".brand").css("color", "#ffffff");
			$("#user").attr('class', 'btn btn-success dropdown-toggle');
			$(".accordion-heading").attr("class", "accordion-heading");
			$(".accordion-heading").addClass("green");
		});
		$("#orenge").click(function() {
			$(".navbar-inner").attr("class", "navbar-inner");
			$(".navbar-inner").addClass("orenge");
			$(".navbar .nav li a").css("color", "#ffffff");
			$(".navbar .nav li ul li a").css("color", "#333333");
			$(".brand").css("color", "#ffffff");
			$("#user").attr('class', 'btn btn-warning dropdown-toggle');
			$(".accordion-heading").attr("class", "accordion-heading");
			$(".accordion-heading").addClass("orenge");
		});
		$("#red").click(function() {
			$(".navbar-inner").attr("class", "navbar-inner");
			$(".navbar-inner").addClass("red");
			$(".navbar .nav li a").css("color", "#ffffff");
			$(".navbar .nav li ul li a").css("color", "#333333");
			$(".brand").css("color", "#ffffff");
			$("#user").attr('class', 'btn btn-danger dropdown-toggle');
			$(".accordion-heading").attr("class", "accordion-heading");
			$(".accordion-heading").addClass("red");
		});
		$("#black").click(function() {
			$(".navbar-inner").attr("class", "navbar-inner");
			$(".navbar .nav li a").css("color", "#999999");
			$(".navbar .nav li ul li a").css("color", "#333333");
			$(".brand").css("color", "#999999");
			$("#user").attr('class', 'btn btn-inverse dropdown-toggle');
			$(".accordion-heading").attr("class", "accordion-heading");
		});
		$("#bg1").click(function() {
			$("body").css("background-image", "url('static/images/bg.png')");
		});
		$("#nonebg").click(function() {
			$("body").css("background-image", "url('static/images/sdf.png')");
		});
		$("#bg3").click(function() {
			$("body").css("background-image", "url('static/images/bg3.jpg')");
		});
		$("#bg4").click(function() {
			$("body").css("background-image", "url('static/images/bg4.jpg')");
		});
		$("#bg5").click(function() {
			$("body").css("background-image", "url('static/images/bg5.jpg')");
		});
		$("#bg6")
				.click(
						function() {
							$("body")
									.css("background",
											"url('static/images/17.png') repeat scroll 0 0 #454545");
						});
		$("#bg2").click(
				function() {
					$("body").css("background-image",
							"url('static/images/dark_brick_wall.png')");
				});
	</script>
	<script>
		$(function() {
			if ($.browser.msie && parseInt($.browser.version, 10) === 6) {
				$('.row div[class^="span"]:last-child').addClass("last-child");
				$('[class*="span"]').addClass("margin-left-20");
				$(
						':button[class="btn"], :reset[class="btn"], :submit[class="btn"], input[type="button"]')
						.addClass("button-reset");
				$(":checkbox").addClass("input-checkbox");
				$('[class^="icon-"], [class*=" icon-"]')
						.addClass("icon-sprite");
				$(".pagination li:first-child a").addClass(
						"pagination-first-child")
			}
		});
		function editInfo(){
			var id = $("#userId").val();
			$.get("editUser.action?result=index&userId=" + id, function(data) {
				$("#myModal").html(data);
				$('#myModal').modal({
					backdrop : false
				});
			});
		}
		changeContent('viewBulletin.action');
	</script>
</body>
</html>