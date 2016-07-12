angular.module("msgbox", ["ui.bootstrap"])
.factory("$msgbox", function($rootScope, $modal, $q){
    var prompt_tpl = '<div class="modal-header" data-ng-show="{{title.length}}"> <h4 class="modal-title">{{title}}</h4></div><div class="modal-body text-center" style="font-size: 18px;word-break:break-all;"><br> {{message}}<br> <br> <button class="btn btn-primary" data-ng-click="ok($event)">确定</button> &nbsp;&nbsp; <button class="btn btn-default" data-ng-click="cancel($event)">取消</button> <br> &nbsp;</div>';
    var alert_tpl = '<div class="modal-header" data-ng-show="{{title.length}}"> <h4 class="modal-title">{{title}}</h4></div><div class="modal-body text-center" style="font-size: 18px;word-break:break-all;"><br> {{message}}<br> <br> <button class="btn btn-primary" data-ng-click="ok($event)">确定</button> <br> &nbsp; </div>';

    var scope = $rootScope.$new();
    
    return {
        show: function(msg, opts){
            var defer = $q.defer();
            opts = opts || {};
            var tpl = undefined;
            if(opts.type){
                if(opts.type=='alert'){
                    tpl = alert_tpl;
                }
                if(opts.type=='prompt'){
                    tpl = prompt_tpl;
                }
            } else {
                tpl = prompt_tpl;
            }
            var size = undefined;
            if(opts.size){
                size = opts.size;
            } else {
                size = 'sm';
            }
            $modal.open({
                template: tpl,
                scope: scope,
                controller: function($scope, $modalInstance){
                    $scope.title = opts.title;
                    $scope.message = msg;
                    
                    $scope.ok = function($event){
                        $event.preventDefault();
                        $modalInstance.close();
                        defer.resolve();
                    };
                    $scope.cancel = function($event){
                        $event.preventDefault();
                        $modalInstance.close();
                        defer.reject();
                    };
                },
                size: size,
                backdrop: "static",
                keyboard: false
            });
            return defer.promise;
        }
    }    
});

