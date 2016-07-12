<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
<head>
	<title>云存储管理中心</title>
	<%@include file="/common/meta.jsp"%>
	</head>
<body>
	<%@include file="/common/top.jsp" %>
	<%@include file="/common/siderbar.jsp" %>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="${ctx }"><span class="glyphicon glyphicon-home"></span></a></li>
				<li><a href="${ctx }/user">用户属性管理</a></li>
				<li class="active"> 用户信息</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">用户信息</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<c:if test="${ type == 0}">
						<%@include file="edit_student.jsp" %>
						</c:if>
						<c:if test="${ type == 1}">
						<%@include file="edit_teacher.jsp" %>
						</c:if>
						<div class="row" id="formButton">
							<div class="row col-lg-12">
								<p>
									<br>
								</p>
							</div>
							<div class="col-lg-4 col-xs-4"></div>
							<div class="col-lg-4 col-xs-4">
								<button class="btn btn-default" onclick="history.back(-1)">返回</button>
								&nbsp;&nbsp;&nbsp; 
								<button class="btn btn-primary" onclick="window.location.href='${ctx}/user/edit/${user.id }/${type }'">修改</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>
<script>
$('input').attr("readonly","readonly");
$('#formButton').css('display','none');
</script>