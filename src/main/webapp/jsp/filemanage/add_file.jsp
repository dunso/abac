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
	<%@include file="/common/siderbarFile.jsp" %>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="${ctx }/user"><span class="glyphicon glyphicon-home"></span></a></li>
				<li>文件属性管理</li>
				<li class="active">添加文件</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">添加文件</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li><a href="#add_file" data-toggle="tab">添加文件</a></li>
							<li><a href="#delete_file" data-toggle="tab">删除文件</a></li>
							<li><a href="#list_file" data-toggle="tab">返回文件列表</a></li>
						</ul>
												
						<div class="tab-content">
							<div class="tab-pane fade in active" id="add_file">
								<%@include file="edit_file.jsp" %>
							</div>
							<div class="tab-pane fade " id="delete_file">
								<%@include file="delete_file.jsp" %>
							</div>
							<div class="tab-pane fade" id="list_file">
								<%@include file="list_file.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>