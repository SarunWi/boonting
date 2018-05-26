angular.module('boontingApp')
    .factory('httpFactory', ['$q', 'mockUpFactory', '$http', function($q, mockUpFactory, $http) {
        const isMockUp = true;
        const baseURL = 'http://122.155.202.161:8080';
        const callBackOption = {
            buffer: true,
            escape: false,
            timeout: 30000
        };
        var getCustomer = function() {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(mockUpFactory.getCustomers());
            } else {
                $http.get(baseURL + '/Boonting/rest/Customer/getCustomer/')
                    .then(function(data, status, headers, config) {
                        // console.log(data.data.records);
                        deferred.resolve(data.data.records);
                    });
            }
            return deferred.promise;
        }
        var getSalesOrder = function() {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(mockUpFactory.getSalesOrders());
            } else {
                $http.get(baseURL + '/Boonting/rest/salesorder/getSalesorders/1/100/')
                    .then(function(data) {
                        // console.log('salesorder data :',data);
                        deferred.resolve(data.data.records);
                    });
            }
            return deferred.promise;
        }
        var getLocation = function() {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(mockUpFactory.getLocations());
            } else {
                $http.get(baseURL + '/Boonting/rest/Location/getLocations')
                    .then(function(data) {
                        // console.log('location data :',data);
                        deferred.resolve(data.data.records);
                    });
            }
            return deferred.promise;
        }
        var getSalesOrderByLocation = function() {
            var deferred = $q.defer();
            if (isMockUp) {
                deferred.resolve(mockUpFactory.getSalesOrderByLocations());
            }
            return deferred.promise;
        }
        var getSalesOrderByCustomer = function() {
            var deferred = $q.defer();
            if (isMockUp) {
                deferred.resolve(mockUpFactory.getSalesOrderByCustomers());
            }
            return deferred.promise;
        }
        var getSalesOrderByDate = function(fromDate, toDate) {
            var deferred = $q.defer();
            $http.get(baseURL + '/Boonting/rest/salesorder/getSalesOrderGroupByLocation//')
                .then(function(data) {
                    // console.log(data.data.records);
                    deferred.resolve(data.data.records);
                })

            return deferred.promise;
        }
        var upsertSalesOrder = function(salesOrder) {
            var deferred = $q.defer();
            var transformedOrder = toSalesOrder(salesOrder);

            console.log(JSON.stringify(transformedOrder));
            $http.post(baseURL + '/Boonting/rest/salesorder/insertUpdateSalesOrder', transformedOrder)
                .then(function(data) {
                    // console.log(data);
                    deferred.resolve(data);
                })

            return deferred.promise;
        }
        var toSalesOrder = function(order) {
            return {
                id: order.salesOrderId ? order.salesOrderId : "",
                location: order.locationId,
                username: order.createdBy,
                created_date: order.createdDate,
                remark: order.salesOrderRemark,
                state: order.stateName,
                status: order.salesOrderStatus ? order.salesOrderStatus : "",
                items: toOrderItemToJSONString(order.recycleMaterialList)
            };
        }
        var toOrderItemToJSONString = function(array) {
            var items = [];
            for (var i = 0; i < array.length; i++) {
                items.push({ rml_name: array[i].rml_name, rml_quantity: array[i].rml_quantity });
            }
            return JSON.stringify(items);
        }
        var insertLocation = function(location = {}) {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(true);
            } else {
                $http.post(baseURL + '/Boonting/rest/Location/insertLocation', location).then(function(data, status, headers, config) {
                    deferred.resolve(data, status, headers);
                });
            }
            return deferred.promise;
        }
        var updateLocation = function(location = {}) {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(true);
            } else {
                $http.post(baseURL + '/Boonting/rest/Location/updateLocation', location).then(function(data, status, headers, config) {
                    deferred.resolve(data, status, headers);
                });
            }
            return deferred.promise;
        }
        var getMaterialItem = function() {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(mockUpFactory.getMaterialItems());
            } else {
                $http.get(baseURL + '/Boonting/rest/Material/getMaterialItems')
                    .then(function(data) {
                        deferred.resolve(data.data.records);
                    });
            }
            return deferred.promise;
        }
        var getSalesOrderOption = function() {
            var deferred = $q.defer();
            if (isMockUp) {
                deferred.resolve(mockUpFactory.getSalesOrderOptions());
            }
            return deferred.promise;
        }
        var getRecycleMaterailById = function(orderId) {
            var deferred = $q.defer();
            console.log(orderId);
            if (orderId != undefined) {
                $http.get(baseURL + '/Boonting/rest/salesorder/getMeterialList/' + orderId)
                    .then(function(data) {
                        console.log(data);
                        deferred.resolve(data.data);
                    });
            } else {
                deferred.resolve([]);
            }
            return deferred.promise;
        }
        return {
            getCustomer: getCustomer,
            getSalesOrder: getSalesOrder,
            getLocation: getLocation,
            getSalesOrderByLocation: getSalesOrderByLocation,
            getSalesOrderByCustomer: getSalesOrderByCustomer,
            upsertSalesOrder: upsertSalesOrder,
            insertLocation: insertLocation,
            updateLocation: updateLocation,
            getMaterialItem: getMaterialItem,
            getSalesOrderOption: getSalesOrderOption,
            getSalesOrderByDate: getSalesOrderByDate,
            getRecycleMaterailById : getRecycleMaterailById
        }
    }])