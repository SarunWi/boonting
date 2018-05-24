angular.module('boontingApp')
.factory('mockUpFactory',['$q',function($q){
	const getCustomers = function(){
		var customers = [ 	{id:1,name:'Harry Potter ',phone:'0819288012',email:'123@gmail.com'},
							{id:2,name:'John Wick',phone:'9101-02394',email:'sdfa@gmail.com'},
							{id:3,name:'Tony Stark',phone:'0193749192',email:'mew@gmail.com'},
							{id:4,name:'Scalet Johasan',phone:'0128018373',email:'may@gmail.com'},
							{id:5,name:'Captain ',phone:'0192385793',email:'kit@gmail.com'},
							{id:6,name:'Hulk',phone:'0912038542',email:'sit@gmail.com'},
							{id:7,name:'Groot',phone:'2458349810',email:'khan@gmail.com'},  
							{id:8,name:'wanda ',phone:'1230903323',email:'yah@gmail.com'},
							{id:9,name:'Vision',phone:'3984598100',email:'eiei@gmail.com'} ];
		return customers;
	}
	const getSalesOrders = function(){
		var salesorders = [ 
			{id:1,name:'SO-0001',customer:{id:1,name:'001',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'WatingConfirmFromSeller',location:{id:1,name:'Bangkok',address:'xxx'} },
			{id:2,name:'SO-0002',customer:{id:1,name:'001',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'ConfirmSalesOrder',location:{id:1,name:'Bangkok',address:'xxx'} },
			{id:3,name:'SO-0003',customer:{id:1,name:'001',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'WatingChecked',location:{id:1,name:'Bangkok',address:'xxx'} },
			{id:4,name:'SO-0004',customer:{id:2,name:'002',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'WatingConfirmFromSeller',location:{id:2,name:'Pattaya',address:'xxx'} },
			{id:5,name:'SO-0005',customer:{id:2,name:'002',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'WatingConfirmFromSeller',location:{id:2,name:'Pattaya',address:'xxx'} },
			{id:6,name:'SO-0006',customer:{id:3,name:'003',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'Completed',location:{id:3,name:'Hua hin',address:'xxx'} },
			{id:7,name:'SO-0007',customer:{id:4,name:'004',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'WatingConfirmFromSeller',location:{id:3,name:'Hua hin',address:'xxx'} },
			{id:8,name:'SO-0008',customer:{id:8,name:'008',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'WatingChecked',location:{id:4,name:'Phuket',address:'xxx'} },
			{id:9,name:'SO-0009',customer:{id:9,name:'009',phone:'0123123'},date:moment().format('YYYY-MM-DD'),status:'Traveling',location:{id:5,name:'Chaing mai',address:'xxx'} }
							];
		return salesorders;
	}
	const getLocations = function(){
		var locations = [
							{id:1,name:'Bangkok',address:'Thailand'},
							{id:2,name:'Pattaya',address:'Thailand'},
							{id:3,name:'Hua Hin',address:'Thailand'},
							{id:4,name:'Chiang Mai',address:'Thailand'}
						];
		return locations;
	}
	return {
		getCustomers 	: getCustomers,
		getSalesOrders 	: getSalesOrders,
		getLocations 	: getLocations
	}
}])