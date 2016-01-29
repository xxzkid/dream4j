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
	
</head>
<body>
    <form>
	<p>username:<input type="text" name="username" /></p>
	<p>password:<input type="password" name="password" /></p>
	<p>captcha:<input type="text" name="captcha" /><img src="${ctx}/code.jpg" id="captcha"></p>
	<p>rememberMe:<input type="checkbox" name="rememberMe" value="true" /></p>
	<p><a href="javascript:;" id="submit">submit</a></p>
    </form>
    
    
    <script src="${res}/js/zepto.min.js"></script>
    <script src="${res}/js/constants.js"></script>
	<script src="${res}/js/test/login.js"></script>

	<jsp:include page="path.jsp"></jsp:include>
</body>
</html>