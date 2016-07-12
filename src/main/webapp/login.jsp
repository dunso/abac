<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html>
	<head>
	<title>用户登录</title>
	<%@include file="/common/meta.jsp"%>
	</head>
	<body>
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">登录</div>
					<div class="panel-body">
						<form action="${ctx}/user/adminLogin" method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="用户账号" name="id" id = "id" >
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="密码" name="password"  id ="password" type="password" value="">
								</div>
								<input type="submit" class="btn btn-primary" value="登录"/>
							</fieldset>
						</form>
					</div>
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->	
	
	</body>


</html>