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
				<li><a href="${ctx}/freegroup"><span class="glyphicon glyphicon-home"></span></a></li>
				<li>自由群组管理</li>
				<li class="active">创建自由群组</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">创建自由群组</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#add_fg" data-toggle="tab">创建自由群组</a></li>
						</ul>
						
						<div class="tab-content">
							<div class="tab-pane fade in active" id="add_group">
								<%@include file="edit_fg.jsp" %>
							</div>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>