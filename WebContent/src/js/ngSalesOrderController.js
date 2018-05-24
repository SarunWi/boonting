angular.module('boontingApp')
    .controller('salesorderCtrl', ['$q', 'httpFactory','$mdDialog', function($q, httpFactory,$mdDialog) {
        var ctrl = this;
        ctrl.wrapper = {
            salesOrders: [],
            displaySalesOrders: []
        }
        var init = function() {
            httpFactory.getSalesOrder().then(function(result) {
                if (result) {
                    ctrl.wrapper.salesOrders = result;
                    ctrl.wrapper.displaySalesOrders = result;
                }
            })
        }
        ctrl.showAdvanced = function(ev) {
            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'src/modalTemplate/salesOrderDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: false // Only for -xs, -sm breakpoints.
                })
                .then(function(salesOrder) {
                    ctrl.saveSalesOrder(salesOrder);
                }, function() {
                    console.log('cancel');
                });
        };
        ctrl.saveSalesOrder = function(salesOrder) {
        	console.log(salesOrder);
            httpFactory.insertSalesOrder(salesOrder).then(function(result) {
                if (result) {
                    console.log('save pass');
                }
            })
        }

        function DialogController($scope, $mdDialog) {
            $scope.salesOrder = { name: '',customer:'',date:undefined, status:'',location:'' };
            $scope.hide = function() {
                $mdDialog.hide();
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            $scope.save = function() {
                $mdDialog.hide($scope.salesOrder);

            };
        }
        init();
    }])