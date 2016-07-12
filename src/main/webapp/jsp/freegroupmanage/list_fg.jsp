<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
<html>
<body>		
					<form action="${ctx}/freegroup/list" method="get">
					<div class="panel-body">	
						
						<table data-toggle="table">
						 <thead>
						    <tr>
						        <th>自由群组ID</th>
						        <th>自由群组名</th>
						        <th>操作</th>
						    </tr>
						    </thead>
						    <tbody>
						    <c:forEach var="freegroup" begin="0" items="${fglist}">
						    <tr>
						    
						    
						    	<td id="${freegroup.fg_id}">${freegroup.fg_id}</td>
						    	<td id="${freegroup.fg_name}">${freegroup.fg_name}</td>
						    	<td>
						    	<button class="btn btn-default">查看</button>
						    	<button class="btn btn-danger">删除</button>
						    	</td>
						    </tr>
						    </c:forEach>
						    </tbody>
						</table>
					</div>
					<div class="form-group col-lg-4 col-xs-4">
						
						<input type="submit" value="显示列表" class="btn btn-primary ">
					</div>
					</form>

</body>
</html>