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
<h1>这是一个需要admin角色的页面</h1>
<jsp:include page="path.jsp"></jsp:include>
</body>
</html>