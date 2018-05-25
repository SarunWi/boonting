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
                        console.log(data.data.records);
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
                        deferred.resolve(data.data.records);
                    });
            }
            return deferred.promise;
        }
        var getLocation = function() {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(mockUpFactory.getLocations());
            }else{  
                    $http.get(baseURL + '/Boonting/rest/Location/getLocations')
                    .then(function(data) {
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
        var insertSalesOrder = function(salesOrder = {}) {
            var deferred = $q.defer();
            if (isMockUp) {
                deferred.resolve(true);
            }else{
                $http.post(baseURL+'/Boonting/rest/Location/insertLocation', 
                                    { 'username'    : username, 
                                    'password'     : password } 
                    ).then(function(data, status, headers, config) {
                        deferred.resolve(data, status, headers);
                    });
            }
            return deferred.promise;
        }
        var updateSalesOrder = function(salesOrder = {}) {
            var deferred = $q.defer();
            if (isMockUp) {
                deferred.resolve(true);
            }
            return deferred.promise;
        }
        var insertLocation = function(location = {}) {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(true);
            }else{
                $http.post(baseURL+'/Boonting/rest/Location/insertLocation', location
                    ).then(function(data, status, headers, config) {
                        deferred.resolve(data, status, headers);
                    });
            }
            return deferred.promise;
        }
        var updateLocation = function(location = {}) {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(true);
            }else{
                $http.post(baseURL+'/Boonting/rest/Location/updateLocation', location
                    ).then(function(data, status, headers, config) {
                        deferred.resolve(data, status, headers);
                    });
            }
            return deferred.promise;
        }
        var getMaterialItem = function() {
            var deferred = $q.defer();
            if (false) {
                deferred.resolve(mockUpFactory.getMaterialItems());
            }else{
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
        return {
            getCustomer: getCustomer,
            getSalesOrder: getSalesOrder,
            getLocation: getLocation,
            getSalesOrderByLocation: getSalesOrderByLocation,
            getSalesOrderByCustomer: getSalesOrderByCustomer,
            insertSalesOrder: insertSalesOrder,
            updateSalesOrder: updateSalesOrder,
            insertLocation: insertLocation,
            updateLocation: updateLocation,
            getMaterialItem: getMaterialItem,
            getSalesOrderOption: getSalesOrderOption
        }
    }])