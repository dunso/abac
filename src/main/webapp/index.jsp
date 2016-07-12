<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
 访问控制测试界面
 @author  chenbin
 @Date    20160113
 @Operate create
 @address Peking University
--%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html ng-app="acApp">
  <head>
    <base href="<%=basePath%>">
    <title>AccessControlTest</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="Access,Control,Test">
	<meta http-equiv="description" content="AccessControlTest">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/accesscontrol.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/strategy.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/policy.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui-bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/accesscontrol.js"></script>
	
	
  </head>
  
  <body>
  
  <div ng-controller="AccessControl" class="c-center">
    <h1><center> 基于属性的访问控制（ABAC）系统 </center></h1> <br/><br/>
 
    
    <ul id="myTab" class="nav nav-tabs">
   		<li class="active"><a href="#queryaccess" data-toggle="tab">查询权限</a></li>
    	<li><a href="#queryattrexpress" data-toggle="tab">查询属性表达式</a></li>
    	<li><a href="#querypolicy" data-toggle="tab">策略管理</a></li>
    	<li><a href="#insertpolicy" data-toggle="tab">增加策略</a></li>
	</ul>

  
    
    
	<div class= "tab-content">
	
		<!-- 查询特定权限 -->
	
		<div class="panel  panel-default tab-pane fade in active" id="queryaccess">
		  <div class="panel-heading " >
		    <h3 class="panel-title">查询特定权限</h3>
		  </div>
		  
		  <div class="panel-body">
		    <form name="queryaccessForm" class="form-inline" action="queryaccess" method="post">
		  		<div class="form-group">
		    		<label>Token</label>
		    		<input type="text" class="form-control" id="token"  name="token" value="1" ng-model="token" placeholder="">
		  		</div>
		  		<div class="form-group">
		    		<label>组ID</label>
		    		<input type="text" class="form-control" id="groupId" name="groupId" value="123" ng-model="groupId" placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>文件/目录ID</label>
		    		<input type="text" class="form-control " id="fileFolderId" name="fileFolderId" value="24325435435344f" ng-model="fileFolderId" placeholder="24325435435344f">
		  		</div>
		  		<div class="form-group m-b-md">
			  		<label>权限选择</label>
		          	<div class="m-t-sm">
		          		<select class="form-control" ng-model="privilege" ng-options="f for f in privileges"><option></option></select>
		            	<!--  <select name="privilege" ng-model="privilege" class="form-control m-b">
			              	<option>allowCreateFloder</option>
			              	<option>allowShareFloder</option>
			              	<option>allowDeleteFloder</option>
			              	<option>allowUploadFile</option>
			              	<option>allowDownloadFile</option>
			              	<option>allowDeleteFile</option>
		            	</select>
		            	-->
         			</div>
	  			</div>
	  			<br></br>
	  			<span >
	  				<button type="button" class="btn btn-md btn-primary no-border" ng-click="queryaccess(0)">查询特定权限 </button>
	  			</span>
	  			<span>
	  				<button type="button" class="btn btn-md btn-primary no-border" ng-click="queryaccess(1)">查询所有权限 </button>
	  			</span>
			</form>
			
		  </div>
		 </div>
		 
		 
		 
		 <!-- 查询指定策略 -->
		 
		 <div class="panel  panel-default tab-pane fade" id="queryattrexpress">
		  <div class="panel-heading " >
		    <h3 class="panel-title">查询属性表达式</h3>
		  </div>
		  
		  <div class="panel-body">
		    <form class="form-inline" action="queryattrexpress">
		  		<div class="form-group">
		    		<label>Token</label>
		    		<input type="text" class="form-control" id="token"  name="token" value="1" ng-model="token" placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>组ID</label>
		    		<input type="text" class="form-control" id="groupId" name="groupId" value="123" ng-model="groupId"  placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>文件/目录ID</label>
		    		<input type="text" class="form-control " id="fileFolderId" name="fileFolderId" value="24325435435344f" ng-model="fileFolderId"  placeholder="24325435435344f">
		  		</div>
	  			<br></br>
	  			<div class="m-t-md">
	  				<button type="button" class="btn btn-md btn-primary no-border" ng-click="queryAttrExpress()">查询属性表达式 </button>
	  			</div>
			</form>
			
		  </div>
		 </div>
		 
		 
		 
		 <!-- 查询策略 -->
		 
		 <div class="panel  panel-default tab-pane fade" id="querypolicy">
		  <div class="panel-heading " >
		    <h3 class="panel-title">查询策略</h3>
		  </div>
		  
		  <div class="panel-body">
		    <form class="form-inline" action="querypolicy" method="post">
		  		<div class="form-group">
		    		<label>Token</label>
		    		<input type="text" class="form-control" id="token"  name="token" value="1" ng-model="token" placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>组ID</label>
		    		<input type="text" class="form-control" id="groupId" name="groupId" value="123" ng-model="groupId" placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>文件/目录ID</label>
		    		<input type="text" class="form-control " id="fileFolderId" name="fileFolderId" value="24325435435344f" ng-model="fileFolderId" placeholder="24325435435344f">
		  		</div>
		  		<div class="form-group" style="margin-top:25px;">
		  			
	  				<button type="button" class="btn btn-md btn-primary no-border" ng-click="querypolicy(1)" style="margin-left:32px;">查询策略 </button>
	  			</div>
		  		<br></br>
		  		
			<div class="table-responsive">
                    <table class="table table-striped b-t b-light">
                        <tbody>
			<tr ng-repeat="p in policys">
			<td>
			    	<div class="form-inline col-sm-10" style="margin:15px -9px 0 -15px;float:left">
		    			<label>属性表达式</label>
		    			<input type="text" class="form-control" id="attrExpress" name="attrExpress" ng-model="p.attrExpress" value="#username='a'&(password='12' $ ty pe='2')$!userID = '1'#" placeholder="#username='a'&(password='12' $ ty pe='2')$!userID = '1'#"/>
		  			</div>
		  			<button type="button" class="btn btn-md btn-success no-border" ng-click="modifypolicy(p.table2id)" style="margin:40px 0 -5px 0;">修改策略 </button>
		  			<button type="button" class="btn btn-md btn-danger no-border" ng-click="deletepolicy(p.table2id)" style="margin:40px 0 -5px 0;">删除策略 </button>
			    	<div class="input-group col-md-12">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="createFloder" ng-true-value="1"  ng-false-value="0" ng-model="p.createFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="创建文件夹" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="deleteFloder"  ng-true-value="1"  ng-false-value="0" ng-model="p.deleteFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="删除文件夹" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="moveFloder" ng-true-value="1"  ng-false-value="0" ng-model="p.moveFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="移动文件夹"  aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="renameFloder" ng-true-value="1"  ng-false-value="0" ng-model="p.renameFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="重命名文件夹" aria-label="...">
					    
			    	</div>
			    
			    	<div class="input-group col-md-12">
			    	      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="createFile" ng-true-value="1"  ng-false-value="0" ng-model="p.createFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="创建文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="deleteFile" ng-true-value="1"  ng-false-value="0" ng-model="p.deleteFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="删除文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="moveFile" ng-true-value="1"  ng-false-value="0" ng-model="p.moveFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="移动文件" aria-label="...">
						  <span class="input-group-addon">
					        <input type="checkbox" value="1" name="renameFile"  ng-true-value="1"  ng-false-value="0" ng-model="p.renameFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="重命名文件"  aria-label="...">
					      
			    		  
					      
					</div> 
					      
					<div class="input-group col-md-12">
						  <span class="input-group-addon">
					        <input type="checkbox" value="1" name="uploadFile" ng-true-value="1"  ng-false-value="0" ng-model="p.uploadFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="上传文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="downloadFile" ng-true-value="1"  ng-false-value="0" ng-model="p.downloadFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="下载文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="operateWays"  ng-true-value="1"  ng-false-value="0" ng-model="p.operateWays" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="支持加密"  aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="integrity" ng-true-value="1"  ng-false-value="0" ng-model="p.integrity" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value=支持完整性 aria-label="...">
			    	</div>
			    
				 
		  		
		  		<div class="clearfix"></div>
		  		</td>
		  	</tr>
		  	</tbody>
		  	</table>
		  	</div>
		  	
	  			
	  			
			</form>
			
		  </div>
		 </div>
		 
		 <!-- 增加策略 -->
		 
		 <div class="panel  panel-default tab-pane fade" id="insertpolicy">
		  <div class="panel-heading " >
		    <h3 class="panel-title">增加策略</h3>
		  </div>
		  
		  <div class="panel-body">
		    <form class="form-inline" action="insertpolicy" method="post">
		  		<div class="form-group">
		    		<label>Token</label>
		    		<input type="text" class="form-control" id="token"  name="token" value="1" ng-model="token" placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>组ID</label>
		    		<input type="text" class="form-control" id="groupId" name="groupId" value="123" ng-model="groupId" placeholder="1">
		  		</div>
		  		<div class="form-group">
		    		<label>文件/目录ID</label>
		    		<input type="text" class="form-control " id="fileFolderId" name="fileFolderId" value="24325435435344f" ng-model="fileFolderId" placeholder="24325435435344f">
		  		</div>
		  		<br></br>
		  		
				
			    	<div class="input-group col-md-12">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="createFloder" ng-true-value="1"  ng-false-value="0" ng-model="createFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="创建文件夹" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="deleteFloder"  ng-true-value="1"  ng-false-value="0" ng-model="deleteFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="删除文件夹" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="moveFloder" ng-true-value="1"  ng-false-value="0" ng-model="moveFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="移动文件夹"  aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="renameFloder" ng-true-value="1"  ng-false-value="0" ng-model="renameFloder" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="重命名文件夹" aria-label="...">
					    
			    	</div>
			    
			    	<div class="input-group col-md-12">
			    	      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="createFile" ng-true-value="1"  ng-false-value="0" ng-model="createFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="创建文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="deleteFile" ng-true-value="1"  ng-false-value="0" ng-model="deleteFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="删除文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="moveFile" ng-true-value="1"  ng-false-value="0" ng-model="moveFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="移动文件" aria-label="...">
						  <span class="input-group-addon">
					        <input type="checkbox" value="1" name="renameFile"  ng-true-value="1"  ng-false-value="0" ng-model="renameFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="重命名文件"  aria-label="...">
					      
			    		  
					      
					</div> 
					      
					<div class="input-group col-md-12">
						  <span class="input-group-addon">
					        <input type="checkbox" value="1" name="uploadFile" ng-true-value="1"  ng-false-value="0" ng-model="uploadFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="上传文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="downloadFile" ng-true-value="1"  ng-false-value="0" ng-model="downloadFile" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="下载文件" aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="operateWays"  ng-true-value="1"  ng-false-value="0" ng-model="operateWays" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value="支持加密"  aria-label="...">
					      <span class="input-group-addon">
					        <input type="checkbox" value="1" name="integrity" ng-true-value="1"  ng-false-value="0" ng-model="integrity" aria-label="...">
					      </span>
					      <input type="text" class="form-control" value=支持完整性 aria-label="...">
			    	</div>
			    
				 <div class="form-inline col-md-10" style="margin:15px 0 0 -15px">
		    		<label>属性表达式</label>
		    		<input type="text" class="form-control" id="attrExpress" name="attrExpress" ng-model="attrExpress" value="#username='a'&(password='12' $ ty pe='2')$!userID = '1'#" placeholder="#username='a'&(password='12' $ ty pe='2')$!userID = '1'#">
		  		</div>
		  		
		  		<div class="clearfix"></div>
		  		
		  		<div class="form-group" style="margin-top:15px;">
	  				<button type="button" class="btn btn-md btn-primary no-border" ng-click="conflictdetection()">冲突检测 </button>
	  			</div>
	  			<div class="form-group" style="margin-top:15px;">
	  				<button type="button" class="btn btn-md btn-primary no-border" ng-click="insertpolicy()">增加策略 </button>
	  			</div>
	  			
	  			
			</form>
			
		  </div>
		 </div>
		
	</div>
    
 </div>   
  <!-- 
    <center>
   	<%--查询特定权限(方法一)：使用REST风格GET请求 --%>
    <a href="queryaccess/1/123/24325435435344f/allowShareFloder">GET方式查询特定权限</a><br/><br/>
    <%--查询特定权限(方法二)：使用POST请求(JQuery方式) --%>
    <input type="button" value="POST方式查询特定权限" id="queryaccessbypost"/><br/><br/>
    <%--查询所有权限(方法一)：使用REST风格GET请求 --%>
    <a href="queryaccessall/1/123/24325435435344f">GET方式查询所有权限</a><br/><br/>
    <%--查询所有权限(方法二)：使用POST请求（表单方式） --%>
    <form action="queryaccessall"  method="post">
    	<table border="1">
		<tr><td>userId: <input type="text" name="userId" value="1"/><br/></td></tr>
		<tr><td>groupId: <input type="text" name="groupId" value="123"/><br/></td></tr>
		<tr><td>fileFolderId:	  <input type="text" name="fileFolderId" value="24325435435344f"/><br/></td></tr>
		<tr><td><input type="submit" name="submit" value="POST方式查询所有权限" /></td></tr>
		</table>
    </form>
    
    <br/><br/>
    <%--查询策略(方法一)：使用REST风格GET请求 --%>
    <a href="querypolicy/1/123/24325435435344f">GET方式查询策略</a><br/><br/>
    <%--查询策略(方法二)：使用POST请求(JQuery方式) --%>
    <input type="button" value="POST方式查询策略" id="querypolicy"/><br/><br/>
    
    <input type="button" value="插入一条策略" id="insertpolicy"/><br/><br/>
    
    <input type="button" value="删除一条策略" id="deletepolicy"/><br/><br/>
    
    <form action="conflictdetection"  method="post">
    	<table border="1">
		<tr><td>环境属性: <input type="text" name="" value="8:00<=loginTime<=17:00"/><br/></td></tr>
		<tr><td>组ID: <input type="text" name="groupId" value="123"/><br/></td></tr>
		<tr><td>文件/目录ID:	  <input type="text" name="fileFolderId" value="24325435435344f"/><br/></td></tr>
		<tr><td>主体属性:	  <input type="text" name="propertyExpression" value="#username='a'#"/><br/></td></tr>
		
		<tr><td>	  <input type="text" name="allowCreateFloder" value="1"/><br/></td></tr>
		<tr><td>	  <input type="text" name="allowShareFloder" value="0"/><br/></td></tr>
		<tr><td>	  <input type="text" name="allowDeleteFloder" value="1"/><br/></td></tr>
		<tr><td>	  <input type="text" name="allowUploadFile" value="0"/><br/></td></tr>
		<tr><td>	  <input type="text" name="allowDownloadFile" value="1"/><br/></td></tr>
		<tr><td>	  <input type="text" name="allowDeleteFile" value="1"/><br/></td></tr>
		
		<tr><td><input type="submit" name="submit" value="冲突检测" /></td></tr>
		</table>
    </form>
    
    
    <br/><br/>
   
    <input type="button" value="冲突检测" id="conflictDetection"/><br/><br/>

    </center>
  -->     
  </body>
</html>
