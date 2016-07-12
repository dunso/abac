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
				<li><a href="${ctx }/user">用户属性管理</a></li>
				<li class="active">添加用户</li>
			</ol>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">添加用户</h3>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#add_user" data-toggle="tab">添加学生</a></li>
							<li><a href="#add_teacher" data-toggle="tab">添加教师</a></li>
						</ul>
						
						<div class="tab-content">
							<div class="tab-pane fade in active" id="add_user">
								<%@include file="edit_student.jsp" %>
							</div>
							<div class="tab-pane fade" id="add_teacher">
								<%@include file="edit_teacher.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>
<script>
function checkusr(userID,type){
	if(userID!= null &&userID!=""){
		$.ajax({
			url:"${ctx}/user/isExist/"+userID,
			type:"GET",
			dataType: "json",
			success:function(data){
				if(data.isExist==true){
					$("#id").focus();
					$("#id").val("");
					$("#id").attr("placeholder","用户已经存在");
					$("#div_id_"+type).attr("class","form-group col-lg-6 has-error");
					return false;
				}
				else
					$("#div_id_"+type).attr("class","form-group col-lg-6");
				return true;
			}
		});
	}
	else{
		$("#id").attr("placeholder","输入不能为空");
		$("#div_id_"+type).attr("class","form-group col-lg-6 has-error");
		return false;
	}
}
function submitForm(fom,type){
	var id = fom.find("#id").val();
	return checkusr(id,type);
}
</script>