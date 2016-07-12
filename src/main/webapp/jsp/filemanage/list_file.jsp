<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
<html>
<body>		
					<form action="${ctx}/file/list" method="get">
					<div class="panel-body">	
						
						<table data-toggle="table">
						    <thead>
						    <tr>
						        <th>文件号</th>
						        <th>文件名</th>
						        <th>文件夹</th>
						        <th>所有者</th>
						        <th>上传时间</th>
						        <th>扩展名</th>
						        <th>云端路径</th>
						    </tr>
						    </thead>
						    <tbody>
						    <c:forEach var="file" begin="0" items="${filelist}">
						    <tr>
						    
						    
						    	<td id="${file.file_id}">${file.file_id}</td>
						    	<td id="${file.file_name}">${file.file_name}</td>
						    	<td id="${file.folderId}">${file.folderId}</td>
						    	<td id="${file.owner}">${file.owner}</td>
						    	<td id="${file.upload_time}">${file.upload_time}</td>
						    	<td id="${file.exp_name}">${file.exp_name}</td>
						    	<td id="${file.cloud_path}">${file.cloud_path}</td>
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