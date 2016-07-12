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
				<li><a href="${ctx}/businessGroup"><span class="glyphicon glyphicon-home"></span></a></li>
				<li>空间管理</li>
				<li class="active">创建空间</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">创建空间</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#add_space" data-toggle="tab">创建空间</a></li>
						</ul>
						
						<div class="tab-content">
						 <div class="tab-pane fade in active" id="add_group">
						 <form class="form-horizontal" action="${ctx}/space/addSpace" method="post">
							<div class="form-group col-lg-6">
								<label class="col-lg-3 control-label">空间名：</label>
								<div class="col-lg-9">
								<input type="text" class="form-control " id="name" name ="name"></div>
							</div>
							<div class="form-group col-lg-6">
								<label class="col-lg-3 control-label">空间id：</label>
								<div class="col-lg-9"><input type="text" class="form-control " id="id" name="id"></div>
							</div>
							<div class="form-group col-lg-6">
								<label class="col-lg-3 control-label">空间大小：</label>
								<div class="col-lg-9"><input type="number" class="form-control "  id ="size" name ="size"></div>
							</div>
							<div class="form-group col-lg-6">
								<label class="col-lg-3 control-label">根目录：</label>
								<div class="col-lg-9"><input type="text" class="form-control "  id ="root" name ="root"></div>
							</div>
				
							<div class= "row col-lg-12"><p><br></p></div>
							<div class = "col-lg-4 col-xs-4"></div>
							<div class="form-group col-lg-4 col-xs-4">
								<input type="reset" value="重置" class="btn btn-default ">
								&nbsp;&nbsp;&nbsp;
								<input type="submit" value="保存" class="btn btn-primary ">
							</div>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>