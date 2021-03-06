<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="form-horizontal" action="${ctx}/folder/add" method="post">
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">目录号：</label>
		<div class="col-lg-9">
		<input type="text" class="form-control " id="id" name ="id"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">目录名：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="name" name="name"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">父节点：</label>
		<div class="col-lg-9"><input type="text" class="form-control "  id ="fatherId" name ="fatherId"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">存储空间id：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id = "storageId" name ="storageId"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">存储空间类型：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="storageType" name="storageType"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">是否为根节点：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="whetherRoot" name ="whetherRoot"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">创建者：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="creater" name = "creater"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">创建时间：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="createDate" name ="createDate"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">共享类型：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="shareType" name ="shareType"></div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">完整性：</label>
		<div class="col-lg-9"><input type="text" class="form-control " id="integrityType" name ="integrityType"></div>
	</div>
	<div class= "row col-lg-12"><p><br></p></div>
	<div class = "col-lg-4 col-xs-4"></div>
	<div class="form-group col-lg-4 col-xs-4">
		<input type="reset" value="重置" class="btn btn-default ">
		&nbsp;&nbsp;&nbsp;
		<input type="submit" value="保存" class="btn btn-primary ">
	</div>
</form>
