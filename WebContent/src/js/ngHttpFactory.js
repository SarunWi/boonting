angular.module('boontingApp')
.factory('httpFactory',['$q','mockUpFactory',function($q,mockUpFactory){
	const isMockUp 		= true;
	const callBackOption = {
                buffer: true,
                escape: false,
                timeout: 30000
            };
	var getCustomer = function(){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(mockUpFactory.getCustomers());
        }
        // SelectProductRemoteAction.validateAccess( 
        // getOpportunityId(), 
        // function(result, event) {
        // 	if (event.status) {
        //     	isValid = result.mapResult['isHasAccess'];
        //         deferred.resolve(isValid);
        //     } else {
        //     	deferred.reject(result);
        //     }
        // }, callBackOption );
        return deferred.promise;
	}
	var getSalesOrder = function(){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(mockUpFactory.getSalesOrders());
        }
        return deferred.promise;
	}
	var getLocation = function(){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(mockUpFactory.getLocations());
        }
        return deferred.promise;
	}
	var getSalesOrderByLocation = function(){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(mockUpFactory.getLocations());
        }
        return deferred.promise;
	}
	var getSalesOrderByCustomer = function(){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(mockUpFactory.getLocations());
        }
        return deferred.promise;
	}
	var insertSalesOrder = function(salesOrder = {}){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(true);
        }
        return deferred.promise;
	}
	var updateSalesOrder = function(salesOrder = {}){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(true);
        }
        return deferred.promise;
	}
	var insertLocation = function(location = {}){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(true);
        }
        return deferred.promise;
	}
	var updateLocation = function(location = {}){
		var deferred = $q.defer();
        if(isMockUp){
        	deferred.resolve(true);
        }
        return deferred.promise;
	}
    var getMaterialItem = function(){
        var deferred = $q.defer();
        if(isMockUp){
            deferred.resolve(mockUpFactory.getMaterialItems());
        }
        return deferred.promise;
    }
    var getSalesOrderOption = function(){
        var deferred = $q.defer();
        if(isMockUp){
            deferred.resolve(mockUpFactory.getSalesOrderOptions());
        }
        return deferred.promise; 
    }
	return {
		getCustomer 	           : getCustomer,
		getSalesOrder 	           : getSalesOrder,
		getLocation 	           : getLocation,
		getSalesOrderByLocation    : getSalesOrderByLocation,
		getSalesOrderByCustomer    : getSalesOrderByCustomer,
        insertSalesOrder           : insertSalesOrder,
        updateSalesOrder           : updateSalesOrder,
        insertLocation             : insertLocation,
        updateLocation             : updateLocation,
        getMaterialItem            : getMaterialItem,
        getSalesOrderOption        : getSalesOrderOption
	}
}])