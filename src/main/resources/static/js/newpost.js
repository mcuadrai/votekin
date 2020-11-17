tagInputEventCalled = false;

newPostButtonClick = false;

var newpost = function() {
};

newpost.openNewPostDialog = function(){
	
	$('#add-post').modal('show');
	newPostButtonClick = true;
}

newpost.isRecentItemIconClick = false

postNotCalled = true;

$(document)
		.ready(
				function() {
					
					$('#add-post').on('hidden.bs.modal', function () {
					    // do something…
						newPostButtonClick = false;
					})

					newpost.preventChild();
					newpost.bindNewPostLocationAutocomplete()
					
					//Add Default cat
					
					newpost.catInit();
					
					

					$('#recent-post-after').submit(function() {

						// $('#recent-post-after').submit();
						return false;
					});

					$(document).on('click', function(e) {
						if (!newpost.isRecentItemIconClick) {
							newpost.removeItems();
						}

						newpost.isRecentItemIconClick = false;

					})

					newpost.searchTag();

					$("#add-post-text")
							.keyup(
									function(e) {
										try {
											// var words =
											// this.value.match(/\S+/g)[0].length;

											var words = this.value.length;

											if (words > 150) {
												// Split the string on first 200
												// words and rejoin on spaces
												var trimmed = $(this).val()
														.split(/\s+/, 150)
														.join(" ");
												// Add a space at the end to
												// make sure more typing creates
												// new words
												$(this).val(trimmed + " ");
											} else {
												// $('#display_count').text(words);
												$('#word_left').html(
														150 - words);
											}
										} catch (e) {
											$('#word_left').html('');
										}
										while ($(this).outerHeight() < this.scrollHeight
												+ parseFloat($(this).css(
														"borderTopWidth"))
												+ parseFloat($(this).css(
														"borderBottomWidth"))) {
											$(this)
													.height(
															$(this).height() + 1);
										}
										;
									});

					$('#recent-post-init').on('click', function(e) {
						$('#recent-post-init').hide();
						$('#recent-post-after').show();
					});

					$('#location-icon').on('click', function(e) {
						newpost.removeItems();
						$('.recent-post-icons').removeClass('active');
						$('#location-icon').addClass('active')
						$('#location').show();

					});

					$('#recent-post-location').on('click', function(e) {

						newpost.removeItems();
						$('.recent-post-icons').removeClass('active');
						$('#location-icon').addClass('active')
						$('#location').show();

					});

					$('#recent-post-tag-icon').on('click', function(e) {
						newpost.removeItems();
						$('.recent-post-icons').removeClass('active');
						$('#recent-post-tag-icon').addClass('active');
						$('#recent-post-tag-list').show();
					});

					/*
					 * $('#recent-post-tag').on('click',function(e){
					 * newpost.removeItems();
					 * $('.recent-post-icons').removeClass('active');
					 * $('#recent-post-tag-icon').addClass('active');
					 * $('#recent-post-tag-list').show(); });
					 */

					

					// Cat

					$('#recent-post-cat-icon').on('click', function(e) {
						newpost.removeItems();
						$('#recent-post-cat-icon').addClass('active');
						$('#recent-post-cat-list-div').show();
					});

					$('#recent-post-cat').on('click', function(e) {
						newpost.removeItems();
						$('#recent-post-cat-icon').addClass('active');
						$('#recent-post-cat-list-div').show();
					});

					$('.recent-post-cat-list-newpost').on('click', function() {
						$('#recent-post-cat').show();
						
						var id = $(this).data('id');
						
						$('#recent-post-cat-text').html($(this).html());
						$('#recent-post-cat-text').attr('data-id', id);
						newpost.removeItems();
					});

					// file selection

					$('#recent-post-file').on('change', function(e) {
						newpost.readURL(this);
					});

					$('#recent-post-img-del').on('click', function(e) {
						$('#recent-post-file').val('')
						$('#recent-post-img').hide();
					});

				});


/*Theam Search*/

newpost.catArr = [];

newpost.catInit = function(){
	
	
	newpost.catArr = $('.recent-post-cat-list-newpost');
	
	$('#recent-post-cat-text-newpost').on('keyup',function(){
		newpost.cat();
	})
	
	$('#recent-post-cat').show();
	
	var _cat = newpost.catArr[0];
	
	var id = $(_cat).data('id');
		
	$('#recent-post-cat-text').html($(_cat).html());
	$('#recent-post-cat-text').attr('data-id', id);
	
}

