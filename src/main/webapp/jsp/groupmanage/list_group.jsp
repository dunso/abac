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
				<li><a href="${ctx }/user"><span class="glyphicon glyphicon-home"></span></a></li>
				<li>业务群组管理</li>
				<li class="active">群组列表</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">群组列表</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#add_group" data-toggle="tab">群组列表</a></li>
						</ul>
						
						<div class="tab-content">
							<div class="tab-pane fade in active" id="add_group">
								<form class="form-horizontal" action="${ctx}/user/student" method="post">
		
									<div class="row">
											<div class="col-lg-12">
												<div class="panel">
													<div class="panel-body">
														<table data-toggle="table" data-url="${ctx}/businessGroup/data" >
														    <thead>
														    <tr>								   
														    <!--   <th data-field="id" data-sortable="true">群组ID</th>  -->  
														        <th data-field="name"  data-sortable="true">群组名</th>
														        <th data-field="adminAttr" data-sortable="true">管理员属性</th>
														        <th data-field="uAttr" data-sortable="true">用户属性</th>
														    <!--  <th data-field="storageId" data-sortable="true">空间ID</th>  -->
														        <th data-field="ctime" data-sortable="true">创建时间</th>
														        <th data-field="utime" data-sortable="true">更改时间</th>
														        <th data-field="operation" data-sortable="true">操作</th>
														    </tr>
																			  
														    </thead>
														</table>
													</div>
												</div>
											</div>
										</div><!--/.row-->	
									
									<div class= "row col-lg-12"><p><br></p></div>
									<div class = "col-lg-4 col-xs-4"></div>
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
