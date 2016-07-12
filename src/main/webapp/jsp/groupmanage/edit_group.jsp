<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="form-horizontal" action="${ctx}/businessGroup/addGroup" method="post">	
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">群组名：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="name" name="name"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">群组空间大小(MB)：</label>
		<div class="col-lg-9">
		<input type="text" class="form-control " id="storageSize" name ="storageSize"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">管理员属性：</label>
		<div class="col-lg-9"><input type="text" class="form-control "  id ="adminAttrs" name ="adminAttrs"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">成员属性：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id = "uAttrs" name ="uAttrs"></div>
	</div>
	<!-- <div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">空间ID：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="storageId" name="storageId"></div>
	</div> -->
	<div class= "row col-lg-12"><p><br></p></div>
	<div class = "col-lg-4 col-xs-4"></div>
	<div class="form-group col-lg-4 col-xs-4">
		<input type="reset" value="重置" class="btn btn-default ">
		&nbsp;&nbsp;&nbsp;
		<input type="submit" value="保存" class="btn btn-primary ">
	</div>
</form>
