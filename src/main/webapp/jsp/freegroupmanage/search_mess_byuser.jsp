<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
<head>
	<title>云存储管理中心</title>
	<%@include file="/common/meta.jsp"%>
	</head>
<body>
<form class="form-horizontal" action="${ctx}/freegroup/searchmess" method="get">

	
	<div class="panel-body">	
						<label class="col-lg-3 control-label">用户ID：</label>	
						<input type="text" class="form-control " id="user_id" name ="user_id"></div>
						
					</div>
	<div class= "row col-lg-12"><p><br></p></div>
	<div class = "col-lg-4 col-xs-4"></div>
	<div class="form-group col-lg-4 col-xs-4">
		<input type="reset" value="重置" class="btn btn-default ">
		&nbsp;&nbsp;&nbsp;
		<input type="submit" value="查看" class="btn btn-primary ">
	</div>
</form>
<table data-toggle="table">

    <thead>
	    <tr>
	        <th>消息ID</th>
	        <th>用户ID</th>
	        <th>自由群组ID</th>
	        <th>消息状态</th>
	        <th width="200px">操作</th>
	    </tr>
	    </thead>
    <tbody>
    <c:forEach var="message" items="${list1}">
    <tr>
    	<td id="${message.mess_id}">${message.mess_id}</td>
    	<td id="${message.user_id}">${message.user_id}</td>
    	<td id="${message.fg_id}">${message.fg_id}</td>
    	<td id="${message.state}">${message.state}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>