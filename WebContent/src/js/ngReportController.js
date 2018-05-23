angular.module('boontingApp')
.controller('reportCtrl',['$q','httpFactory',function($q,httpFactory){
	var ctrl = this;
	ctrl.wrapper = { 
					salesOrders : [],
					displaySalesOrders: []
					}
	var init = function(){
		httpFactory.getSalesOrder().then(function(result){
			if(result){
				ctrl.wrapper.salesOrders 			= result;
				ctrl.wrapper.displaySalesOrders 	= result;
			}
		})
	}
	init();
}])