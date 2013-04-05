<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3>修改用户</h3>
    </div>
    <div class="modal-body">
    	<s:form id="userForm" enctype="multipart/form-data"  cssClass="form-horizontal">
   		 <s:hidden name="user.id"></s:hidden>
   		 <s:hidden name="result"></s:hidden>
   		 <div class="control-group ">
                	<label class="control-label">姓名:</label>
                	<div class="controls">
                		<s:textfield name="user.name"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">电话:</label>
                	<div class="controls">
						<s:textfield name="user.phone"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">邮箱:</label>
                	<div class="controls">
						<s:textfield name="user.email"/>
					</div>
		</div>
   		 <div class="control-group ">
                	<label class="control-label">备注:</label>
                	<div class="controls">
						<s:textfield name="user.remark"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">登录用户名:</label>
                	<div class="controls">
						<s:textfield name="user.userName"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">登录密码:</label>
                	<div class="controls">
						<s:password name="user.password" value="123456" showPassword="true"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">确认密码:</label>
                	<div class="controls">
						<s:password name="confirmPassword" value="123456" showPassword="true"/>
					</div>
		</div>
		 <div class="control-group ">
                	<label class="control-label">系统管理员:</label>
                	<div class="controls">
						<s:select style="width:auto;" list="#{false:'否',true:'是'}" name="user.isAdmin"></s:select>
					</div>
		</div>
		</s:form>
    </div>
    <div class="modal-footer">
    <a href="javascript:void(0);" id="save" class="btn btn-primary">保存</a>
    <a href="javascript:void(0);" class="btn" data-dismiss="modal">关闭</a>
    </div>
    <script type="text/javascript">
    	$("#save").click(function(){
    		$.post("editUserSubmit.action",$("#userForm").serialize(),function(data){
    			if(data.result == "true"){
    				$('#myModal').modal('hide');
    				if($("input[name=result]").val() == "index"){
    					bootstrapAlert("修改成功！");
    				}else{
	    				oTable.fnPageChange( 'first' );
    				}
    			}else{
    				var errors = {
    					fieldErrors:data.fieldErrors,
    					errors:data.actionErrors
    				}
    				bootstrapValidation($("#userForm"),errors);
    			}
    		});
    	});
    
    </script>