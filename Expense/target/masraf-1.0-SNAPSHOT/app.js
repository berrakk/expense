angular.module( 'expense', [
    'expense.home',
    'expense.expense',
    'ui.router',
    'ngResource',
    'ngCookies'
])
    .config( function myAppConfig ( $stateProvider, $urlRouterProvider, $locationProvider) {
        $urlRouterProvider.otherwise( '/home' );
        $locationProvider.hashPrefix('');
    })
    .run( ['$rootScope','$cookies', '$http', function myRun ( $rootScope, $cookies, $http ) {
        $rootScope.globals = $cookies.getObject('globals') || {};

        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
            $rootScope.isEmployeeLoggedin = true;
            $rootScope.identityNumber = $rootScope.globals.currentUser.identityNumber;
            $rootScope.name = $rootScope.globals.currentUser.name;
        }
    }])
    .controller( 'AppCtrl', function AppCtrl ( $scope ) {
        $scope.$on('$stateChangeSuccess', function(event, toState){

            if (angular.isDefined( toState.data.pageTitle ) ) {
                    $scope.pageTitle = toState.data.pageTitle ;
                }
        });

});




