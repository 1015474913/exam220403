<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>班级信息添加</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
    <c:set var="path" value="<%=basePath %>"></c:set>
    <link href="${path }/statics/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
    <link href="${path }/statics/css/form-public.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${path }/statics/js/zeroModal/zeroModal.css" />
</head>
<body>
<div style="text-align: center;">
    <form action="${path}/edu/classes/addClass" method="POST" >
        <!-- 修改情况下才显示编号 -->

        <b class="form-title">班级名称：</b>
        <input type="text" name="className"  class="ipt"/>
        <br /><br />
        <b class="form-title">所属年级：</b>
        <select class="sel" name="grade.gradeId" >
            <c:forEach items="${grades}" var="grade" >
                <option value="${grade.gradeId}">${grade.gradeName}</option>
            </c:forEach>
        </select>


        <br /><br />
        <b class="form-title">班 主&nbsp;&nbsp;任：</b>
        <select class="sel" name="teacher.teacherId" >
            <c:forEach items="${teachers}" var="teacher" >
                <option value="${teacher.teacherId}">${teacher.teacherName}</option>
            </c:forEach>
        </select>

        <br /><br />

        <input class="sub" type="submit" value="提交"/>
    </form>
</div>


<!-- js引入 -->
<script src="${path }/statics/js/jquery.js"></script>
<script src="${path }/statics/js/bootstrap/bootstrap.min.js"></script>
<script src="${path }/statics/js/form-public.js"></script>
</body>
</html>