<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>学生信息管理</title>
	<%
    	String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
	<c:set var="path" value="<%=basePath %>"></c:set>
 	<link href="${path }/statics/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/statics/js/zeroModal/zeroModal.css" />
</head>
<style>

</style>
<body>

	<div style="text-align: center;">
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">学生编号</th>
					<th style="text-align: center;">学生姓名</th>
					<th style="text-align: center;">学生账户</th>
					<th style="text-align: center;">登录密码</th>
					<th style="text-align: center;">就读班级</th>
					<th style="text-align: center;">就读年级</th>
				<%-- 	<c:if test="${sessionScope.adminPower == 1 }"> --%>
						<th style="text-align: center;">操作
							&emsp;
							
							<!-- <a href="preAddStudent" class="btn btn-xs btn-info">添加</a> -->
						</th>
					<%-- </c:if> --%>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty students }">
						<c:forEach items="${pageInfo.list}" var="student">
							<tr>
								<td>${student.studentId }</td>
								<td>${student.studentName }</td>
								<td>${student.studentAccount }</td>
								<td>
									<span id="hidePwd">******</span>
									<span id="showPwd" style="display: none">${student.studentPwd }</span>
									<button type="button" class="btn btn-info btn-xs viewPwd">查看</button>
								</td>
								<td>${student.classInfo.className }</td>
								<td>${student.classInfo.grade.gradeName }</td>
								<td>
									<div class="btn-group">
										<a href="${pageContext.request.contextPath }/edu/admin/selectStudentById?studentId=${student.studentId }" class="btn btn-info btn-sm">修改</a>
										<%-- <button type="button" class="btn btn-info btn-sm" onclick="_iframe(1, 'student/${student.studentId }', 'students')">修改</button> --%>
										<%-- <button type="button" class="btn btn-danger btn-sm" onclick="del('student/${student.studentId }')">删除</button> --%>
										<a href="${pageContext.request.contextPath }/edu/admin/deleteStudent?studentId=${student.studentId }" class="btn btn-danger btn-sm">删除</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		<form action="student" method="post">
			<input type="hidden" value="DELETE" name="_method" />
		</form>
		<c:if test="${pageTotal > 1 }">
			<div>
				<ul class="pagination">
					<li><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=1">首页</a></li>
					<c:if test="${pageNow-1 > 0 }">
						<li><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=${pageNow-1 }">上一页</a></li>
					</c:if>
					<c:forEach begin="${pageNow }" end="${pageNow+4 }" var="subPage">
						<c:if test="${subPage-5 > 0 }">
							<li><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=${subPage-5 }">${subPage-5 }</a></li>
						</c:if>
					</c:forEach>
					<c:forEach begin="${pageNow }" end="${pageNow+5 }" step="1" var="pageNo">
						<c:if test="${pageNo <= pageTotal }">
							<c:if test="${pageNow == pageNo }">
								<li class="active"><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=${pageNo }">${pageNo }</a></li>
							</c:if>
							<c:if test="${pageNow != pageNo }">
								<li><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=${pageNo }" class="pageLink">${pageNo }</a></li>
							</c:if>
						</c:if>
					</c:forEach>
					<c:if test="${pageNow+1 <= pageTotal }">
						<li><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=${pageNow+1 }">下一页</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath }/edu/admin/tostudents?startPage=${pageTotal }">尾页</a></li>
					<li>
						<a>${pageNow }/${pageTotal }</a>
					</li>
					<li>
						<div style="width:-1%; height:100%;float:right;">
							<form action="${pageContext.request.contextPath }/edu/admin/tostudents" id="scannerPageForm">
								<input id="scannerPage" type="text" name="startPage" style="width: 40px; height: 30px; border: 1px solid gray; border-radius: 4px;" />
								<input class="btn btn-default goPage" type="submit" value="Go" style="margin-left: -4px; height: 30px;" />
							</form>
						</div>
					</li>
				</ul>
			</div>
		</c:if>
	</div>


	<!-- js引入 -->
    <script src="${path}/statics/js/jquery.js"></script>
    <script src="${path}/statics/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/statics/js/handle.js"></script>
    <script src="${path }/statics/js/zeroModal/zeroModal.min.js"></script>
    <script src="${path }/statics/js/add-update.js"></script>
   	<script src="${path }/statics/js/handle.js"></script>
   	<script type="text/javascript">
   		$(function() {
   			$(".viewPwd").click(function() {
   				var pwd0 = $(this).siblings("#hidePwd").text();
   				if (pwd0.indexOf("*") != -1) {
   					 var pwd = $(this).siblings("#showPwd").text(); 
   					 /* var pwd =$("#showPwd").text();  */
   					$(this).siblings("#hidePwd").text(pwd);
   					return;
   				} else {
   					$(this).siblings("#hidePwd").text("******");
   				}
   			});
   		});
   	</script>
</body>
</html>