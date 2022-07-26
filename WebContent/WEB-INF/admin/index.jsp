<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title> 后台管理系统</title>
	<%
    	String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
	<c:set var="path" value="<%=basePath %>"></c:set>
	<link href='${path }/statics/images/admin/admin_index.png' rel='shortcut icon' type='image/x-icon' />
 	<link href="${path }/statics/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<!-- js引入 -->
    <script src="${path }/statics/js/jquery.js"></script>
    <script src="${path }/statics/js/bootstrap/bootstrap.min.js"></script>
</head>
	 <c:if test="${admin.teacherName == null }">
		<%response.sendRedirect("/edu/toLogin"); %>
	</c:if> 
	<frameset rows="15%, *" frameborder="0">
    	<frame src="${path }/edu/admin/toHead" name="head" noresize="noresize" />
    	<frameset cols="15%, *" frameborder="0">
    		<frame src="${path }/edu/admin/toLeft" name="left" noresize="noresize" />
    		<frameset rows="7%, *">
	    		<frame src="${path }/edu/admin/toNav" name="nav" noresize="noresize" />
	    		<frame src="${path }/edu/admin/toHome" name="right" noresize="noresize" />    			
    		</frameset>
    	</frameset>
    </frameset>

</html>