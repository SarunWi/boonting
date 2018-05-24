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
        ctrl.showAdvanced = function(ev,item) {
            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'src/modalTemplate/locationDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: false,
                    locals: {
                        selectedItem : item
                    }
                })
                .then(function(location) {
                    ctrl.saveLocation(location);
                }, function() {
                    console.log('cancel');
                });
        };
        ctrl.saveLocation = function(location){
            if(location.id){
                httpFactory.updateLocation(location).then(function(result){
                    if(result){
                        console.log('save pass');
                    }
                })
        	}else{
                httpFactory.insertLocation(location).then(function(result){
            		if(result){
            			console.log('save pass');
            		}
            	})
            }
        }

        function DialogController($scope, $mdDialog,selectedItem) {
        	$scope.location = { name: '' ,address: ''}
            var init = function(){
                if(selectedItem){
                    $scope.location = selectedItem;
                }
            }
            $scope.hide = function() {
                $mdDialog.hide();
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            $scope.save = function() {
                $mdDialog.hide($scope.location);

            };
            init();
        }
        init();


    }])