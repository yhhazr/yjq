<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<link href="static/css/bootstrap.css" rel="stylesheet">
<link href="static/css/login-style.css" rel="stylesheet">
<script type="text/javascript" src="static/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="well" style="width:40%;margin:auto auto;">
				<div class="navbar navbar-static navbar_as_heading">
					<div class="navbar-inner">
						<div class="container" style="width: auto;">
							<a class="brand">用户登录</a>
						</div>
					</div>
				</div>
				<s:actionerror />
				<s:form id="validationForm" namespace="/" action="loginSubmit"
					enctype="multipart/form-data" cssClass="form-horizontal">



					<div class="control-group ">
						<label class="control-label">用户名:</label>
						<div class="controls">
							<input type="text" name="userName" value=""
								id="validationForm_name" />
							<s:fielderror>
								<s:param>userName</s:param>
							</s:fielderror>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="validationForm_password">密&nbsp;&nbsp;&nbsp;码:</label>
						<div class="controls">
							<input type="password" name="password"
								id="validationForm_password" />
							<s:fielderror>
								<s:param>password</s:param>
							</s:fielderror>
						</div>
					</div>


					
					<input type="submit" value="登录" style="margin-left:160px;"
						class="btn btn-primary">

				</s:form>
			</div>
		</div>
	</div>
</body>
</html>