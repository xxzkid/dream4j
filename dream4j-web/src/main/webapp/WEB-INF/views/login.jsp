<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<jsp:include page="common.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<p>username:<input type="text" name="username" /></p>
<p>password:<input type="password" name="password" /></p>
<p>captcha:<input type="text" name="captcha" /></p>
<p><a href="javascript:;" onclick="">submit</a></p>

<script src="${resources}/js"></script>
</body>
</html>