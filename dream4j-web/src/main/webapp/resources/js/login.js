/** 每一个js的最前面就是以当前js名称的对象，所有的function, field 都必须写在对象中， */
var login = {};

$(function(){
	
	$('#captcha').on('click', function(){
		$(this).attr('src', PATH.ctx + '/code.jpg?_t=' + new Date().getTime());
	});
	
	$('#submit').on('click', login.submit);
	
});

login.submit = function() {
	var data = {};
	data.username = $('input[name="username"]').val();
	data.password = $('input[name="password"]').val();
	data.captcha = $('input[name="captcha"]').val();
	data.rememberMe = $.trim($('input[name="rememberMe"]:checked').val()) == '' ? false : true;
	console.log(data);
	login.dologin(data);
}

login.reset = function() {
	$('form').get(0).reset();
}

login.dologin = function(data) {
	var url = PATH.ctx + "/login";
	$.post(url, data, function(json){
		console.log(json);
		if(json.result.code === 0) {
		    window.location.href = PATH.ctx + "/test";
		} else {
			alert(CODE[json.result.code]);
			login.reset();
		}
	}, 'json');
}