<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3>修改工作</h3>
    </div>
    <div class="modal-body">
    	<s:form id="workForm" enctype="multipart/form-data"  cssClass="form-horizontal">
    	 <s:hidden name="work.id"/>
   		 <div class="control-group ">
                	<label class="control-label">工作名称:</label>
                	<div class="controls">
                		<s:textfield name="work.workName"/>
					</div>
		</div>
		 <div class="control-group ">
                	<label class="control-label">工作进度:</label>
                	<div class="controls">
						<s:select style="width:auto;" list="#{'进行中':'进行中','已完成':'已完成'}" name="work.progress"></s:select>
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
    		$.post("editWorkSubmit.action",$("#workForm").serialize(),function(data){
    			if(data.result == "true"){
    				$('#myModal').modal('hide');
    				workTable.fnPageChange( 'first' );
    			}else{
    				var errors = {
    					fieldErrors:data.fieldErrors,
    					errors:data.actionErrors
    				}
    				bootstrapValidation($("#workForm"),errors);
    			}
    		});
    	});
    
    </script>