angular.module('BookKeeper',[])
    .controller('BookKeeperController', function($scope,$http){
        console.log("Testing..")
        $scope.isLive = false;
//        $scope.name = "HC NAME";
        $scope.register = function(){
            $http.post("/register")
            .then(
                function success(response){
                console.log(response.data);
                console.log("Registering...");
                $scope.user = response.data;
                $scope.isLive = true;
                console.log(isLive);
                },
                function error(response){
                console.log("Unable to register");
                });
        };
        $scope.getAllBooks = function(){
            console.log("Retrieving data");
            $http.get("http://localhost:8080/books.json")
                .then(
                    function success(response){
                    console.log(response.data);
                    $scope.books = response.data;
                    },
                    function error(response){
                    console.log("Error");
                    });
        };
        $scope.addBook = function(){
            console.log("About to add the following book data:" + JSON.stringify($scope.newBook));
                $http.post("/addBook.json",$scope.newBook)
                    .then(
                        function success(response){
                            console.log(response.data);
                            console.log("Adding data to scope...");
                            $scope.books = response.data;
                            },
                            function error(response){
                            console.log("error");
                            });
        };

    });