'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('VisitListCtrl', ['$scope', 'Visit', 'Auth',
    function($scope, Visit, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.phones = Visit.query();
        $scope.orderProp = 'ipAddress';
    }
]);

phonecatControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', 'VisitDetail', 'Auth',
    function($scope, $routeParams, VisitDetail, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.phone = VisitDetail.get({
            id: $routeParams.phoneId
        });
    }
]);
