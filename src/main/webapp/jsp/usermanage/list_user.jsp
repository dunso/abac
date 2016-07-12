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
				<li class="active"><a href="${ctx }/user">用户属性管理</a></li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">用户属性管理</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="col-lg-2 col-md-2 col-xs-6">用户列表</div>
						<div class="col-lg-9 col-md-9 col-xs-4"></div>
						<div class="col-lg-1 col-md-1 col-xs-1"><button class="btn btn-primary" onclick="window.location.href='${ctx}/user/add'">添加</button></div>
					</div>
					<div class="panel-body">
						<form action="${ctx }/user/list" method="post" id = "subForm">
							<input type="hidden" id="page" name="page">
							<input type="hidden" id ="pagesize" name="pagesize" value="${pagesize }">
							<div class = "form-group col-lg-4">
								<label>用户类型</label>
								<select class="form-control" name ="type" id="type" >
									<option value="">全部</option>
									<option value="0">学生</option>
									<option value="1">教师</option>
								</select>
							</div>
							<div class = "form-group col-lg-4">
								<label>学号/职工号</label>
								<input class="form-control" type="text" name ="userID" value="${userID }">
							</div>
							<div class = "form-group col-lg-4">
								<label>姓名</label>
								<input class="form-control" type="text" name ="name" value="${name }">
							</div>
							<div class="form-group col-lg-4 col-lg-offset-8 col-xs-4 col-xs-offset-8">
								<input type="reset" value="重置" class="btn btn-default col-lg-offset-1">
								<input type="submit" value="查询" class="btn btn-primary col-lg-offset-1">
							</div>
						</form>
						<div class = "diliver"></div>
						<table data-toggle="table" >
						    <thead>
						    <tr>
						        <th>学号/职工号</th>
						        <th>姓名</th>
						        <th>学生/教师</th>
						        <th width="200px">操作</th>
						    </tr>
						    </thead>
						    
						    <c:if test="${not empty userList }">
						    <tbody>
						    <c:forEach items="${userList }" var="user">
						    <tr id ="item_${user.userID }">
						    	<td>${user.userID }</td>
						    	<td>${user.username }</td>
						    	<td><c:if test="${user.type == '0' }">
						    			学生
						    		</c:if>
						    		<c:if test="${user.type == '1' }">
						    			教师
						    		</c:if>
						    		</td>
						    	<td >
							    	<button class="btn btn-default" onclick="window.location.href='${ctx}/user/info/${user.userID}/${user.type}'">查看</button>&nbsp;&nbsp;&nbsp;
							    	<button class="btn btn-primary" onclick="window.location.href='${ctx}/user/edit/${user.userID}/${user.type}'">修改</button>&nbsp;&nbsp;&nbsp;
							    	<button class="btn btn-danger"  onclick="javascript:deleteusr('${user.userID}','${user.type}')">删除</button>
						    	</td>
						    </tr>
						    </c:forEach>
						    </tbody>
						    </c:if>
						</table>
						<%@include file="/common/pagination.jsp" %>
					</div>
				</div>
			</div>
		</div><!--/.row-->	
	</div><!--/.main-->
</body>
</html>
<script>
	$("#type").val("${type}");
	function deleteusr(userID,type){
		$.ajax({
			url:"${ctx}/user/delete/"+userID+"/"+type,
			type:"GET",
			success:function(){
				$("#item_"+userID).css("display","none");
			}
		});
	}
</script>