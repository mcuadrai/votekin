tagInputEventCalled = false;

var newpost = function(){};

newpost.isRecentItemIconClick = false

postNotCalled = true;

$(document).ready(function(){
	
	newpost.preventChild();
	
	$('#recent-post-after').submit(function(){
			
			
			//$('#recent-post-after').submit();
		return false;
	});
	
	
	
	$(document).on('click',function(e){
		if(!newpost.isRecentItemIconClick){
			newpost.removeItems();
		}
		
		newpost.isRecentItemIconClick = false;
		
	})
	
	 $("#add-post-text").keyup(function(e) {
		try{
		//var words = this.value.match(/\S+/g)[0].length;
			
			var words = this.value.length;

	    if (words > 150) {
	      // Split the string on first 200 words and rejoin on spaces
	      var trimmed = $(this).val().split(/\s+/, 150).join(" ");
	      // Add a space at the end to make sure more typing creates new words
	      $(this).val(trimmed + " ");
	    }
	    else {
	      //$('#display_count').text(words);
	      $('#word_left').html(150-words);
	    }
		}catch (e) {
			$('#word_left').html('');
		}
       while($(this).outerHeight() < this.scrollHeight + parseFloat($(this).css("borderTopWidth")) + parseFloat($(this).css("borderBottomWidth"))) {
            $(this).height($(this).height()+1);
        };
	});
	
	$('#recent-post-init').on('click',function(e){
		$('#recent-post-init').hide();
		$('#recent-post-after').show();
	});
	
	$('#location-icon').on('click',function(e){
		newpost.removeItems();
		$('.recent-post-icons').removeClass('active');
		$('#location-icon').addClass('active')
		$('#location').show();
		
	});
	
	$('#recent-post-location').on('click',function(e){
		
		newpost.removeItems();
		$('.recent-post-icons').removeClass('active');
		$('#location-icon').addClass('active')
		$('#location').show();
		
	});
	
	$('#recent-post-tag-icon').on('click',function(e){
		newpost.removeItems();
		$('.recent-post-icons').removeClass('active');
		$('#recent-post-tag-icon').addClass('active');
		$('#recent-post-tag-list').show();
	});
	
	/*$('#recent-post-tag').on('click',function(e){
		newpost.removeItems();
		$('.recent-post-icons').removeClass('active');
		$('#recent-post-tag-icon').addClass('active');
		$('#recent-post-tag-list').show();
	});*/
	
	
	
	$('#tags-input').on('change', function() {
		
		tagInputEventCalled = true;
		
		$('#recent-post-tag').show();
		
		addTagsVal($('#tags-input').val());
		
		$('#tags-input').val('');
		var x = $('#tags_input_chosen').children('.chosen-single')[0]
		var y = $(x).children()[0];
		$(y).html('')
		
		var searchInputTagX = $('#tags_input_chosen').children('.chosen-drop').children('.chosen-search')[0];
		var searchInputTag = $(searchInputTagX).children()[0];
		$(searchInputTag).val('');
		
	});
	
	//Cat
	
	$('#recent-post-cat-icon').on('click',function(e){
		newpost.removeItems();
		$('#recent-post-cat-icon').addClass('active');
		$('#recent-post-cat-list-div').show();
	});
	
	$('#recent-post-cat').on('click',function(e){
		newpost.removeItems();
		$('#recent-post-cat-icon').addClass('active');
		$('#recent-post-cat-list-div').show();
	});
	
	
	
	$('#recent-post-cat-list').on('change', function() {
		$('#recent-post-cat').show();
		$('#recent-post-cat-text').html($('#recent-post-cat-list option:selected').html());
		newpost.removeItems();
	});
	
	//file selection
	
	$('#recent-post-file').on('change',function(e){
		newpost.readURL(this);
	});
	
	$('#recent-post-img-del').on('click',function(e){
		$('#recent-post-file').val('')
		$('#recent-post-img').hide();
	});
	
});




newpost.removeItems = function(){
	$('.recent-post-items').hide();
	$('.recent-post-icons').removeClass('active');
	newpost.isRecentItemIconClick = true;
};

newpost.preventChild = function(){
	$('.recent-post-items').on('click',function(){
		newpost.isRecentItemIconClick = true;
	});
};

newpost.selectlocation = function(){
	$('#recent-post-location').show();
	$('#recent-post-location-text').html($('#location').val());
	newpost.removeItems();
	
};

newpost.readURL=function(input) {

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

newpost.newPostSubmit = function(opt){

	
	
	var a = $('#add-post-text').val()
	if(a==null || a.trim()==''){
		return;
	}
	
	NProgress.start();
	
	$("#btnSubmit").prop("disabled", true);
	
	console.info("Clicked");
	
	var form = $('#recent-post-after')[0];
	
	var x = $('#recent-post-tag-text').children();
	
	var s = '';
	
	for(var i =0; i<x.length;i++){
		
		
		s+=$(x[i]).data('val');
		
		if(i<x.length-1){
			s+=','
		}
	}
	
	var data = new FormData(form);
	
	data.append("tags", s);
	
	 /*$('<input />').attr('type', 'hidden')
      .attr('name', "tags")
      .attr('value', s)
      .appendTo('#recent-post-after');*/
	
	var _url = "/opinion/createOpinionAndFindAll";
	
	if(opt==1){
		_url = "/debate/createAnswer";
	}
	
	$.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: _url,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

        	try{
        		if(page == 'addNewPost'){
            		window.location = '/opinion/recentposts';
            		return;
            	}
        	}catch (e) {
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
            try{
            	//var div = $('#loadMoreDiv');
				//div.html('');
				pageNumber=0;
				makeRecentPostUI();
				
            }catch (e) {
				// TODO: handle exception
			}

        },
        error: function (e) {

            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            NProgress.done();

        }
    });

	
};


function addTagsVal(str){
	var s = '<li data-val = "'+str+'">'+str+'<span class="recent-post-tag-close">x</span></li>';
	
	$('#recent-post-tag-text').append(s);
	
	$('.recent-post-tag-close').unbind();
	
	$('.recent-post-tag-close').click(function(){
		
		$(this).parent().remove()
	});
}

