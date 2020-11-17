debateTagInputEventCalled = false;

debatePostButtonClick = false;

var debate = function() {
};



debate.isRecentItemIconClick = false

postNotCalled = true;

$(document)
		.ready(
				function() {
					
					$('#add-post').on('hidden.bs.modal', function () {
					    // do something…
						debatePostButtonClick = false;
					})

					debate.preventChild();
					debate.bindNewPostLocationAutocomplete()
					
					//Add Default cat
					
					debate.catInit();
					
					

					$('#debate-after').submit(function() {

						// $('#debate-after').submit();
						return false;
					});

					$(document).on('click', function(e) {
						if (!debate.isRecentItemIconClick) {
							debate.removeItems();
						}

						debate.isRecentItemIconClick = false;

					})

					debate.searchTag();

					$("#debate-add-post-text")
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
												$('#debate-word_left').html(
														150 - words);
											}
										} catch (e) {
											$('#debate-word_left').html('');
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

					$('#debate-init').on('click', function(e) {
						$('#debate-init').hide();
						$('#debate-after').show();
					});

					$('#debate-location-icon').on('click', function(e) {
						debate.removeItems();
						$('.debate-icons').removeClass('active');
						$('#debate-location-icon').addClass('active')
						$('#debate-location-block').show();

					});

					$('#debate-location').on('click', function(e) {

						debate.removeItems();
						$('.debate-icons').removeClass('active');
						$('#debate-location-icon').addClass('active')
						$('#debate-location').show();

					});

					$('#debate-tag-icon').on('click', function(e) {
						debate.removeItems();
						$('.debate-icons').removeClass('active');
						$('#debate-tag-icon').addClass('active');
						$('#debate-tag-list').show();
						return false;
					});

					/*
					 * $('#debate-tag').on('click',function(e){
					 * debate.removeItems();
					 * $('.debate-icons').removeClass('active');
					 * $('#debate-tag-icon').addClass('active');
					 * $('#debate-tag-list').show(); });
					 */

					

					// Cat

					$('#debate-cat-icon').on('click', function(e) {
						debate.removeItems();
						$('#debate-cat-icon').addClass('active');
						$('#debate-cat-list-div').show();
						return false;
					});

					$('#debate-cat').on('click', function(e) {
						debate.removeItems();
						$('#debate-cat-icon').addClass('active');
						$('#debate-cat-list-div').show();
					});

					$('.debate-cat-list-newpost').on('click', function() {
						$('#debate-cat').show();
						
						var id = $(this).data('id');
						
						$('#debate-cat-text').html($(this).html());
						$('#debate-cat-text').attr('data-id', id);
						debate.removeItems();
					});

					// file selection

					$('#debate-file').on('change', function(e) {
						debate.readURL(this);
					});

					$('#debate-img-del').on('click', function(e) {
						$('#debate-file').val('')
						$('#debate-img').hide();
					});

				});


/*Theam Search*/

debate.catArr = [];

debate.catInit = function(){
	
	
	debate.catArr = $('.debate-cat-list-newpost');
	
	$('#debate-cat-text-newpost').on('keyup',function(){
		debate.cat();
	})
	
	$('#debate-cat').show();
	
	var _cat = debate.catArr[0];
	
	var id = $(_cat).data('id');
		
	$('#debate-cat-text').html($(_cat).html());
	$('#debate-cat-text').attr('data-id', id);
	
}

debate.cat = function(){
	var _v = $('#debate-cat-text-newpost').val();
	
	if(_v.length>0){
		
		
		for(var i = 0; i<debate.catArr.length; i++){
			var _valHtml = debate.catArr[i];
			var _val = $(_valHtml).html();
		    if(_val.toLowerCase().search(_v.toLowerCase())>=0){
		    	$(_valHtml).removeClass('list-hide');
		    }else{
		    	$(_valHtml).removeClass('list-hide');
		    	$(_valHtml).addClass('list-hide');
		    }
		}
		
		
		
	
	}else{
		
		for(var i = 0; i<debate.catArr.length; i++){
			var _valHtml = debate.catArr[i];
			
			$(_valHtml).removeClass('list-hide');
			
		}
		
		
	}
	
}


debate.removeItems = function() {
	$('.debate-items').hide();
	$('.debate-icons').removeClass('active');
	debate.isRecentItemIconClick = true;
};

debate.preventChild = function() {
	$('.debate-items').on('click', function() {
		debate.isRecentItemIconClick = true;
	});
};

debate.selectlocation = function(loc) {
	$('#debate-location').show();
	$('#debate-location-text').html(loc);
	debate.removeItems();

};

debate.readURL = function(input) {

	var img = '#debate-img-item';

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$(img).attr('src', e.target.result);
			$('#debate-img').show();
		}

		reader.readAsDataURL(input.files[0]);
	}
};

