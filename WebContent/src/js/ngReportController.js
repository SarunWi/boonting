angular.module('boontingApp')
.controller('reportCtrl',['$q','httpFactory',function($q,httpFactory){
	var ctrl = this;
	ctrl.wrapper = { 
					reportSelected : 'SalesorderByCustomer',
					reportOptions : [
									{label:'Sales Order by Customer',value:'SalesorderByCustomer'}, 
									{label:'Sales Order by Location',value:'SalesorderByLocation'}
									],
					Locations : {},
					Customers : {}
					}
	ctrl.reportWrapper = {};
	var init = function(){
		$q.all([
			httpFactory.getCustomer() ,
			httpFactory.getLocation()
			 ]).then($q.spread(function(customer, location){
			 	ctrl.wrapper.locations = toMap(location);
			 	ctrl.wrapper.customers = toMap(customer);
			 	ctrl.switchReport();
			 }));
	}
	var toMap = function(arrayList){
		var mapitem = {};
		for(var i=0;i<arrayList.length;i++){
			mapitem[arrayList[i]] = arrayList[i];
		}
		return mapitem;
	}
	ctrl.switchReport = function(){
		if(ctrl.wrapper.reportSelected=='SalesorderByCustomer'){
			httpFactory.getSalesOrderByCustomer().then(function(groupByCustomer){
				ctrl.reportWrapper = transformToReport(groupByCustomer);
			})
		}else if(ctrl.wrapper.reportSelected=='SalesorderByLocation'){
			httpFactory.getSalesOrderByLocation().then(function(groupByLocation){
				ctrl.reportWrapper = transformToReport(groupByLocation);
			})
		}
	}
	var transformToReport = function(soGroupItems){
		var groupSalesOrderByIdAndMonth = {};
		for(var i=0;i<soGroupItems.length;i++){
			console.log(soGroupItems);
			var groupItemByMonth = {};
			var groupItems = soGroupItems[i];
			for(var j=0;j<groupItems.length;j++){
				var month = moment(groupItems[j].date,'YYYY-MM-DD').format('MM');
				if(groupItemByMonth.includes(month)){
					groupItemByMonth[month].push(groupItems[j]);
				}else{
					groupItemByMonth[month] = [groupItems[j]];
				}
			}
			groupSalesOrderByIdAndMonth[soGroupItems[i].id] = groupItemByMonth[month];
		}
		console.log('group ',JSON.parse(JSON.stringify(groupSalesOrderByIdAndMonth)) );
		return groupSalesOrderByIdAndMonth;
	}
	init();
}])