angular.module('boontingApp',['ngRoute','ngMaterial','ngMessages','smart-table'])
.config(window.$QDecorator)
.config(function($routeProvider) {
    $routeProvider
    .when("/",{
    	templateUrl : "src/main.html",
    	controller 	: "mainPage as ctrl"
    })
    .when("/customer", {
        templateUrl : "src/customerPage.html",
        controller 	: "customerCtrl as ctrl"
    })
    .when("/salesOrder", {
        templateUrl : "src/salesOrderPage.html",
        controller  : "salesorderCtrl as ctrl"
    })
    .when("/location", {
        templateUrl : "src/locationPage.html",
        controller  : "locationCtrl as ctrl"
    })
    .when("/report", {
        templateUrl : "src/reportPage.html",
        controller  : "reportCtrl as ctrl"
    })
})
.controller('mainController',['$q','$scope',function($q,$scope){
	var ctrl = this;
	// $scope.currentNavItem  = 'home';
	ctrl.wrapper = { pageHeader : 'some header' };

}])
.controller('mainPage',['$q',function($q){
	var ctrl = this;
	ctrl.wrapper = {pageHeader :'main'};
}])