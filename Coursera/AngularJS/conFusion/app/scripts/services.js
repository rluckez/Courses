'use strict';

angular.module('confusionApp')

		.constant("baseUrl","http://localhost:4000/") 		

        .service('menuFactory', ['$resource', 'baseUrl', function($resource, baseUrl) {
    
			this.getDishes = function(){
				return $resource(baseUrl + "dishes/:id", null,  {'update':{method:'PUT' }});
			};                
			
			this.getPromotions = function(){
				return $resource(baseUrl + "promotions/:id", null);
			};
                        
        }])

        .factory('corporateFactory', ['$resource', 'baseUrl', function($resource, baseUrl) {
    
            var corpfac = {};
			
			corpfac.getLeaders = function(){
				return $resource(baseUrl + "leadership/:id", null);
			};
			
			return corpfac;
    
        }])

		.service('feedbackFactory', ['$resource', 'baseUrl', function($resource, baseUrl){
			
			this.getFeedbacks = function(){
				//Don't need another configuration to save feedbacks on server. Just need to use method save from $resource object that get returned	
				return $resource(baseUrl + "feedback/:id", null);		
			};
			
		}])

;
