<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">

	<ul class="nav menu">
		<li class="parent "><a href="#sub-item-1" data-toggle="collapse"> <span
				class="glyphicon glyphicon-list"></span> 用户属性管理 <span
				data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em
					class="glyphicon glyphicon-s glyphicon-plus"></em></span>
		</a>
			<ul class="children collapse" id="sub-item-1">
				<li><a class="" href="${ctx}/user/import"> <span
						class="glyphicon glyphicon-share-alt"></span> 导入用户信息
				</a></li>
				<li><a class="" href="${ctx}/user/list"> <span
						class="glyphicon glyphicon-share-alt"></span> 维护用户信息
				</a></li>
			</ul></li>
			
			<li class="parent "><a href="#"> <span
				class="glyphicon glyphicon-list"></span> 业务群组管理 <span
				data-toggle="collapse" href="#sub-item-2" class="icon pull-right"><em
					class="glyphicon glyphicon-s glyphicon-plus"></em></span>
		</a>
			<ul class="children collapse" id="sub-item-2">
				<li><a class="" href="${ctx}/jsp/groupmanage/add_group.jsp"> <span
						class="glyphicon glyphicon-share-alt"></span> 创建群组</a>
				</a></li>
				<li><a class="" href="${ctx}/businessGroup/getList"> <span
						class="glyphicon glyphicon-share-alt"></span> 群组列表
				</a></li>
			</ul></li>
	</ul>
</div>
<!--/.sidebar-->