debate.newPostSubmit = function(opt) {

	showLoader();
	var a = $('#debate-add-post-text').val()
	if (a == null || a.trim() == '') {
		return;
	}

	// NProgress.start();

	$("#btnSubmit").prop("disabled", true);

	console.info("Clicked");

	var form = $('#debate-after')[0];

	var x = $('#debate-tag-text').children();

	var s = '';

	for (var i = 0; i < x.length; i++) {

		s += $(x[i]).data('val');

		if (i < x.length - 1) {
			s += ','
		}
	}

	var data = new FormData(form);

	data.append("tags", s);
	
	var theam = $('#debate-cat-text').data('id');
	data.append("themes",theam);
	
	
	var location  =$('#debate-location-text').html(); 
	data.append("location",location);
	/*
	 * $('<input />').attr('type', 'hidden') .attr('name', "tags")
	 * .attr('value', s) .appendTo('#debate-after');
	 */

	
	data.append("originOpinionId",opId);
	
	var _url = "/debate/createAnswer";
	
	

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

			$('#debate-add-post-text').val('');
			$('#debate-word_left').html('');
			$('#debate-tag').hide();
			$('#debate-tag-text').html('');
			$('#debate-img').hide();
			$('#debate-file').val('');
			alert("Answer Posted successfully!");
			try {
				// var div = $('#loadMoreDiv');
				// div.html('');
				pageNumber = 0;
				makeRecentPostUI();

			} catch (e) {
				// TODO: handle exception
			}
			//$('#add-post').modal('hide');
			hideLoader();
		},
		error : function(e) {

			console.log("ERROR : ", e);
			$("#btnSubmit").prop("disabled", false);
			//$('#add-post').modal('hide');
			// NProgress.done();
			hideLoader();

		}
	});

};

debate.searchTag = function() {

	var searchInputTag = $("#debate-tags_input_box");

	// var searchInputTag =
	// $('#tags_input_chosen').children('.chosen-drop').children('.chosen-search').children('.default')[0]

	$(searchInputTag).keyup(function(event) {
		if (event.keyCode == 40) {

		} else if (event.keyCode == 38) {

		} else if (event.keyCode == 13) {
			try {
				if (debateTagInputEventCalled == true) {
					debateTagInputEventCalled = false;
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

			$('#debate-tag').show();

			for (var i = 0; i < stringArr.length; i++) {
				var str = stringArr[i];

				// debate.js
				debate.addTagsVal(str);

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

		$(".debate-tag_input_option").each(function() {
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

					var sss = '<li class="dropdown-link debate-tag_input_option">'
							+ string + '</li>'
					/*
					 * var sss = '<option class="tagopt" value="' + string +
					 * '">' + string + '</option>';
					 */

					$('#debate-tags_input_list').empty().append(sss);

					var sOpt = '';

					for (var i = 0; i < obj.length; i++) {

						if (optionsList.indexOf(obj[i].value) < 0) {
							
							sOpt += '<li class="dropdown-link debate-tag_input_option">'
							+ obj[i].value + '</li>'
						}

					}
					
					$('#debate-tags_input_list').append(sOpt);

				} catch (e) {

				}
				
				debate.tagoptionenvent();
			}
		});
	};
}

debate.addTagsVal=function(str) {
	var s = '<li data-val = "' + str + '">' + str
			+ '<span class="debate-tag-close"><i class="fa fa-times-circle" aria-hidden="true"></i></span></li>';

	$('#debate-tag-text').append(s);

	$('.debate-tag-close').unbind();

	$('.debate-tag-close').click(function() {

		$(this).parent().remove()
	});
	
	
}

debate.tagoptionenvent = function(){
	$('.debate-tag_input_option').unbind();
	$('.debate-tag_input_option').on(
			'click',
			function() {

				debateTagInputEventCalled = true;

				$('#debate-tag').show();

				debate.addTagsVal($(this).html());

				
				
				var searchInputTag = $('#debate-tags_input_box');
						
				$(searchInputTag).val('');

			});
}

debate.locationLiOnClick = function(){
	$('.debate-location-li').unbind();
	
	$('.debate-location-li').on('click',function(){
		var loc = $(this).html();
			
		debate.selectlocation(loc);
		
	});
	
}

debate.bindNewPostLocationAutocomplete = function() {
	
	

	  var acService = new google.maps.places.AutocompleteService(),
	    placesService = new google.maps.places.PlacesService(document.createElement('div'));

	  $("#debate-location-select-text").autocomplete({
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
	        	  
	        	  s+='<li class="dropdown-link debate-location-li">'+places[i].description+'</li>';
	        	  
	            _places.push({
	              id: places[i].place_id,
	              value: places[i].description,
	              label: places[i].description
	            });
	          }
	          
	          $('#debate-location-menu').html(s);
	          debate.locationLiOnClick();
	          
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
	    		  debate.selectlocation();
	    	  }catch (e) {
			
			}
	    	  
	        if (status === google.maps.places.PlacesServiceStatus.OK) {
	        	
	        }
	      });


	    }
	  });
	}
