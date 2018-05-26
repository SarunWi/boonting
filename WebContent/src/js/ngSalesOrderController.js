angular.module('boontingApp')
    .controller('salesorderCtrl', ['$q', 'httpFactory', '$mdDialog', function($q, httpFactory, $mdDialog) {
        var ctrl = this;
        ctrl.wrapper = {
            salesOrders: [],
            displaySalesOrders: []
        }
        ctrl.optionWrapper = {
            locationMapping: {},
            customerMapping: {}
        };
        var init = function() {
            $q.all([
                httpFactory.getSalesOrder(),
                httpFactory.getCustomer(),
                httpFactory.getLocation()]
            ).then($q.spread(function(salesOrder, customers, locations) {
                ctrl.wrapper.salesOrders            = salesOrder;
                ctrl.wrapper.displaySalesOrders     = salesOrder;
                ctrl.optionWrapper.locationMapping  = toMap(locations, 'locationId');
                ctrl.optionWrapper.customers        = toMap(customers, 'username');

                console.log(ctrl.wrapper.salesOrders);
                // console.log('customer mapping ',ctrl.optionWrapper.customers);
                // console.log('location mapping ',ctrl.optionWrapper.locationMapping);

            }))
        }
        var toMap = function(arrayList, field) {
            var mapitem = {};
            for (var i = 0; i < arrayList.length; i++) {
                mapitem[arrayList[i][field]] = arrayList[i];
            }
            return mapitem;
        }

        ctrl.showAdvanced = function(ev, item) {
            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'src/modalTemplate/salesOrderDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: false,
                    locals: {
                        selectedItem: item
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

            if(!salesOrder.id){
                salesOrder.id = "";
            }
            httpFactory.upsertSalesOrder(salesOrder).then(function(result) {
                console.log(result);
            });
        }

        function DialogController($scope, $mdDialog, httpFactory, $q, selectedItem) {
            $scope.salesOrder = {
                name: undefined,
                createdBy: undefined,
                createdDate: undefined,
                stateName: undefined,
                locationId: undefined,
                salesOrderRemark: '',
                recycleMaterialList: [],
                salesOrderStatus: undefined,

            };
            $scope.wrapper = {
                customers: [],
                materialItems: [],
                locations: [],
                status: [],
                materialMap: {},
                customerMap: {}

            }
            var initOption = function() {
                console.log('selected Item :', selectedItem);
                if (selectedItem) {
                    if (selectedItem.recycleMaterialList == undefined || selectedItem.recycleMaterialList == null) {
                        selectedItem.recycleMaterialList = [];
                    }
                    $scope.salesOrder = selectedItem;
                }

                $q.all([
                    httpFactory.getCustomer(),
                    httpFactory.getLocation(),
                    httpFactory.getMaterialItem(),
                    httpFactory.getSalesOrderOption(),
                    httpFactory.getRecycleMaterailById($scope.salesOrder.salesOrderId)
                ]).then($q.spread(function(customers, locations, materials, statusOpts,recycleMaterialList) {
                    console.log(recycleMaterialList);
                    for(var i=0;i<recycleMaterialList.length;i++){
                        recycleMaterialList[i].rml_name = recycleMaterialList[i].rmlName;
                        recycleMaterialList[i].rml_quantity = recycleMaterialList[i].rmlQuantity;
                    }
                    $scope.wrapper.customers = customers;
                    $scope.wrapper.locations = locations;
                    $scope.wrapper.materialItems        = materials;
                    $scope.wrapper.status               = statusOpts;
                    $scope.salesOrder.recycleMaterialList = recycleMaterialList?recycleMaterialList:[];
                    setMaterial(materials);
                }));
            }
            var setMaterial = function(materials) {
                for (var i = 0; i < materials.length; i++) {
                    $scope.wrapper.materialMap[materials[i].rmName] = materials[i];
                }

            }

            $scope.changeProduct = function(item, materialId) {
                console.log(materialId);
                console.log($scope.wrapper.materialMap);
                item.rml_Price = $scope.wrapper.materialMap[materialId].rmPrice;
                if (item.rml_Price != undefined && item.rml_quantity != undefined) {
                    item.totalPrice = item.unitPrice * item.rml_quantity;
                    console.log(item.totalPrice);
                }
            }
            $scope.changeQuantity = function(item) {

                if (item.rml_Price != undefined && item.rml_quantity != undefined) {
                    item.totalPrice = item.rml_Price * item.rml_quantity;
                }
                console.log(item);
            }
            $scope.addItem = function() {
                $scope.salesOrder.recycleMaterialList.push({});
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