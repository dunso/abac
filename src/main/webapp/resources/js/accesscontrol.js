angular.module('acApp', ['msgbox'])
.factory('ac', function($http,$rootScope) {
    //var server_url = "http://localhost:9090/";
	var server_url = "http://120.27.50.173:8080/abac/";
    return {
      post: function(api, data, success, error){   
        var url = server_url+api;
        var req = {
          'method':'POST',
          'url':url,
          'data':data
        }
        $http(req).success(function(res){
          if(success!=undefined){
            success(res);
          }

        }).error(function(res){
          if(error != undefined){
            error(res);
          }
        });
      },
     
      get: function(api, success, error){
    	  var url = server_url+api;
        $http.get(url).success(function(res){
          if(success!=undefined){
            success(res);
          }
        }).error(function(res){
          if(error!=undefined){
            error(res);
          }
        });
      },
    };
  })
  .config(function($httpProvider) {
	  $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
	  $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
 
	  $httpProvider.defaults.transformRequest = [function(data) {
    
	    var param = function(obj) {
	      var query = '';
	      var name, value, fullSubName, subName, subValue, innerObj, i;
	  
	      for (name in obj) {
	        value = obj[name];
	  
	        if (value instanceof Array) {
	          for (i = 0; i < value.length; ++i) {
	            subValue = value[i];
	            fullSubName = name + '[' + i + ']';
	            innerObj = {};
	            innerObj[fullSubName] = subValue;
	            query += param(innerObj) + '&';
	          }
	        } else if (value instanceof Object) {
	          for (subName in value) {
	            subValue = value[subName];
	            fullSubName = name + '[' + subName + ']';
	            innerObj = {};
	            innerObj[fullSubName] = subValue;
	            query += param(innerObj) + '&';
	          }
	        } else if (value !== undefined && value !== null) {
	          query += encodeURIComponent(name) + '='
	              + encodeURIComponent(value) + '&';
	        }
	      }
	  
	      return query.length ? query.substr(0, query.length - 1) : query;
	    };
  
	    return angular.isObject(data) && String(data) !== '[object File]'? param(data): data;
	  }];
  })
  .controller('AccessControl', ['$scope','$msgbox','ac', function($scope,$msgbox,ac) {
	  
	  
	  
	  $scope.token = "As6VkJPshb34jZ255nXEKw==";
      $scope.groupId = "1";
      $scope.fileFolderId = "014634826261380d";
      $scope.privileges = ['createFloder', 'renameFloder', 'deleteFloder', 'uploadFile', 'downloadFile', 'deleteFile'];
      $scope.privilege =$scope.privileges[0];
      $scope.strategyID = "0";
      $scope.createFloder = "0";
      $scope.deleteFloder = "0";
      $scope.renameFloder = "0";
      $scope.moveFloder = "0";
      $scope.uploadFile = "0";
      $scope.downloadFile = "0";
      $scope.createFile= "0";
      $scope.deleteFile = "0";
      $scope.renameFile = "0";
      $scope.moveFile = "0";
      $scope.operateWays = "0";
      $scope.integrity = "0";
      $scope.attrExpress = "#projectId='1'&(age>'30' $ ty pe='0')$!userID = '1'#";
      
      $scope.policys = [];
	 
      $scope.queryaccess = function(type){
    	  /*
    	  var data = {
	    		"token":$scope.token,
	    	  	"groupId":$scope.groupId,
	    	  	"fileFolderId":$scope.fileFolderId,
	    	  	"privilege":$scope.privilege
    	  };
    	  ac.post("/queryaccess", data, function(response){
              if(response != undefined){
            	  $msgbox.show(JSON.stringify(response),{type:'alert'});
              }
          }, function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          });
          */
    	  
    	  var api = "/queryaccess?token="+$scope.token+"&groupId="+$scope.groupId+"&fileFolderId="+$scope.fileFolderId;
    	  if(type == "0") api += "&privilege="+$scope.privilege;
    	
    	  ac.get(api,function(response){
			  if(response != undefined) {
				  $msgbox.show(response.data, {type: 'alert'});
			  }
          },function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          }); 
    	
    	  
    	   
      }
      
      $scope.queryaccessall = function(){
    	  var data = {
  	    		"token":$scope.token,
  	    	  	"groupId":$scope.groupId,
  	    	  	"fileFolderId":$scope.fileFolderId
      	  };
    	  
    	  ac.post("/queryaccessall", data, function(response){
              if(response != undefined){
				  if("" == response.data){
					  $msgbox.show(JSON.stringify("无匹配数据"),{type:'alert'});
				  }else{
					  $msgbox.show(JSON.stringify(response.data),{type:'alert'});
				  }

              }
          }, function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          });
          
    	  /*
    	  ac.get("/queryaccess?token="+$scope.token+"&groupId="+$scope.groupId+"&fileFolderId="+$scope.fileFolderId,function(response){
    		  $msgbox.show(JSON.stringify(response),{type:'alert'});
    		 
          },function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          });
          */
      }
      
      $scope.queryAttrExpress = function(){
    	  var data = {
    	    		"token":$scope.token,
    	    	  	"groupId":$scope.groupId,
    	    	  	"fileFolderId":$scope.fileFolderId
          };
    	  
    	  ac.post("/queryattrexpress", data, function(response){
              if(response != undefined){
				  if("" == response.data){
					  $msgbox.show(JSON.stringify("无匹配数据"),{type:'alert'});
				  }else{
					  $msgbox.show(JSON.stringify(response.data),{type:'alert'});
				  }
              }
          }, function(response){
        	  $msgbox.show("服务器繁忙，请稍后再试！",{type:'alert'});
          });
          
    	  /*
    	  ac.get("/queryattrexpress?token="+$scope.token+"&groupId="+$scope.groupId+"&fileFolderId="+$scope.fileFolderId,function(response){
    		  $msgbox.show(JSON.stringify(response),{type:'alert'});
    		 
          },function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          });
          */
      }
      
      
      $scope.querypolicy = function(flag){
    	  var data = {
    	    		"token":$scope.token,
    	    	  	"groupId":$scope.groupId,
    	    	  	"fileFolderId":$scope.fileFolderId
          };
    	  ac.post("/querypolicy", data, function(response){
              if(response != undefined){
				  if(response.code == 50000 || response.code == 40000){
					  $msgbox.show(response.data,{type:'alert'});
				  }else{
					  if("" == response.data){
						  $msgbox.show(JSON.stringify("无匹配数据"),{type:'alert'});
					  }else{
						  $scope.policys = response.data;
					  }

				  }
              }
          }, function(response){
        	  $msgbox.show("系统繁忙，请稍后再试！",{type:'alert'});
          });
      }
      
      $scope.modifypolicy = function(table2id){
   	  for(var i = 0 ;i<$scope.policys.length; i++ ){
    		  if($scope.policys[i].table2id == table2id){
    			  var data = {
    					"token":$scope.token,
    		  	    	"groupId":$scope.groupId,
    		  	    	"fileFolderId":$scope.fileFolderId,
			    	  	"table2id":table2id,
			    	  	"createFloder":$scope.policys[i].createFloder,
		  	    	  	"renameFloder":$scope.policys[i].renameFloder,
		  	    	    "moveFloder":$scope.policys[i].moveFloder,
		  	    	    "deleteFloder":$scope.policys[i].deleteFloder,
			    	  	"uploadFile":$scope.policys[i].uploadFile,
			    	  	"downloadFile":$scope.policys[i].downloadFile,
			    	  	"createFile":$scope.policys[i].createFile,
			    	  	"deleteFile":$scope.policys[i].deleteFile,
		  	    	  	"moveFile":$scope.policys[i].moveFile,
		  	    	    "renameFile":$scope.policys[i].renameFile,
		  	    	    "operateWays":$scope.policys[i].operateWays,
			    	  	"integrity":$scope.policys[i].integrity,
			    	  	"attrExpress":$scope.policys[i].attrExpress
    		     };
    		  }
    	  }
   	  
   	  	  console.log(data);
    	  ac.post("/modifypolicy", data, function(response){
              if(response != undefined){
				  if("" == response.data){
					  $msgbox.show(JSON.stringify("无匹配数据"),{type:'alert'});
				  }else{
					  $msgbox.show(JSON.stringify(response.data),{type:'alert'});
				  }
              }
          }, function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          });
      }
      
      $scope.insertpolicy = function(){
    	  
    	  var data = {
	  	    		"token":$scope.token,
	  	    	  	"groupId":$scope.groupId,
	  	    	  	"fileFolderId":$scope.fileFolderId,
	  	    	  	"createFloder":$scope.createFloder,
	  	    	  	"renameFloder":$scope.renameFloder,
	  	    	    "moveFloder":$scope.moveFloder,
	  	    	    "deleteFloder":$scope.deleteFloder,
		    	  	"uploadFile":$scope.uploadFile,
		    	  	"downloadFile":$scope.downloadFile,
		    	  	"createFile":$scope.createFile,
		    	  	"deleteFile":$scope.deleteFile,
	  	    	  	"moveFile":$scope.moveFile,
	  	    	    "renameFile":$scope.renameFile,
	  	    	    "operateWays":$scope.operateWays,
		    	  	"integrity":$scope.integrity,
		    	  	"attrExpress":$scope.attrExpress
	      };
  		  ac.post("/insertpolicy", data, function(response){
	          if(response != undefined){
				  if("" == response.data){
					  $msgbox.show(JSON.stringify("无匹配数据"),{type:'alert'});
				  }else{
					  $msgbox.show(JSON.stringify(response.data),{type:'alert'});
				  }
	          }
  		  }, function(response){
  			  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
  		  }); 
      }
      
      
      $scope.conflictdetection = function(){
    	  var data = {
    			"token":$scope.token,
  	    	  	"groupId":$scope.groupId,
  	    	  	"fileFolderId":$scope.fileFolderId,
  	    	  	"createFloder":$scope.createFloder,
  	    	  	"renameFloder":$scope.renameFloder,
  	    	    "moveFloder":$scope.moveFloder,
  	    	    "deleteFloder":$scope.deleteFloder,
	    	  	"uploadFile":$scope.uploadFile,
	    	  	"downloadFile":$scope.downloadFile,
	    	  	"createFile":$scope.createFile,
	    	  	"deleteFile":$scope.deleteFile,
  	    	  	"moveFile":$scope.moveFile,
  	    	    "renameFile":$scope.renameFile,
  	    	    "operateWays":$scope.operateWays,
	    	  	"integrity":$scope.integrity,
	    	  	"attrExpress":$scope.attrExpress
  		  };
    	  ac.post("/conflictdetection", data, function(response){
              if(response != undefined){
            	  if(JSON.stringify(response.data) == "{}"){
            		  $msgbox.show("恭喜您，无冲突！",{type:'alert'});
            	  }else{
					  if("" == response.data){
						  $msgbox.show(JSON.stringify("无匹配数据"),{type:'alert'});
					  }else{
						  $msgbox.show(JSON.stringify(response.data),{type:'alert'});
					  }
            	  }
            	  return response;
              }
          }, function(response){
        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
          });
    	  
      }
      
      $scope.deletepolicy =function(table2id){
    	  $msgbox.show("确定要删除吗?").then(function(){
    		  var data = {
    	    			"table2id" : table2id,  
    	    			"token": $scope.token,
    	    			"fileFolderId":$scope.fileFolderId
    	    	  }
    	    	  ac.post("/deletepolicy", data, function(response){
    	    		  
    	              if(response != undefined){
    	            	  $msgbox.show(JSON.stringify(response.data),{type:'alert'});
    	            	  $scope.querypolicy(0);
    	              }
    	          }, function(response){
    	        	  $msgbox.show("服务器正忙,请稍后重试!",{type:'alert'});
    	          });
          },function(){

          });  
      }
      
      
    }]);
    
   








