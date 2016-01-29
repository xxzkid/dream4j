/** 每一个js的最前面就是以当前js名称的对象，所有的function, field 都必须写在对象中， */
var login = {};

$(function(){
	
	$('#captcha').on('click', login.captchaClick);
	
	$('#submit').on('click', login.submit);
	
});

login.captchaClick = function() {
	$('#captcha').attr('src', PATH.ctx + '/code.jpg?_t=' + new Date().getTime());
};

login.reset = function() {
	$('form').get(0).reset();
}

login.submit = function() {
	var data = {};
	data.username = $('input[name="username"]').val();
	data.password = $('input[name="password"]').val();
	data.captcha = $('input[name="captcha"]').val();
	data.rememberMe = $.trim($('input[name="rememberMe"]:checked').val()) == '' ? false : true;
	login.dologin(data, login.submitCallback);
}

login.submitCallback = function(data, json) {
	console.log(json);
	if(json.result.code === 0) {
	    window.location.href = PATH.ctx + "/test";
	} else if (json.result.code === -1) {
		$.ui.alert('提示', json.result.object[0].defaultMessage);
	} else {
		$.ui.alert('提示', CODE[json.result.code]);
	}
	login.captchaClick();
	login.reset();
}

login.dologin = function(data, fn) {
	var url = PATH.ctx + "/login";
	$.post(url, data, function(json){
		if (typeof fn === 'function' ) fn(data, json);
	}, 'json');
}