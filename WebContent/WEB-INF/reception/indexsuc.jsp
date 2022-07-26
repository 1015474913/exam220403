<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>登录</title>
	<%
    	String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
	<c:set var="path" value="<%=basePath %>"></c:set>
 	<link href="${path }/statics/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<div style="width:100%;height:100%;text-align: center;">
	    <h2>${fail }</h2>
		<h2>${success }</h2>
	</div>

	<!-- js引入 -->
    <script src="${path }/statics/js/jquery.js"></script>
    <script src="${path }/statics/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/statics/js/login.js"></script>
</body>
<script type="text/javascript">
setTimeout(function(){
	window.location.href = '${path }/reception/toIndex';
	parent.zeroModal.closeAll();
},1000);
</script>
</html>