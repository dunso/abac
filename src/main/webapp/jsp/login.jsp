<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="content" class="container">
		<form class="form-horizontal" action="/sshdemo/user/adminLogin" style="margin: 20px auto; width: 430px;" method="post">
			<fieldset>
				<legend>
					用户登录
				</legend>
				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">用户名</label>
					<div class="col-sm-6">
						<input type="text" name="id" class="form-control" id="id">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码</label>
					<div class="col-sm-6">
						<input type="password" name="password" class="form-control" id="password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-6">
						<input type="submit" class="btn btn-primary" value="登录"/>
						
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	
</body>
</html>