$(function(){
	$("#queryaccessbypost").click(function(){
		var url = "queryaccess";
		var args = {
			"userId":"1",
			"groupId":"123",
			"fileFolderId":"24325435435344f",
			"privilege":"allowRenameFloder"
		}
		$.post(url,args,function(data){
			alert(data);
		})
		return false;
	});
})

$(function(){
	$("#querypolicy1").click(function(){
		var url = "querypolicy";
		var args = {
			"userId":"1",
			"groupId":"123",
			"fileFolderId":"24325435435344f",
		};
		$.post(url,args,function(data){
			alert(data);
		});
		return false;
	});
})


/*
$(function(){
	$("#insertpolicy1").click(function(){
		var url = "insertpolicy1";
		var args = {
			"userId":"1",
			"groupId":"124",
			"fileFolderId":"24354395343446f",
			"allowCreateFloder":"0",
			"allowShareFloder":"1",
			"allowDeleteFloder":"0",
			"allowUploadFile":"1",
			"allowDownloadFile":"0",
			"allowDeleteFile":"1",
			"operateWays":"1",
			"integrity":"1",
			"propertyExpression":"#username='a'&(password='12' $ ty pe='1')$!userID = '1'#",
		};
		$.post(url,args,function(data){
			
		});
		//return false;
	});
})
*/




$(function(){
	$("#deletepolicy1").click(function(){
		var url = "deletepolicy";
		var args = {
			"policyId":"12"
		};
		$.post(url,args,function(data){
			alert(data);
		});
		return false;
	});
})


$(function(){
	$("#conflictDetection").click(function(){
		
		var url = "conflictdetection";
		var args = {
				"groupId":"123",
				"fileFolderId":"24325435435344f",
				"propertyExpression":"#username=\'a\'#",
				"allowCreateFloder": 1	,
				"allowRenameFloder":0,
				"allowDeleteFloder":1,
				"allowUploadFile":0,
				"allowDownloadFile":1,
				"allowDeleteFile":1
		};
		$.post(url,args,function(data){
			alert(data);
		});
		return false;
	});
})

function queryaccess1(v){
	if(v==0){
		document.queryaccessForm.action="queryaccess";
	}else{
		document.queryaccessForm.action="queryaccessall";
	}
	queryaccessForm.submit();
} 
