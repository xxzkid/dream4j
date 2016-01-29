<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>403 禁止访问</title>
    <link rel="stylesheet" href="${res}/css/weui.min.css" />
</head>
<body>
    <div class="page">
        <div class="weui_msg">
            <div class="weui_icon_area"><i class="weui_icon_msg weui_icon_warn"></i></div>
            <div class="weui_text_area">
	            <h2 class="weui_msg_title">禁止访问</h2>
	            <p class="weui_msg_desc">由于您没有相关权限，无法访问</p>
	        </div>
        </div>
    </div>
	<script src=""></script>
	<jsp:include page="path.jsp"></jsp:include>
</body>
</html>