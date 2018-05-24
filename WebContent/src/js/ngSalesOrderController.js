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
        ctrl.showAdvanced = function(ev,item) {
            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'src/modalTemplate/salesOrderDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: false,
                    locals: {
                        selectedItem : item
                    }
                })
                .then(function(salesOrder) {
                    ctrl.saveSalesOrder(salesOrder);
                }, function() {
                    console.log('cancel');
                });
        };
        ctrl.saveSalesOrder = function(salesOrder) {
        	console.log(salesOrder);
            if(salesOrder.id){
                httpFactory.updateSalesOrder(salesOrder).then(function(result) {
                    if (result) {
                        console.log('save pass');
                    }
                })
            }else{
                httpFactory.insertSalesOrder(salesOrder).then(function(result) {
                    if (result) {
                        console.log('save pass');
                    }
                })
            }
        }

        function DialogController($scope, $mdDialog,httpFactory,$q,selectedItem) {
            $scope.salesOrder = {   
                                    name    : undefined,
                                    customer: undefined,
                                    date    : undefined, 
                                    status  : undefined,
                                    location: undefined,
                                    note    : '',
                                    items   : [] 
                                };
            $scope.wrapper = {
                customers       : [],
                materialItems   : [],
                locations       : [],
                status          : [],
                materialMap     : {}
            }
            var initOption = function(){
                console.log('selected Item :',selectedItem);
                if(selectedItem){
                    $scope.salesOrder = selectedItem;
                }
                $q.all([ 
                        httpFactory.getCustomer(),
                        httpFactory.getLocation(),
                        httpFactory.getMaterialItem(),
                        httpFactory.getSalesOrderOption()
                 ]).then($q.spread(function(customers,locations,materials,statusOpts){
                    $scope.wrapper.customers = customers;
                    $scope.wrapper.locations = locations;
                    $scope.wrapper.materialItems = materials;
                    $scope.wrapper.status = statusOpts;
                    setMaterial(materials);
                 }));
            }
            var setMaterial = function(materials){
                for(var i=0;i<materials.length;i++ ){
                    $scope.wrapper.materialMap[materials[i].id] = materials[i];
                }

            }
            $scope.changeProduct = function(item,materialId){
                console.log(item);
                item.unitPrice = $scope.wrapper.materialMap[materialId].unitPrice;
                if(item.unitPrice!=undefined &&item.quantity!=undefined){
                    item.totalPrice = item.unitPrice*item.quantity;
                    console.log(item.totalPrice);
                }
            }
            $scope.changeQuantity = function(item){

                if(item.unitPrice!=undefined &&item.quantity!=undefined){
                    item.totalPrice = item.unitPrice*item.quantity;
                }
                console.log(item);
            }
            $scope.addItem = function(){
                $scope.salesOrder.items.push({});
            }
            $scope.hide = function() {
                $mdDialog.hide();
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            $scope.save = function() {
                $mdDialog.hide($scope.salesOrder);

            };
            initOption();
        }
        init();
    }])