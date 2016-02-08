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
	<div class="weui_cells weui_cells_form">
		<div class="weui_cell">
			<div class="weui_cell_hd">
				<label class="weui_label">用户名</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="text" name="username" placeholder="请输入用户名" />
			</div>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_hd">
				<label class="weui_label">密码</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="password" name="password" placeholder="请输入密码" />
			</div>
		</div>
		<div class="weui_cell weui_vcode">
			<div class="weui_cell_hd">
				<label class="weui_label">验证码</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="text" name="captcha" placeholder="请输入验证码" />
			</div>
			<div class="weui_cell_ft">
				<img src="${ctx}/code.jpg" id="captcha" />
			</div>
		</div>
		<div class="weui_cell weui_cell_switch">
			<div class="weui_cell_hd">
			    <div class="weui_label">记住我</div>
			</div>
			<div class="weui_cell_bd">
				<input class="weui_switch" type="checkbox" name="rememberMe" value="true" />
			</div>
		</div>
		<div class="weui_cell">
		  <div class="weui_cell_hd">
			<a class="weui_btn weui_btn_primary" id="submit" href="javascript:">确定</a>
		  </div>
          <div class="weui_cell_hd">
			<a class="weui_btn weui_btn_primary" id="submit" href="${ctx}/">注册</a>
          </div>
		</div>
	</div>
	</form>

	<script src="${res}/js/zepto.min.js"></script>
	<script src="${res}/js/constants.js"></script>
	<script src="${res}/js/ui.js"></script>
	<script src="${res}/js/test/login.js"></script>

	<jsp:include page="path.jsp"></jsp:include>
</body>
</html>