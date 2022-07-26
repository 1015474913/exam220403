<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>
	<%
    	String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
	<c:set var="path" value="<%=basePath %>"></c:set>
 	<link href="${path }/statics/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link href="${path }/statics/css/form-public.css" rel="stylesheet" />
</head>
<body>

	<div style="text-align: center;">
		<form action="${path}/edu/grades/addGradeSubmit" method="POST"  >

			<br /><br />
			年级名称：<input type="text" class="ipt" name="gradeName"/>
			<br /><br />
			<input type="submit" value="提交" class="sub" />		
		</form>
	</div>


	<!-- js引入 -->
    <script src="${path }/statics/js/jquery.js"></script>
    <script src="${path }/statics/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/statics/js/form-public.js"></script>
</body>
</html>