<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3>新增公告</h3>
    </div>
    <div class="modal-body">
    	<s:form id="bulForm" enctype="multipart/form-data"  cssClass="form-horizontal">
   		 <div class="control-group ">
                	<label class="control-label">标题:</label>
                	<div class="controls">
                		<s:textfield name="bulletin.title"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">正文:</label>
                	<div class="controls">
                		<s:textarea name="bulletin.content"></s:textarea>
					</div>
		</div>

		 <div class="control-group ">
                	<label class="control-label">重要程度:</label>
                	<div class="controls">
						<s:select style="width:auto;" list="#{'一般':'一般','很重要':'很重要'}" name="bulletin.degree"></s:select>
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
    		$.post("addBulletinSubmit.action",$("#bulForm").serialize(),function(data){
    			if(data.result == "true"){
    				$('#myModal').modal('hide');
    				oTable.fnPageChange( 'first' );
    			}else{
    				var errors = {
    					fieldErrors:data.fieldErrors,
    					errors:data.actionErrors
    				}
    				bootstrapValidation($("#bulForm"),errors);
    			}
    		});
    	});
    
    </script>