<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>login</title>
	<link rel="stylesheet" href="${res}/css/weui.min.css" />
	<script src="${res}/js/zepto.min.js"></script>
</head>
<body>
	<p>username:<input type="text" name="username" /></p>
	<p>password:<input type="password" name="password" /></p>
	<p>captcha:<input type="text" name="captcha" /><img src="${ctx}/code.jpg" id="captcha"></p>
	<p>rememberMe:<input type="checkbox" name="rememberMe" value="true" /></p>
	<p><a href="javascript:;" id="submit">submit</a></p>

	<script>
    $(function(){
    	$('#captcha').on('click', function(){
    		$(this).attr('src', PATH.ctx + '/code.jpg?_t=' + new Date().getTime());
    	});
    	
    	$('#submit').on('click', function(){
    		var data = {};
    		data.username = $('input[name="username"]').val();
    		data.password = $('input[name="password"]').val();
    		data.captcha = $('input[name="captcha"]').val();
    		data.rememberMe = $.trim($('input[name="rememberMe"]:checked').val()) == '' ? false : true;
    		console.log(data);
    		dologin(data);
    	});
    });
    
    var dologin = function(data) {
    	var url = PATH.ctx + "/login";
    	$.post(url, data, function(json){
    		console.log(json);
    		if(json.code == 0) {
    		    window.location.href = PATH.ctx + "/test";
    		}
    	}, 'json');
    }
    </script>

	<jsp:include page="path.jsp"></jsp:include>
</body>
</html>