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
	<%@include file="/common/siderbarFolder.jsp" %>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="${ctx }/folder"><span class="glyphicon glyphicon-home"></span></a></li>
				<li>目录管理</li>
				<li class="active">添加目录</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">添加目录</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#add_folder" data-toggle="tab">添加目录</a></li>
							<li><a href="#rename_folder" data-toggle="tab">目录重命名</a></li>
							<li><a href="#move_folder" data-toggle="tab">移动目录</a></li>
							<li><a href="#delete_folder" data-toggle="tab">删除目录及下所有内容</a></li>
							<li><a href="#list_folder" data-toggle="tab">返回目录列表</a></li>
						</ul>
						
						<div class="tab-content">
							<div class="tab-pane fade in active" id="add_folder">
								<%@include file="edit_folder.jsp" %>
							</div>
							<div class="tab-pane fade " id="rename_folder">
								<%@include file="rename_folder.jsp" %>
							</div>
							<div class="tab-pane fade " id="move_folder">
								<%@include file="move_folder.jsp" %>
							</div>
							<div class="tab-pane fade " id="delete_folder">
								<%@include file="delete_folder.jsp" %>
							</div>
							<div class="tab-pane fade" id="list_folder">
								<%@include file="list_folder.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>