newpost.cat = function(){
	var _v = $('#recent-post-cat-text-newpost').val();
	
	if(_v.length>0){
		
		
		for(var i = 0; i<newpost.catArr.length; i++){
			var _valHtml = newpost.catArr[i];
			var _val = $(_valHtml).html();
		    if(_val.toLowerCase().search(_v.toLowerCase())>=0){
		    	$(_valHtml).removeClass('list-hide');
		    }else{
		    	$(_valHtml).removeClass('list-hide');
		    	$(_valHtml).addClass('list-hide');
		    }
		}
		
		
		
	
	}else{
		
		for(var i = 0; i<newpost.catArr.length; i++){
			var _valHtml = newpost.catArr[i];
			
			$(_valHtml).removeClass('list-hide');
			
		}
		
		
	}
	
}


newpost.removeItems = function() {
	$('.recent-post-items').hide();
	$('.recent-post-icons').removeClass('active');
	newpost.isRecentItemIconClick = true;
};

newpost.preventChild = function() {
	$('.recent-post-items').on('click', function() {
		newpost.isRecentItemIconClick = true;
	});
};

newpost.selectlocation = function(loc) {
	$('#recent-post-location').show();
	$('#recent-post-location-text').html(loc);
	newpost.removeItems();

};

newpost.readURL = function(input) {

	var img = '#recent-post-img-item';

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$(img).attr('src', e.target.result);
			$('#recent-post-img').show();
		}

		reader.readAsDataURL(input.files[0]);
	}
};

newpost.newPostSubmit = function(opt) {

	showLoader();
	var a = $('#add-post-text').val()
	if (a == null || a.trim() == '') {
		return;
	}

	// NProgress.start();

	$("#btnSubmit").prop("disabled", true);

	console.info("Clicked");

	var form = $('#recent-post-after')[0];

	var x = $('#recent-post-tag-text').children();

	var s = '';

	for (var i = 0; i < x.length; i++) {

		s += $(x[i]).data('val');

		if (i < x.length - 1) {
			s += ','
		}
	}

	var data = new FormData(form);

	data.append("tags", s);
	
	var theam = $('#recent-post-cat-text').data('id');
	data.append("themes",theam);
	
	
	var location  =$('#recent-post-location-text').html(); 
	data.append("location",location);
	/*
	 * $('<input />').attr('type', 'hidden') .attr('name', "tags")
	 * .attr('value', s) .appendTo('#recent-post-after');
	 */

	var _url = "/opinion/createOpinionAndFindAll";

	
	
	
	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : _url,
		data : data,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 600000,
		success : function(data) {

			try {
				if (page == 'addNewPost') {
					window.location = '/opinion/recentposts';
					return;
				}
			} catch (e) {
				// TODO: handle exception
			}

			console.log("SUCCESS : ", data);
			$("#btnSubmit").prop("disabled", false);

			$('#add-post-text').val('');
			$('#word_left').html('');
			$('#recent-post-tag').hide();
			$('#recent-post-tag-text').html('');
			$('#recent-post-img').hide();
			$('#recent-post-file').val('');
			alert("Post created successfully!");
			try {
				// var div = $('#loadMoreDiv');
				// div.html('');
				pageNumber = 0;
				makeRecentPostUI();

			} catch (e) {
				// TODO: handle exception
			}
			$('#add-post').modal('hide');
			hideLoader();
		},
		error : function(e) {

			console.log("ERROR : ", e);
			$("#btnSubmit").prop("disabled", false);
			$('#add-post').modal('hide');
			// NProgress.done();
			
			hideLoader();

		}
	});

};

