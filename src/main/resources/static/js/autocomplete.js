//<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places"> 


//used for validation.js
var isLocationSelected = false;

function addressSearch(ser){
	
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({ 'address': ser }, function(results, status) {
		debugger;
	    if (status == google.maps.GeocoderStatus.OK) {
	        
	    }
	});
	
	
}

function addressSearchCallBack(response){
	console.info(response);
}

function addressAutoComplete(ser){
	
	var sessionToken = new Date().getTime();
	
	var url = 'https://maps.googleapis.com/maps/api/place/autocomplete/json?input='+ser+'&key='+MAP_API_KEY+'&sessiontoken='+sessionToken;
	//"https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+ser+"&types=geocode&language=en&key="+MAP_API_KEY
	
	/*var xhttp = new XMLHttpRequest();           
    xhttp.open('GET', url, false);
    xhttp.setRequestHeader("Access-Control-Allow-Origin"," *");
    xhttp.setRequestHeader("Access-Control-Allow-Credentials"," true");
    xhttp.setRequestHeader("Access-Control-Max-Age"," 1000");
    xhttp.setRequestHeader("Access-Control-Allow-Headers"," X-Requested-With, Content-Type, Origin, Cache-Control, Pragma, Authorization, Accept, Accept-Encoding");
    xhttp.setRequestHeader("Access-Control-Allow-Methods"," PUT, POST, GET, OPTIONS, DELETE");
    xhttp.setRequestHeader('Access-Control-Allow-Origin','*');
    xhttp.setRequestHeader('Access-Control-Allow-Credentials', 'true');
    xhttp.setRequestHeader('Access-Control-Allow-Methods', 'GET, OPTIONS');
    xhttp.setRequestHeader('Access-Control-Allow-Headers', 'Content-Type');
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            var result = JSON.parse(this.response);
            console.info(result);
            if (result.results[0]) {
                latlng = result.results[0].geometry.location;
                document.cookie = 'lat=' + latlng.lat;
                document.cookie = 'lng=' + latlng.lng;
                map.setCenter(latlng);
                marker.setPosition(latlng);
            }
         }
    };  
    xhttp.send();*/
	
	 $.ajax({
		 url:url,
		 type:'GET',
		 /*headers: {
			 "Access-Control-Allow-Origin":" *",
			 "Access-Control-Allow-Credentials":" true",
			 "Access-Control-Max-Age":" 1000",
			 "Access-Control-Allow-Headers":" X-Requested-With, Content-Type, Origin, Cache-Control, Pragma, Authorization, Accept, Accept-Encoding",
			 "Access-Control-Allow-Methods":" PUT, POST, GET, OPTIONS, DELETE"
		    },*/
		beforeSend: function(xhttp) { 
			xhttp.setRequestHeader("Access-Control-Allow-Origin"," *");
		    xhttp.setRequestHeader("Access-Control-Allow-Credentials"," true");
		    xhttp.setRequestHeader("Access-Control-Max-Age"," 1000");
		    xhttp.setRequestHeader("Access-Control-Allow-Headers"," X-Requested-With, Content-Type, Origin, Cache-Control, Pragma, Authorization, Accept, Accept-Encoding");
		    xhttp.setRequestHeader("Access-Control-Allow-Methods"," PUT, POST, GET, OPTIONS, DELETE");
		      
		    },
		 dataType: 'jsonp',
		 success:function(result){
			 console.info(result);
		 }
	 });
 }
 
function initAutocomplete() {
    /*var map = new google.maps.Map(document.getElementById('map'), {
      center: {lat: -33.8688, lng: 151.2195},
      zoom: 13,
      mapTypeId: 'roadmap'
    });*/

    // Create the search box and link it to the UI element.
    var input = document.getElementById('location-select-text');
    var searchBox = new google.maps.places.SearchBox(input);
    //map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    /*map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });*/

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    
    /*google.maps.event.addDomListener(input,'click',function(){
        this.value='';
    });*/
    
    searchBox.addListener('places_changed', function() {
      var places = searchBox.getPlaces();

      if (places.length == 0) {
        return;
      }

      // Clear out the old markers.
      markers.forEach(function(marker) {
        marker.setMap(null);
      });
      markers = [];

      // For each place, get the icon, name and location.
      var bounds = new google.maps.LatLngBounds();
      places.forEach(function(place) {
        if (!place.geometry) {
          console.log("Returned place contains no geometry");
          return;
        }
        var icon = {
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(25, 25)
        };

        // Create a marker for each place.
        /*markers.push(new google.maps.Marker({
          map: map,
          icon: icon,
          title: place.name,
          position: place.geometry.location
        }));*/

        if (place.geometry.viewport) {
          // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      debugger;
    });
  }
 
 
	$(window).on('load',function() {
		//initAutocomplete()
		//bindAutocomplete();
	});