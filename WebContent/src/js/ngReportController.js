angular.module('boontingApp')
.controller('reportCtrl',['$q','httpFactory',function($q,httpFactory){
	var ctrl = this;
	ctrl.wrapper = { 
					reportSelected : 'SalesorderByCustomer',
					reportOptions : [
									{label:'Sales Order by Customer',value:'SalesorderByCustomer'}, 
									{label:'Sales Order by Location',value:'SalesorderByLocation'}
									]
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