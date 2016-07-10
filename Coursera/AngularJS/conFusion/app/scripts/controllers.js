'use strict';

angular.module('confusionApp')

        .controller('MenuController', ['$scope', 'menuFactory', function($scope, menuFactory) {
            
            $scope.tab = 1;
            $scope.filtText = '';
            $scope.showDetails = false;

            $scope.showMenu = false;
            $scope.message = "Loading ...";
			menuFactory.getDishes().query(
                function(response) {
                    $scope.dishes = response;
                    $scope.showMenu = true;
                },
                function(response) {
                    $scope.message = "Error: "+response.status + " " + response.statusText;
                }
			);			

                        
            $scope.select = function(setTab) {
                $scope.tab = setTab;
                
                if (setTab === 2) {
                    $scope.filtText = "appetizer";
                }
                else if (setTab === 3) {
                    $scope.filtText = "mains";
                }
                else if (setTab === 4) {
                    $scope.filtText = "dessert";
                }
                else {
                    $scope.filtText = "";
                }
            };

            $scope.isSelected = function (checkTab) {
                return ($scope.tab === checkTab);
            };
    
            $scope.toggleDetails = function() {
                $scope.showDetails = !$scope.showDetails;
            };
        }])

        .controller('ContactController', ['$scope', function($scope) {

            $scope.feedback = {mychannel:"", firstName:"", lastName:"", agree:false, email:"" };
            
            var channels = [{value:"tel", label:"Tel."}, {value:"Email",label:"Email"}];
            
            $scope.channels = channels;
            $scope.invalidChannelSelection = false;
                        
        }])

        .controller('FeedbackController', ['$scope', 'feedbackFactory', function($scope, feedbackFactory) {
            
            $scope.sendFeedback = function() {
                
                console.log($scope.feedback);
                
                if ($scope.feedback.agree && !$scope.feedback.mychannel) {
                    $scope.invalidChannelSelection = true;
                    console.log('incorrect');
                }
                else {
					//Save feedback on server
					feedbackFactory.getFeedbacks().save($scope.feedback,
						function(){
							alert("Feedback saved with success!");
						}
				   	);
                    $scope.invalidChannelSelection = false;
                    $scope.feedback = {
						mychannel:"",
						firstName:"",
						lastName:"",
						agree:false,
						email:"" 
					};									                    
                    $scope.feedbackForm.$setPristine();                    
                }
            };
        }])

        .controller('DishDetailController', ['$scope', '$stateParams', 'menuFactory', function($scope, $stateParams, menuFactory) {

			$scope.showDish = false;
			$scope.message = "Loading ...";
			$scope.dish = menuFactory.getDishes().get({id:parseInt($stateParams.id,10)})
				.$promise.then(
					function(response){
						$scope.dish = response;
						$scope.showDish = true;
					},
					function(response) {
						$scope.message = "Error: "+response.status + " " + response.statusText;
					}
				);
            
        }])

        .controller('DishCommentController', ['$scope', 'menuFactory', function($scope, menuFactory) {
            
            $scope.mycomment = {rating:5, comment:"", author:"", date:""};
            
            $scope.submitComment = function () {
                
                $scope.mycomment.date = new Date().toISOString();
                console.log($scope.mycomment);
				
				$scope.dish.comments.push($scope.mycomment);
				menuFactory.getDishes().update({id:$scope.dish.id},$scope.dish);
				
                $scope.commentForm.$setPristine();
                
                $scope.mycomment = {rating:5, comment:"", author:"", date:""};
				
            };
        }])

		.controller('IndexController', ['$scope', '$stateParams', 'menuFactory', 'corporateFactory', function($scope, $stateParams, menuFactory, corporateFactory){
			
			$scope.showDish = false;
			$scope.message = "Loading ...";
			$scope.dish = menuFactory.getDishes().get({id:0})
				.$promise.then(
					function(response){
						$scope.dish = response;
						$scope.showDish = true;
					},
					function(response) {
						$scope.message = "Error: "+response.status + " " + response.statusText;
					}
				);
			
			$scope.showPromotion = false;
			$scope.promotionMessage = "Loading ...";
			$scope.promotion = menuFactory.getPromotions().get({id:0})
				.$promise.then(
					function(response){
						$scope.promotion = response;
						$scope.showPromotion = true;
					},
					function(response){
						$scope.promotionMessage = "Error: "+response.status + " " + response.statusText;
					}
				);
			
			$scope.showSpecialist = false;
			$scope.specialistMessage = "Loading ...";
			$scope.specialist = corporateFactory.getLeaders().get({id:3})
				.$promise.then(
					function(response){
						$scope.specialist = response;
						$scope.showSpecialist = true;
					},
					function(response){
						$scope.specialistMessage = "Error: "+response.status + " " + response.statusText;
					}
			);
			
		}])

		.controller('AboutController', ['$scope', '$stateParams', 'corporateFactory', function($scope, $stateParams, corporateFactory){
			
			$scope.leadershipMessage = "Loading ...";
		 	$scope.showLeadership = false;
			corporateFactory.getLeaders().query(
                function(response) {
                    $scope.leadership = response;
                    $scope.showLeadership = true;
                },
                function(response) {
                    $scope.leadershipMessage = "Error: "+response.status + " " + response.statusText;
                }
			);			
			
			
		}])


;
