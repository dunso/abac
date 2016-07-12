<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="form-horizontal" action="${ctx}/file/add" method="post">
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">文件号：</label>
		<div class="col-lg-9">
		<input type="text" class="form-control " id="id" name ="id"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">文件名：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="name" name="name"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">文件夹：</label>
		<div class="col-lg-9"><input type="number" class="form-control "  id ="folder" name ="folder"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">所有者：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id = "owner" name ="owner"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">上传时间：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="uploadtime" name="uploadtime"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">扩展名：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="expname" name ="expname"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">云端路径：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="cloudpath" name = "cloudpath"></div>
	</div>
	<div class= "row col-lg-12"><p><br></p></div>
	<div class = "col-lg-4 col-xs-4"></div>
	<div class="form-group col-lg-4 col-xs-4">
		<input type="reset" value="重置" class="btn btn-default ">
		&nbsp;&nbsp;&nbsp;
		<input type="submit" value="保存" class="btn btn-primary ">
	</div>
</form>