var app = angular.module('expense.expense', ['expense.home','ui.router','ui.bootstrap', 'ngResource']);



app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('createExpenseState', {
            url: '/createExpense',
            views: {
                "main":{
                    templateUrl: 'expense/createExpense.tpl.html'
                }
            },
            data: {pageTitle: 'Create Expense'}
        })


        .state('approvalWaitingExpensesState', {
            url: '/approvalWaitingExpenses',
            views: {
                "main":{
                    templateUrl: 'expense/expenses.tpl.html'
                }
            },
            data: {pageTitle: 'Onay Bekleyen Masraflar'}
        })

        .state('expensesState', {
            url: '/expenses/:identityNumber',
            views: {
                "main":{
                    templateUrl: 'expense/expenses.tpl.html',
                    controller: 'ExpenseController'
                }
            },
            data: {pageTitle: 'Masraflarım'},
            resolve: {

                expensesByEmployee: function(employeeService, $stateParams){
                    return employeeService.getExpensesByEmployee($stateParams.identityNumber);
                }
            }
        })


});

app.factory('expenseService', [function($resource, $q){
    var service = {};

    service.getExpensesByEmployee = function(identityNumber){
        var deferred = $q.defer();
        var Expense = $resource('rest/expense/employee/:identityNumber');
        Expense.query({identityNumber: identityNumber}).$promise.then(
            function (expenses) {
                deferred.resolve(expenses);
            }, function (error) {
                deferred.reject(error)
            });

        return deferred.promise;

    };

    return service;

}]);


app.controller('ExpenseController',['$scope', 'expenseService', '$rootScope' ,function ($scope, expenseService, expensesByEmployee) {

    $scope.expensesByEmployee = expensesByEmployee;
    console.log($scope.expensesByEmployee);

}]);


    app.controller('ExpenseController',['$scope' ,function ($scope) {

        $scope.IsVisible = false;

        $scope.expenses=[
    {
        "expenseId": 1,
        "dateCreateExp": 1536646481000,
        "expStatement": "sana ne",
        "employee": {
            "identityNumber": 12345678912,
            "employeeName": "Osman",
            "employeeSurname": "Kiraz",
            "employeeEmail": "osman.kiraz@turkcell.com.tr",
            "employeePassword": "123456",
            "manager": 27139807478,
            "department": {
                "depId": 1,
                "depName": "Billing Development",
                "reportingDepartment": 2,
                "managerId": 27139807478
            },
            "employeeType": {
                "employeeType": "Employee",
                "employeeId": 1
            }
        },
        "approvalStatus": {
            "approvalId": 3,
            "approvalStatus": "Reddedildi"
        },
        "expenseSet": [
            {
                "expenseSubId": 1,
                "expenseAmount": 110,
                "expTypeId": {
                    "expenseTypeId": 1,
                    "expenseTypeName": "Taksi"
                },
                "subExpenseDate": 1536926991000
            }
        ]
    },
    {
        "expenseId": 4,
        "dateCreateExp": 1536872400000,
        "expStatement": "Mesai akşam yemeği",
        "employee": {
            "identityNumber": 11436014516,
            "employeeName": "Berrak",
            "employeeSurname": "Karaduman",
            "employeeEmail": "berrak.karaduman@staj.turkcell.com.tr",
            "employeePassword": "123456",
            "manager": 27139807478,
            "department": {
                "depId": 1,
                "depName": "Billing Development",
                "reportingDepartment": 2,
                "managerId": 27139807478
            },
            "employeeType": {
                "employeeType": "Employee",
                "employeeId": 1
            }
        },
        "approvalStatus": {
            "approvalId": 1,
            "approvalStatus": "Yönetici Onayında"
        },
        "expenseSet": [
            {
                "expenseSubId": 2,
                "expenseAmount": 150,
                "expTypeId": {
                    "expenseTypeId": 1,
                    "expenseTypeName": "Taksi"
                },
                "subExpenseDate": 1536927017000
            }
        ]
    },
    {
        "expenseId": 3,
        "dateCreateExp": 1536646481000,
        "expStatement": "post request yapiyoruz",
        "employee": {
            "identityNumber": 12345678912,
            "employeeName": "Osman",
            "employeeSurname": "Kiraz",
            "employeeEmail": "osman.kiraz@turkcell.com.tr",
            "employeePassword": "123456",
            "manager": 27139807478,
            "department": {
                "depId": 1,
                "depName": "Billing Development",
                "reportingDepartment": 2,
                "managerId": 27139807478
            },
            "employeeType": {
                "employeeType": "Employee",
                "employeeId": 1
            }
        },
        "approvalStatus": {
            "approvalId": 3,
            "approvalStatus": "Reddedildi"
        },
        "expenseSet": [
            {
                "expenseSubId": 4,
                "expenseAmount": 250,
                "expTypeId": {
                    "expenseTypeId": 1,
                    "expenseTypeName": "Taksi"
                },
                "subExpenseDate": 1536926991000
            }
        ]
    },
    {
        "expenseId": 5,
        "dateCreateExp": 1536929179000,
        "expStatement": "fatmanın expense i",
        "employee": {
            "identityNumber": 12345678915,
            "employeeName": "Fatma",
            "employeeSurname": "Ataş",
            "employeeEmail": "fatma.atas@turkcell.com.tr",
            "employeePassword": "123456",
            "manager": 12345678914,
            "department": {
                "depId": 3,
                "depName": "Billing Solutions",
                "reportingDepartment": 2,
                "managerId": 12345678914
            },
            "employeeType": {
                "employeeType": "Employee",
                "employeeId": 1
            }
        },
        "approvalStatus": {
            "approvalId": 2,
            "approvalStatus": "Üst Yonetici Onayı"
        },
        "expenseSet": [
            {
                "expenseSubId": 3,
                "expenseAmount": 120,
                "expTypeId": {
                    "expenseTypeId": 2,
                    "expenseTypeName": "Eğitim"
                },
                "subExpenseDate": 1537304400000
            }
        ]
    }
]
        $scope.IsVisible = true;


    }]);











