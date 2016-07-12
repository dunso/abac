<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form class="form-horizontal" action="${ctx}/freegroup/respondmess" method="post">

	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">消息ID：</label>
	<div class="col-lg-9"><input type="text" class="form-control " id="mess_id" name="mess_id"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">响应(0：拒绝， 1：同意)：</label>
		<div class="col-lg-9"><input type="text" class="form-control "  id ="state" name ="state"></div>
	</div>
	<div class= "row col-lg-12"><p><br></p></div>
	<div class = "col-lg-4 col-xs-4"></div>
	<div class="form-group col-lg-4 col-xs-4">
		<input type="reset" value="重置" class="btn btn-default ">
		&nbsp;&nbsp;&nbsp;
		<input type="submit" value="确定" class="btn btn-primary ">
	</div>
</form>
</body>
</html>