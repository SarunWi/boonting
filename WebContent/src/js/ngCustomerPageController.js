angular.module('boontingApp')
.controller('customerCtrl',['$q','httpFactory',function($q,httpFactory){
	var ctrl = this;
	ctrl.wrapper = { 
					customers : [],
					displayCustomer: []
					}
	var init = function(){
		httpFactory.getCustomer().then(function(result){
			if(result){
				ctrl.wrapper.customers 			= result;
				ctrl.wrapper.displayCustomer 	= result;
			}
		})
	}
	init();
}])