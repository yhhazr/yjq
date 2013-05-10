<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.nav-tabs{
		margin:0;
	}
	.uploadButton{
		padding:10px;
		margin-left:40%
	}
	.uploadify-queue-item{
		max-width: 550px;
	}
	.upload-footer{
		text-align: center;
	}
	.closePic{
		margin-top:-20px;
	}
	.closeDoc{
		margin-top:-20px;
	}
	
	
</style>
<script type="text/javascript">
$(function() {
    $('#pic_upload').uploadify({
        'swf'      : 'static/images/uploadify.swf',
        'fileObjName' : 'picture',
        'fileSizeLimit' : '2048KB',
        'formData'  : {'workId' : $('#workId').val()},
        'fileTypeExts' : '*.gif; *.jpg; *.png; *.jpeg; *.ico; *.swf',
        'auto'     : false,
        'queueID': 'picQueue',
        'buttonText' : '上传图片',
        'uploader' : 'uploadPic.action',
        'onSelectError' : function(file,errorCode) {
        	switch(errorCode){
        	case -110:
            	alert('文件' + file.name + '大于' + $('#pic_upload').uploadify('settings','fileSizeLimit'));
            	break;
        	}
        },
       	'onSelect' : function(file){
       		$('#picModal').modal({
				backdrop:false
			});
       	},
       	'onQueueComplete' : function(file) {
       		$('#picModal').modal('hide');
       		changeContent("viewWork.action?workId="+ $('#workId').val());
        } 
    });
});
$(function() {
    $('#doc_upload').uploadify({
        'swf'      : 'static/images/uploadify.swf',
        'fileObjName' : 'doc',
        'fileSizeLimit' : '2048KB',
        'formData'  : {'workId' : $('#workId').val()},
        'fileTypeExts' : '*.doc; *.docx; *.txt; *.ppt; *.pptx; *.xls; *.xlsx',
        'auto'     : false,
        'queueID': 'docQueue',
        'buttonText' : '上传文件',
        'uploader' : 'uploadDoc.action',
        'onSelectError' : function(file,errorCode) {
        	switch(errorCode){
        	case -110:
            	alert('文件' + file.name + '大于' + $('#doc_upload').uploadify('settings','fileSizeLimit'));
            	break;
        	}
        },
       	'onSelect' : function(file){
       		$('#docModal').modal({
				backdrop:false
			});
       	},
       	'onQueueComplete' : function(file) {
       		$('#docModal').modal('hide');
       		changeContent("viewWork.action?workId="+ $('#workId').val()); 
       		$('#myTab a:last').tab('show');
        } 
    });
});
$('#picModal').on('hidden', function () {
	$('#pic_upload').uploadify('cancel','*');
});
$('#docModal').on('hidden', function () {
	$('#doc_upload').uploadify('cancel','*');
});
$("#uploadPic").click(function(){
	$('#pic_upload').uploadify('upload','*');
});
$("#uploadDoc").click(function(){
	$('#doc_upload').uploadify('upload','*');
});
$(".closePic").click(function(){
	var id = $(this).val();
	function callback(){
		$.get("deletePic.action?workId=" + id,function(data){
			if(data.result == "true"){
				changeContent("viewWork.action?workId="+ $('#workId').val());
			}else{
				alert("失败！");
			}
		});
	}
	bootstrapConfirm("确定删除吗？",callback);
});
$(".closeDoc").click(function(){
	var id = $(this).val();
	function callback(){
		$.get("deleteDoc.action?workId=" + id,function(data){
			if(data.result == "true"){
				changeContent("viewWork.action?workId="+ $('#workId').val());
				$('#myTab a:last').tab('show');
			}else{
				alert("失败！");
			}
		});
	}
	bootstrapConfirm("确定删除吗？",callback);
})