newpost.searchTag = function() {

	var searchInputTag = $("#tags_input_box");

	// var searchInputTag =
	// $('#tags_input_chosen').children('.chosen-drop').children('.chosen-search').children('.default')[0]

	$(searchInputTag).keyup(function(event) {
		if (event.keyCode == 40) {

		} else if (event.keyCode == 38) {

		} else if (event.keyCode == 13) {
			try {
				if (tagInputEventCalled == true) {
					tagInputEventCalled = false;
					return;
				}
			} catch (e) {
				// TODO: handle exception
			}

			var string = searchInputTag.val();

			searchInputTag.val('');

			if (string == null || string.trim() == '') {
				string = $(this).val();
			}

			if (string == null || string.trim() == '') {
				return;
			}

			$(searchInputTag).val('');

			var stringArr = string.split(',');

			$('#recent-post-tag').show();

			for (var i = 0; i < stringArr.length; i++) {
				var str = stringArr[i];

				// newpost.js
				addTagsVal(str);

			}

		} else {

			clearTimeout(typingTimer);
			typingTimer = setTimeout(function() {
				serchInputTagEvents();
			}, doneTypingInterval);

			// serchInputTagEvents();
		}
	});

	var typingTimer; // timer identifier
	var doneTypingInterval = 2000; // time in ms, 5 second for example

	$(searchInputTag).on('keydown', function() {
		clearTimeout(typingTimer);
	});

	/*
	 * $(searchInputTag).on('input',function(){ serchInputTagEvents(); });
	 */

	var serchInputTagEvents = function(event) {

		var string = $(searchInputTag).val();

		var optionsList = [];

		$(".tag_input_option").each(function() {
			var x = $(this).html();
			optionsList.push(x);
		});

		// $(optResult).append(sOpt);
		// $('#tags-input').val(preValue).trigger('chosen:updated');

		$.ajax({
			url : '/opinion/tagsList',
			method : 'post',
			data : {
				'value' : string
			},
			success : function(response) {
				console.info(response);

				/*
				 * var optResultUl =
				 * $('#tags_input_chosen').children('.chosen-drop').children('.chosen-results')[0]
				 */

				var s = '';

				var a = [];
				try {

					var obj = JSON.parse(response).data;

					var sss = '<li class="dropdown-link tag_input_option">'
							+ string + '</li>'
					/*
					 * var sss = '<option class="tagopt" value="' + string +
					 * '">' + string + '</option>';
					 */

					$('#tags_input_list').empty().append(sss);

					var sOpt = '';

					for (var i = 0; i < obj.length; i++) {

						if (optionsList.indexOf(obj[i].value) < 0) {
							
							sOpt += '<li class="dropdown-link tag_input_option">'
							+ obj[i].value + '</li>'
						}

					}
					
					$('#tags_input_list').append(sOpt);

				} catch (e) {

				}
				
				newpost.tagoptionenvent();
			}
		});
	};
}

function addTagsVal(str) {
	var s = '<li data-val = "' + str + '">' + str
			+ '<span class="recent-post-tag-close"><i class="fa fa-times-circle" aria-hidden="true"></i></span></li>';

	$('#recent-post-tag-text').append(s);

	$('.recent-post-tag-close').unbind();

	$('.recent-post-tag-close').click(function() {

		$(this).parent().remove()
	});
	
	
}

newpost.tagoptionenvent = function(){
	$('.tag_input_option').unbind();
	$('.tag_input_option').on(
			'click',
			function() {

				tagInputEventCalled = true;

				$('#recent-post-tag').show();

				addTagsVal($(this).html());

				
				
				var searchInputTag = $('#tags_input_box');
						
				$(searchInputTag).val('');

			});
}

newpost.locationLiOnClick = function(){
	$('.new-post-location-li').unbind();
	
	$('.new-post-location-li').on('click',function(){
		var loc = $(this).html();
			
		newpost.selectlocation(loc);
		
	});
	
}

newpost.bindNewPostLocationAutocomplete = function() {
	
	

	  var acService = new google.maps.places.AutocompleteService(),
	    placesService = new google.maps.places.PlacesService(document.createElement('div'));

	  $("#location-select-text").autocomplete({
	    source: function(req, resp) {
	    	isLocationSelected = false;
	      acService.getPlacePredictions({
	        input: req.term,
	        types:['(regions)']
	      }, function(places, status) {
	    	  
	    	  
	        if (status === google.maps.places.PlacesServiceStatus.OK) {
	          var _places = [];
	          
	          var s = '';
	          
	          for (var i = 0; i < places.length; ++i) {
	        	  
	        	  //Create custom menu
	        	  //location-menu
	        	  
	        	  s+='<li class="dropdown-link new-post-location-li">'+places[i].description+'</li>';
	        	  
	            _places.push({
	              id: places[i].place_id,
	              value: places[i].description,
	              label: places[i].description
	            });
	          }
	          
	          $('#location-menu').html(s);
	          newpost.locationLiOnClick();
	          
	          //resp(_places);
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
