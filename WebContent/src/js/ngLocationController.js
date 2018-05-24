angular.module('boontingApp')
    .controller('locationCtrl', ['$q', 'httpFactory', '$mdDialog','$scope', function($q, httpFactory, $mdDialog,$scope) {
        var ctrl = this;
        ctrl.wrapper = {
            locations: [],
            displayLocations: []
        }
        var init = function() {
            httpFactory.getLocation().then(function(result) {
                if (result) {
                    ctrl.wrapper.locations = result;
                    ctrl.wrapper.displayLocations = result;
                }
            })
        }
        ctrl.showAdvanced = function(ev) {
            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'src/modalTemplate/locationDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: false // Only for -xs, -sm breakpoints.
                })
                .then(function(location) {
                    ctrl.saveLocation(location);
                }, function() {
                    console.log('cancel');
                });
        };
        ctrl.saveLocation = function(location){
        	httpFactory.insertLocation(location).then(function(result){
        		if(result){
        			console.log('save pass');
        		}
        	})
        }

        function DialogController($scope, $mdDialog) {
        	$scope.location = { name: '' ,address: ''}
            $scope.hide = function() {
                $mdDialog.hide();
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            $scope.save = function() {
                $mdDialog.hide($scope.location);

            };
        }
        init();


    }])