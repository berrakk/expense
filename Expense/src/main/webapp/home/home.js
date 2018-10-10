var app = angular.module('expense.home', ['ui.router','ui.bootstrap', 'ngResource', 'ngCookies']);



app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('homeState', {
            url: '/home',
            views: {
                "main":{
                    templateUrl: 'home/home.tpl.html',
                    controller: 'HomeController'
                }
            },
            data: {pageTitle: 'Ana Sayfa'}
        })
        .state('signup', {
            url: '/signup',
            views: {
                "main":{
                    templateUrl: 'home/signup.tpl.html'
                }
            },
            data: {pageTitle: 'Kayıt Ol'}
        })


});


app.factory('authenticationService', ['$http', '$cookies', '$rootScope', function($http, $cookies, $rootScope){

    var service = {};

    service.SetCredentials = SetCredentials;
    service.ClearCredentials = ClearCredentials;

    return service;

    function SetCredentials(identityNumber, name, password) {
        var authdata = Base64.encode(identityNumber + ':' + password);

        $rootScope.globals = {
            currentUser: {
                identityNumber: identityNumber,
                name: name,
                authdata: authdata
            }
        };

        // set default auth header for http requests
        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;

        // store user details in globals cookie that keeps user logged in for 1 week (or until they logout)
        var cookieExp = new Date();
        cookieExp.setDate(cookieExp.getDate() + 7);
        $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp, path: '/' });

        console.log($cookies.getObject('globals'));
    }

    function ClearCredentials() {
        $rootScope.globals = {};
        $cookies.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic';
    }
}]);




// Base64 encoding service used by AuthenticationService
var Base64 = {

    keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',

    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        do {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                this.keyStr.charAt(enc1) +
                this.keyStr.charAt(enc2) +
                this.keyStr.charAt(enc3) +
                this.keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        } while (i < input.length);

        return output;
    },

    decode: function (input) {
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
        var base64test = /[^A-Za-z0-9\+\/\=]/g;
        if (base64test.exec(input)) {
            window.alert("There were invalid base64 characters in the input text.\n" +
                "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                "Expect errors in decoding.");
        }
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        do {
            enc1 = this.keyStr.indexOf(input.charAt(i++));
            enc2 = this.keyStr.indexOf(input.charAt(i++));
            enc3 = this.keyStr.indexOf(input.charAt(i++));
            enc4 = this.keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";

        } while (i < input.length);

        return output;
    }
};

app.factory('employeeService', function($resource, $q){
    var service = {};

    service.authenticateEmployee = function(identityNumber){
        var deferred = $q.defer();
        var Employee = $resource('rest/employee/:identityNumber');
        Employee.get({identityNumber: identityNumber}).$promise.then(
            function (employee) {
                deferred.resolve(employee);
            }, function (error) {
                deferred.reject(error)
            });

        return deferred.promise;
    };


    service.getExpensesByEmployee = function(identityNumber){
        var Expense = $resource('rest/expense/employee/:identityNumber');
        return Expense.query({identityNumber: identityNumber}).$promise.then(function (expenses) {
            console.log(expenses[0]);
            return expenses[0];
        });


    };

    return service;

});


app.controller('HomeController',['$scope', 'employeeService', 'authenticationService', function ($scope, employeeService, authenticationService) {


    // (function initController() {
    //     // reset login status
    //     //$scope.isEmployeeLoggedin = false;
    //     //$scope.employee = null;
    //     authenticationService.ClearCredentials();
    // })();

    $scope.signout = function(){

        $scope.isEmployeeLoggedin = false;
        authenticationService.ClearCredentials();
    };

    // employeeService.getExpensesByEmployee($scope.identityNumber).then(function (expenses) {
    //     console.log(expenses);
    // });


    $scope.login = function(){
        employeeService.authenticateEmployee($scope.identityNumber).then(function (employee) {
            if (employee.employeePassword === $scope.password){
                $scope.name = employee.employeeName;
                authenticationService.SetCredentials($scope.identityNumber, $scope.name, $scope.password);
                $scope.isEmployeeLoggedin = true;
            }
        })
    };


    $scope.slides = [
        { image: '../image/image1.jpg', text: '1' },
        { image: '../image/image2.jpg', text: '2' },
        { image: '../image/image3.jpg', text: '3' },
        { image: '../image/image4.jpg', text: '4' },
        { image: '../image/image5.jpg', text: '5' }
    ]

}]);











