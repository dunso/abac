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
		<div class = "row"><div class ="col-lg-12">
		
			<div class="alert alert-warning" role="alert">
				上传前请确保上传的文件符合格式要求，参见<a href="${ctx}/template/Student_Template.xlsx" >学生信息模板</a>
				或者<a href="${ctx}/template/Teacher_Template.xlsx">教师信息模板</a>
			</div>
		</div></div>
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
								
								<div class="row">
								<form class="form-horizontal" enctype="multipart/form-data" id="Up_S" action="${ctx }/user/import/0" method="post" >
									<label class="col-lg-3 control-label">上传文件 :</label>
									<div class="col-lg-6"><input type="file" class="form-control" id="file" name="file"></div>
									<div class="col-lg-3"><input type ="submit" value="提交" class=" btn btn-primary" ></div>
								</form>
								</div>
							</div>
							<div class="tab-pane fade" id="add_teacher">
								<div class="row">
								<form class="form-horizontal" enctype="multipart/form-data" id="Up_T" action="${ctx }/user/import/1" method="post" >
									<label class="col-lg-3 control-label">上传文件 :</label>
									<div class="col-lg-6"><input type="file" class="form-control" id="file" name="file"></div>
									<div class="col-lg-3"><input type ="submit" value="提交" class=" btn btn-primary" ></div>
								</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class = "row" id="result">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="col-lg-6 col-md-6 col-xs-6">下表为上传保存的数据</div>
						<div class="col-lg-4 col-md-4 col-xs-4"></div>
						<div class="col-lg-2 col-md-2 col-xs-2"><button class="btn btn-primary" onclick="javascript:$('#result').hide();">关闭</button></div>
					</div>
					<div class="panel-body">
						<table  id="S_result"></table>
						<table  id="T_result"></table>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>
<script src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript">  
	$("#result").hide();
	$("#T_result").hide();
    $(function() {  
        $("#Up_S").submit(function(){
        	$("#result").hide();
        	$("T_result").hide();
            $(this).ajaxSubmit({  
                type:"post",    
                url:"${ctx}/user/import/0",  
                success:function(result){
                	$("#result").show();
                	$("#S_result").show();
    				$("#S_result").bootstrapTable({
    					columns: [{
    				        field: 'id',
    				        title: '学号'
    				    }, {
    				        field: 'name',
    				        title: '姓名'
    				    }, {
    				        field: 'age',
    				        title: '年龄'
    				    },{
    				    	field: 'teacherID',
    				        title: '导师'
    				    },{
    				    	field: 'department',
    				        title: '所属院系'
    				    },{
    				    	field: 'academy',
    				        title: '所属学苑'
    				    },{
    				    	field: 'studygroup',
    				        title: '所属研究组'
    				    },{
    				    	field: 'courses',
    				        title: '所选课程'
    				    }],
    				    data:result  
    				});  
                }
            });  
            return false;   
        });       
        $("#Up_T").submit(function(){
        	$("#result").hide();
        	$("#S_result").hide();
            $(this).ajaxSubmit({  
                type:"post",    
                url:"${ctx}/user/import/1",  
                success:function(result){
                	$("#result").show();
                	$("#T_result").show();
    				$("#T_result").bootstrapTable({
    					columns: [{
    				        field: 'id',
    				        title: '教师号'
    				    }, {
    				        field: 'name',
    				        title: '姓名'
    				    }, {
    				        field: 'age',
    				        title: '年龄'
    				    },{
    				    	field: 'title',
    				        title: '职称'
    				    },{
    				    	field: 'duty',
    				        title: '职务'
    				    },{
    				    	field: 'department',
    				        title: '所属院系'
    				    },{
    				    	field: 'studyGroup',
    				        title: '所属研究组'
    				    },{
    				    	field: 'courses',
    				        title: '教授课程'
    				    }],
    				    data:result
    				});  
                }
            });  
            return false;   
        });       
    });  
      
</script>  