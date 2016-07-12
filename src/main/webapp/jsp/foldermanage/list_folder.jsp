<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
<html>
<body>		
					<form action="${ctx}/folder/list" method="get">
					<div class="panel-body">	
						
						<table data-toggle="table">
						    <thead>
						    <tr>
						        <th>目录号</th>
						        <th>目录名</th>
						        <th>操作</th>
						    </tr>
						    </thead>
						    <tbody>
						    <c:forEach var="folder" begin="0" items="${folderlist}">
						    <tr>
						    
						    
						    	<td id="${folder.folderID}">${folder.folderID}</td>
						    	<td id="${folder.name}">${folder.name}</td>
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