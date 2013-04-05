function bootstrapValidation(form, data) {

    "use strict";
    var errors = {
			fieldErrors:data.fieldErrors,
			errors:data.actionErrors
	};
    // Clear existing errors on submit
    form.find("div.error").removeClass("error");
    form.find("span.s2_help_inline").remove();
    form.find("div.s2_validation_errors").remove();

    //Handle non field errors
    if (errors.errors && errors.errors.length > 0) {
        var errorDiv = $("<div class='alert alert-error s2_validation_errors'></div>");
        form.prepend(errorDiv);
        $.each(errors.errors, function(index, value) {
            errorDiv.append('<p>' + value + '</p>\n');
        });
    }

    //Handle field errors
    if (errors.fieldErrors) {
        $.each(errors.fieldErrors, function(index, value) {
            var element = form.find(":input[name=\"" + index + "\"]"), controlGroup, controls;
            if (element && element.length > 0) {

            // select first element
                element = $(element[0]);
            controlGroup = element.closest("div.control-group");
            controlGroup.addClass('error');
            controls = controlGroup.find("div.controls");
            if (controls) {
                controls.append("<span class='help-inline s2_help_inline'>" + value[0] + "</span>");
            }
            }
        });
    }
}
function bootstrapConfirm(message,callback){
	var div = "<div style='width:300px;margin:0 0 0 0;left:40%;top:30%;' class='modal hide' id='confirmModal'><div class='modal-body'><span><div style='text-align:center; min-height:30px; _height:30px; padding-top:20px'>"+message+"</div></span></div><div style='padding:7px;' class='modal-footer'><a href='javascript:void(0);' id='yes' class='btn'>确定</a><a href='javascript:void(0);' class='btn' id='no'>取消</a></div></div>";
	$("body").append(div);
	$("#confirmModal").modal({
		backdrop:false
	});
	$("#no").click(function(){
		$('#confirmModal').modal('hide');
		$('#confirmModal').remove();
	});
	$("#yes").click(function(){
		$('#confirmModal').modal('hide');
		$('#confirmModal').remove();
		callback();
	});
}
function bootstrapAlert(message){
	var div = "<div style='width:300px;margin:0 0 0 0;left:40%;top:30%;' class='modal hide' id='confirmModal'><div class='modal-body'><span><div style='text-align:center; min-height:30px; _height:30px; padding-top:20px'>"+message+"</div></span></div><div style='padding:7px;' class='modal-footer'><a href='javascript:void(0);' class='btn' id='no'>确定</a></div></div>";
	$("body").append(div);
	$("#confirmModal").modal({
		backdrop:false
	});
	$("#no").click(function(){
		$('#confirmModal').modal('hide');
		$('#confirmModal').remove();
	});
}