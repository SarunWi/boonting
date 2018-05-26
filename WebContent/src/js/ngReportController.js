angular.module('boontingApp')
    .controller('reportCtrl', ['$q', 'httpFactory', function($q, httpFactory) {
        var ctrl = this;
        ctrl.wrapper = {
            reportSelected: 'SalesorderByCustomer',
            reportOptions: [
                { label: 'Sales Order by Customer', value: 'SalesorderByCustomer' },
                { label: 'Sales Order by Location', value: 'SalesorderByLocation' }
            ],
            Locations: {},
            Customers: {},
            stateOption: []
        }
        ctrl.reportWrapper = {
            rawOrderByDate: [],
            groupReport : []
        };
        var init = function() {
            $q.all([
                httpFactory.getCustomer(),
                httpFactory.getLocation(),
                httpFactory.getSalesOrderByDate('', ''),
                httpFactory.getSalesOrderOption()
            ]).then($q.spread(function(customer, location, salesorderByDate,stateOption) {
                ctrl.wrapper.locations = toMap(location,'locationId');
                ctrl.wrapper.customers = toMap(customer,'username');
                ctrl.wrapper.stateOption = stateOption;
                ctrl.reportWrapper.rawOrderByDate = salesorderByDate;
                ctrl.switchReport();

                console.log(ctrl.wrapper.customers);
                console.log(ctrl.wrapper.locations);
            }));
        }
        var toMap = function(arrayList,key) {
            var mapitem = {};
            for (var i = 0; i < arrayList.length; i++) {
                mapitem[arrayList[i][key]] = arrayList[i];
            }
            return mapitem;
        }
        ctrl.switchReport = function() {
            if (ctrl.wrapper.reportSelected == 'SalesorderByCustomer') {
                ctrl.groupSalesOrderByCustomer(ctrl.reportWrapper.rawOrderByDate);
            } else if (ctrl.wrapper.reportSelected == 'SalesorderByLocation') {
                ctrl.groupSalesOrderByLocation(ctrl.reportWrapper.rawOrderByDate);
            }
            console.log('mode :',ctrl.wrapper.reportSelected,' : ',ctrl.reportWrapper.groupReport);
        }
        ctrl.groupSalesOrderByLocation = function(orders) {
            var groupByLocation = groupBy(orders,"locationId");
        	var groupByLocationAndState 	= [];
        	var groupByLocationWithObject 	= []; 
        	for(key in groupByLocation ){
				groupByLocationWithObject.push({ "key":key, val:groupBy(groupByLocation[key],"stateName")});
        	}
        	for(var i=0;i<groupByLocationWithObject.length;i++){
        		groupByStateArray = [];
        		for(key in groupByLocationWithObject[i].val){
        			groupByStateArray.push({"key":key,val:groupByLocationWithObject[i].val[key],total:groupByLocationWithObject[i].val[key].length });
        		}
        		groupByLocationAndState.push({'key':groupByLocationWithObject[i].key,'val':groupByStateArray});
        	}
        	ctrl.reportWrapper.groupReport  = groupByLocationWithObject;
        }
        ctrl.groupSalesOrderByCustomer = function(orders) {
        	var groupBycustomer = groupBy(orders,"createdBy");
        	var groupBycustomerAndState 	= [];
        	var groupByCustomerWithObject 	= []; 
        	for(key in groupBycustomer ){
				groupByCustomerWithObject.push({ "key":key, val:groupBy(groupBycustomer[key],"stateName") });
        	}
        	for(var i=0;i<groupByCustomerWithObject.length;i++){
        		groupByStateArray = [];
        		for(key in groupByCustomerWithObject[i].val){
        			groupByStateArray.push({"key":key,val:groupByCustomerWithObject[i].val[key],total:groupByCustomerWithObject[i].val[key].length });
        		}
        		groupBycustomerAndState.push({'key':groupByCustomerWithObject[i].key,'val':groupByStateArray});
        	}
        	ctrl.reportWrapper.groupReport  = groupByCustomerWithObject;
        }
        const groupBy = (items, key) => items.reduce(
            (result, item) => ({
                ...result,
                [item[key]]: [
                    ...(result[item[key]] || []),
                    item,
                ],
            }), {},
        );
        init();
    }])