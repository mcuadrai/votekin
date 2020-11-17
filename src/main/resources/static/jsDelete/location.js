var geocoder = new google.maps.Geocoder();

function successFunction(position) {
    var lat = position.coords.latitude;
    var lng = position.coords.longitude;
    codeLatLng(lat, lng);
}

function errorFunction(){
    //alert("Geocoder failed");
}


function initialize_Location(callback) {
	if(localStorage['myLocation'] == null){
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(successFunction, errorFunction);
		 }
	}else{
		$('#location').val(localStorage['myLocation']);
		newpost.selectlocation();
		try{
			callback();
		}catch (e) {
			
		}
	}
	  

}

function codeLatLng(lat, lng) {
	 
      var loc_add='No Address found';
      var latlng = new google.maps.LatLng(lat, lng);
      geocoder.geocode({'latLng': latlng}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
      
          if (results[1]) {
         //formatted address
        	 loc_add =results[0].formatted_address   
        	
        	 var loc_ = '';
        	 
        	 for (var i=0; i<results[0].address_components.length; i++) {
                 for (var b=0;b<results[0].address_components[i].types.length;b++) {

                	 if (results[0].address_components[i].types[b] == "administrative_area_level_2") {
                         //this is the object you are looking for
                         var city= results[0].address_components[i];
                         
                         loc_+= city.long_name +', ';
                         
                         
                     }
                	 
                 //there are different types that might hold a city admin_area_lvl_1 usually does in come cases looking for sublocality type will be more appropriate
                     if (results[0].address_components[i].types[b] == "administrative_area_level_1") {
                         //this is the object you are looking for
                         var city= results[0].address_components[i];
                         
                         loc_+= city.long_name +', ';
                         
                         
                     }
                     
                     if (results[0].address_components[i].types[b] == "country") {
                         //this is the object you are looking for
                         var city= results[0].address_components[i];
                         
                         loc_+= city.long_name ;
                         
                         $.ajax({
                        	 url:'https://restcountries.eu/rest/v1/alpha/'+city.short_name,
                        	 success:function(result){
                        		 
                        		 localStorage['currencies'] = result.currencies[0]
                        	 }
                         })
                         
                     }
                 }
             }
        	 
        	 localStorage['myLocation'] = loc_; 
        	 $('#location').val(loc_);
        	 newpost.selectlocation();
         
        } else {
          //alert("No results found");
        }
      } else {
        //alert("Geocoder failed due to: " + status);
      }
    });
     
  }

		 
