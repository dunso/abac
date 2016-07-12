<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="t_form" class="form-horizontal" action="${ctx}/user/teacher" method="post" onsubmit="javascript:submitForm(this,'t')">
	<div class="form-group col-lg-6" id="div_id_t">
		<label class="col-lg-3 control-label">教师号：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="id" name="id" onchange="javascript:checkusr(this.value,'t')"
				pattern="^[A-Za-z0-9]+$" required="required" placeholder="只能输入数字和字母" value="${user.id }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">姓名：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="name" name="name"
				required="required" value="${user.name }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">年龄：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="age" name="age"
				value="${user.age }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">职称：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="title" name="title"
				value="${user.title }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">职务：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="duty" name="duty"
				value="${user.duty }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">所属院系：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="department"
				name="department" value="${user.department }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">所属研究组：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="studyGroup"
				name="studyGroup" value="${user.studyGroup }">
		</div>
	</div>
	<div class="form-group col-lg-6">
		<label class="col-lg-3 control-label">教授课程：</label>
		<div class="col-lg-9">
			<input type="text" class="form-control " id="courses" name="courses"
				value="${user.courses}">
		</div>
	</div>
	<div class="row" id="formButton">
		<div class="row col-lg-12">
			<p>
				<br>
			</p>
		</div>
		<div class="col-lg-4 col-xs-4"></div>
		<div class="form-group col-lg-4 col-xs-4">
			<input type="reset" value="重置" class="btn btn-default ">
			&nbsp;&nbsp;&nbsp; <input type="submit" value="保存"
				class="btn btn-primary ">
		</div>
	</div>
</form>