</script>
</head>
<div class="well">
	<ul class="nav nav-tabs" id="myTab">
		<li class="active"><a href="#tab1" data-toggle="tab">图片</a></li>
		<li><a href="#tab2" data-toggle="tab">文件</a></li>
	</ul>
	<s:hidden name="workId"></s:hidden>
	<div class="tab-content">
		<div class="tab-pane active" id="tab1">
			<div style="height: auto; min-height: 200px; background: white; border: 1px solid #DDDDDD; border-top: none;">
				<div class="uploadButton">	
					<input type="file" name="picture" id="pic_upload" />
				</div>
				<ul class="thumbnails" style="padding:10px">
					<s:iterator value="picList" id="pic" status="st">
						<s:if test="#st.getIndex()%4 == 0">
							<li style="margin-left: 0;" class="span3">
							<div class="thumbnail">
								<a rel="clearbox[gallery=pic]" href="upload/<s:property value='#pic.picName'/> " class="thumbnail"> 
									<img style="height:160px;" alt="" src="upload/<s:property value='#pic.picName' />">
								</a>
								 <div style="text-align: center;overflow:hidden;width:90%;height:14px;margin-bottom:5px"><s:property value='#pic.realName'/></div>
							 	<button value="<s:property value='#pic.id'/>" class="close closePic" title="删除">&times;</button>
							</div>
							</li>
						</s:if>
						<s:else>
							<li class="span3">
							<div class="thumbnail">
								<a rel="clearbox[gallery=pic]" href="upload/<s:property value='#pic.picName'/> " class="thumbnail"> 
									<img style="height:160px;" alt="" src="upload/<s:property value='#pic.picName' />">
								</a>
								 <div style="text-align: center;overflow:hidden;width:90%;height:14px;margin-bottom:5px"><s:property value='#pic.realName'/></div>
							 	<button value="<s:property value='#pic.id'/>" class="close closePic" title="删除">&times;</button>
							</div>
							</li>
						</s:else>
						
					</s:iterator>
				</ul>

			</div>
		</div>
		<div class="tab-pane" id="tab2">
			<div style="height: auto; min-height: 200px; background: white; border: 1px solid #DDDDDD; border-top: none;">
				<div class="uploadButton">	
					<input type="file" name="doc" id="doc_upload" />
				</div>
				<ul class="thumbnails" style="padding:10px">
					<s:iterator value="docList" id="doc" status="st">
						<s:if test="#st.getIndex()%6 == 0">
							<li style="margin-left:0;" class="span2">
							<div class="thumbnail">
								<a target="_black" href="download.action?id=<s:property value='#doc.id' />">
								<img alt="" src="static/images/<s:property value='#doc.ext' />">
								<div style="text-align: center;overflow:hidden;width:90%;height:14px;margin-bottom:5px"><s:property value='#doc.realName'/></div>
								</a>
								<button value="<s:property value='#doc.id'/>" class="close closeDoc" title="删除">&times;</button>
							</div>
							</li>
						</s:if>
						<s:else>
							<li class="span2">
							<div class="thumbnail">
								<a target="_black" href="download.action?id=<s:property value='#doc.id' />">
								<img alt="" src="static/images/<s:property value='#doc.ext' />">
								<div style="text-align: center;overflow:hidden;width:90%;height:14px;margin-bottom:5px"><s:property value='#doc.realName'/></div>
								</a>
								<button value="<s:property value='#doc.id'/>" class="close closeDoc" title="删除">&times;</button>
							</div>
							</li>
						</s:else>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="modal hide" id="picModal">
	<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3>上传图片</h3>
    </div>
    <div class="modal-body">
    	<div id="picQueue"></div>
    </div>
    <div class="modal-footer upload-footer">
    <button id="uploadPic" class="btn btn-primary"><i class="icon-upload icon-white"></i>上传</button>
    <a href="javascript:void(0);" class="btn" data-dismiss="modal"><i class="icon-remove"></i>关闭</a>
    </div>
</div>
<div class="modal hide" id="docModal">
	<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3>上传图片</h3>
    </div>
    <div class="modal-body">
    	<div id="docQueue"></div>
    </div>
    <div class="modal-footer upload-footer">
    <button id="uploadDoc" class="btn btn-primary"><i class="icon-upload icon-white"></i>上传</button>
    <a href="javascript:void(0);" class="btn" data-dismiss="modal"><i class="icon-remove"></i>关闭</a>
    </div>
</div>
</html>