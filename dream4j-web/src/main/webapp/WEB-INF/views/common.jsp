<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 项目路径 --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"></c:set>
<%-- 静态文件路径 --%>
<c:set var="res" value="${ctx}/resources" scope="request"></c:set>
<%-- 图片路径 --%>
<c:set var="imgserver" value="/img" scope="request"></c:set>