//<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places"> 


//used for validation.js
var isLocationSelected = false;

function addressAutoComplete(ser){
	 $.ajax({
		 url:"https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+ser+"&types=geocode&language=en&key="+MAP_API_KEY,
		 dataType: 'jsonp',
		 success:function(result){
			 console.info(result);
		 }
	 });
 }
 
 
 function bindAutocomplete() {

	  var acService = new google.maps.places.AutocompleteService(),
	    placesService = new google.maps.places.PlacesService(document.createElement('div'));

	  $(".address-autocomplete").autocomplete({
	    source: function(req, resp) {
	    	isLocationSelected = false;
	      acService.getPlacePredictions({
	        input: req.term,
	        types:['(regions)']
	      }, function(places, status) {
	        if (status === google.maps.places.PlacesServiceStatus.OK) {
	          var _places = [];
	          for (var i = 0; i < places.length; ++i) {
	            _places.push({
	              id: places[i].place_id,
	              value: places[i].description,
	              label: places[i].description
	            });
	          }
	          resp(_places);
	        }
	      });
	    },
	    select: function(e, o) {
	      placesService.getDetails({
	        placeId: o.item.id
	      }, function(place, status) {
	    	  isLocationSelected = true;
	    	  try{
	    		  newpost.selectlocation();
	    	  }catch (e) {
			
			}
	    	  
	        if (status === google.maps.places.PlacesServiceStatus.OK) {
	        	
	        }
	      });


	    }
	  });
	}
	$(window).load(bindAutocomplete);