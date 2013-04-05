createCog = function(element, animate, append, context, opacity) {
	if ($(element).length === 0)
		return false;
	var append = typeof append == 'undefined' ? false : append;
	var animate = typeof animate == 'undefined' ? false : animate;
	var cogString = '<div class="cog-container"><div class="cog-bottom-left"></div><div class="cog-top-right"></div></div>';
	if (append) {
		element.append(cogString);
	} else {
		element.html(cogString);
	}
	if (typeof opacity != 'undefined')
		element.find('.cog-container').css({
			opacity : opacity
		});
	if (animate)
		animateCog(element, context);
	return true;
}
animateCog = function(element, context) {
	if (typeof context == 'undefined')
		context = $('body');
	var element = element.find('.cog-container');
	var bottomLeftCogAngle = 0;
	var topRightCogAngle = 0;
	var interval = setInterval(function() {
		var domElement = context.find(element);
		// If the element no longer exists, then remove the interval for garbage
		// disposal.
		if (domElement.length === 0 || !domElement.is(':visible'))
			return clearInterval(interval);
		var bottomLeftValue = 'rotate(' + bottomLeftCogAngle + 'deg)';
		var topRightValue = 'rotate(' + topRightCogAngle + 'deg)';
		element.find('div.cog-bottom-left').css({
			'-webkit-transform' : bottomLeftValue,
			'-moz-transform' : bottomLeftValue
		});
		element.find('div.cog-top-right').css({
			'-webkit-transform' : topRightValue,
			'-moz-transform' : topRightValue
		});
		bottomLeftCogAngle += 2.0;
		topRightCogAngle -= 3.01;
	}, 20);
}
function showOverLay() {
	createCog($('div#iframe-loading-overlay'), true);
	$("#iframe-loading-overlay").show();
}
function closeOverLay() {
	$("#iframe-loading-overlay").fadeOut(500);
	//createCog($('div#iframe-loading-overlay'), false);
}