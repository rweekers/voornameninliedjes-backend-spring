'use strict';

angular.module('myApp.songAdd', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/songAdd', {
            templateUrl: 'songAdd/songAdd.html',
            controller: 'SongAddCtrl'
        });
    }
])

.controller('SongAddCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', 'Song',
    function($scope, $location, $routeParams, $cookieStore, Song) {

        /*
        $scope.song = Song.get({
            id: 12070
        });*/
        $scope.song = new Song({
            artist: 'Test Artist'
        });
        $scope.song.title = 'Test Title';

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userInserted = $cookieStore.get('user');
            // $scope.song.$save();
            // console.log("Song saved is " + $scope.song.title);

            $scope.song.$save(function(user) {
                if (user.id) {
                    console.log("Song saved is " + user.id);
                    $location.path('/songs');
                } else {
                    console.log("Song could not be saved");
                    $scope.result = 'Blabla';
                    // TODO keep on page
                    // ??? $location.path('/songAdd');
                }
            });


        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/songs');
        };
    }
]);