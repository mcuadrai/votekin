
//var searchOff = true;

jQuery(document).ready(function($) {
	
	"use strict";
	
	
	
	/*$('#search-form').click(function(){
		searchOff = false;
	})*/
	
//------- Notifications Dropdowns
  $('.top-area > .setting-area > li').on("click",function(){
	$(this).siblings().children('div').removeClass('active');
	$(this).children('div').addClass('active');
	return false;
  });
//------- remove class active on body
  $("body *").not('.top-area > .setting-area > li').on("click", function() {
	$(".top-area > .setting-area > li > div").removeClass('active');		
 });
	

//--- user setting dropdown on topbar	
/*$('.user-img').on('click', function() {
	$('.user-setting').toggleClass("active");
	return false;
});*/

$(document).on("click", function () {
	$(".menu-toggle").removeClass('active');
	$('.navbar-collapse').collapse('hide')
	/*if(searchOff){
		//$('.header-search').hide();
	}*/
	//searchOff = true;
	
});




$(".menu-toggle-btn").on("click", function () {
	$(".menu-toggle").removeClass('active');
	var x = $(this).find(".menu-toggle");
	$(x[0]).toggleClass("active");
	return false;
});
	
//--- side message box	
$('.friendz-list > li, .chat-users > li').on('click', function() {
	$('.chat-box').addClass("show");
	return false;
});	
	$('.close-mesage').on('click', function() {
		$('.chat-box').removeClass("show");
		return false;
	});	
	
//------ scrollbar plugin
	if ($.isFunction($.fn.perfectScrollbar)) {
		$('.dropdowns, .twiter-feed, .invition, .followers, .chatting-area, .peoples, #people-list, .chat-list > ul, .message-list, .chat-users, .left-menu').perfectScrollbar();
	}

/*--- socials menu scritp ---*/	
	$('.trigger').on("click", function() {
	    $(this).parent(".menu").toggleClass("active");
	  });
	
/*--- emojies show on text area ---*/	
	$('.add-smiles > span').on("click", function() {
	    $(this).parent().siblings(".smiles-bunch").toggleClass("active");
	  });

// delete notifications
$('.notification-box > ul li > i.del').on("click", function(){
    $(this).parent().slideUp();
	return false;
  }); 	

/*--- socials menu scritp ---*/	
	$('.f-page > figure i').on("click", function() {
	    $(".drop").toggleClass("active");
	  });

//===== Search Filter =====//
	(function ($) {
	// custom css expression for a case-insensitive contains()
	jQuery.expr[':'].Contains = function(a,i,m){
	  return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase())>=0;
	};

	function listFilter(searchDir, list) { 
	  var form = $("<form>").attr({"class":"filterform","action":"#"}),
	  input = $("<input>").attr({"class":"filterinput","type":"text","placeholder":"Search Contacts..."});
	  $(form).append(input).appendTo(searchDir);

	  $(input)
	  .change( function () {
		var filter = $(this).val();
		if(filter) {
		  $(list).find("li:not(:Contains(" + filter + "))").slideUp();
		  $(list).find("li:Contains(" + filter + ")").slideDown();
		} else {
		  $(list).find("li").slideDown();
		}
		return false;
	  })
	  .keyup( function () {
		$(this).change();
	  });
	}

//search friends widget
	$(function () {
	  listFilter($("#searchDir"), $("#people-list"));
	});
	}(jQuery));	

//progress line for page loader
	$('body').show();
	//NProgress.start();
	//setTimeout(function() { NProgress.done(); $('.fade').removeClass('out'); }, 2000);
	
//--- bootstrap tooltip	
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip();
	});
	
// Sticky Sidebar & header
	if($(window).width() < 769) {
		jQuery(".sidebar").children().removeClass("stick-widget");
	}

	if ($.isFunction($.fn.stick_in_parent)) {
		$('.stick-widget').stick_in_parent({
			parent: '#page-contents',
			offset_top: 60,
		});

		
		$('.stick').stick_in_parent({
		    parent: 'body',
            offset_top: 0,
		});
		
	}
	
/*--- topbar setting dropdown ---*/	
	$(".we-page-setting").on("click", function() {
	    $(".wesetting-dropdown").toggleClass("active");
	  });	
	  
/*--- topbar toogle setting dropdown ---*/	
$('#nightmode').on('change', function() {
    if ($(this).is(':checked')) {
        // Show popup window
        $(".theme-layout").addClass('black');	
    }
	else {
        $(".theme-layout").removeClass("black");
    }
});

//chosen select plugin
if ($.isFunction($.fn.chosen)) {
	$("select").chosen();
}

//----- add item plus minus button
if ($.isFunction($.fn.userincr)) {
	$(".manual-adjust").userincr({
		buttonlabels:{'dec':'-','inc':'+'},
	}).data({'min':0,'max':20,'step':1});
}	
	
if ($.isFunction($.fn.loadMoreResults)) {
	
	$('.loadMore').loadMoreResults({
		displayedItems: 7,
		showItems: 3,
		button: {
		  'class': 'btn-load-more',
		  'text': 'Load More',
		  'id' : 'btn-load-more'
		}
	});	
}


	//===== owl carousel  =====//
	if ($.isFunction($.fn.owlCarousel)) {
		$('.sponsor-logo').owlCarousel({
			items: 6,
			loop: true,
			margin: 30,
			autoplay: true,
			autoplayTimeout: 1500,
			smartSpeed: 1000,
			autoplayHoverPause: true,
			nav: false,
			dots: false,
			responsiveClass:true,
				responsive:{
					0:{
						items:3,
					},
					600:{
						items:3,

					},
					1000:{
						items:6,
					}
				}

		});
	}
	
// slick carousel for detail page
	if ($.isFunction($.fn.slick)) {
	$('.slider-for-gold').slick({
		slidesToShow: 1,
		slidesToScroll: 1,
		arrows: false,
		slide: 'li',
		fade: false,
		asNavFor: '.slider-nav-gold'
	});
	
	$('.slider-nav-gold').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		asNavFor: '.slider-for-gold',
		dots: false,
		arrows: true,
		slide: 'li',
		vertical: true,
		centerMode: true,
		centerPadding: '0',
		focusOnSelect: true,
		responsive: [
		{
			breakpoint: 768,
			settings: {
				slidesToShow: 3,
				slidesToScroll: 1,
				infinite: true,
				vertical: false,
				centerMode: true,
				dots: false,
				arrows: false
			}
		},
		{
			breakpoint: 641,
			settings: {
				slidesToShow: 3,
				slidesToScroll: 1,
				infinite: true,
				vertical: true,
				centerMode: true,
				dots: false,
				arrows: false
			}
		},
		{
			breakpoint: 420,
			settings: {
				slidesToShow: 3,
				slidesToScroll: 1,
				infinite: true,
				vertical: false,
				centerMode: true,
				dots: false,
				arrows: false
			}
		}	
		]
	});
}
	
//---- responsive header
	
$(function() {

	//	create the menus
	$('#menu').mmenu();
	$('#shoppingbag').mmenu({
		navbar: {
			title: 'General Setting'
		},
		offCanvas: {
			position: 'right'
		}
	});

	//	fire the plugin
	$('.mh-head.first').mhead({
		scroll: {
			hide: 200
		}
		
	});
	$('.mh-head.second').mhead({
		scroll: false
	});

	
});		

//**** Slide Panel Toggle ***//
	  $("span.main-menu").on("click", function(){
	     $(".side-panel").addClass('active');
		  $(".theme-layout").addClass('active');
		  return false;
	  });

	  $('.theme-layout').on("click",function(){
		  $(this).removeClass('active');
	     $(".side-panel").removeClass('active');
		  
	     
	  });

	  
// login & register form
	$('button.signup').on("click", function(){
		//$('.login-reg-bg').addClass('show');
		window.location = '/signup';
		return false;
	  });
	  
	  $('.already-have').on("click", function(){
		//$('.login-reg-bg').removeClass('show');
		  window.location = '/login';
		return false;
	  });
	
//----- count down timer		
	if ($.isFunction($.fn.downCount)) {
		$('.countdown').downCount({
			date: '11/12/2018 12:00:00',
			offset: +10
		});
	}
	
/** Post a Comment **/
jQuery(".post-comt-box textarea").on("keydown", function(event) {

	if (event.keyCode == 13) {
		var comment = jQuery(this).val();
		var parent = jQuery(".showmore").parent("li");
		var comment_HTML = '	<li><div class="comet-avatar"><img src="images/resources/comet-1.jpg" alt=""></div><div class="we-comment"><div class="coment-head"><h5><a href="time-line.html" title="">Jason borne</a></h5><span>1 year ago</span><a class="we-reply" href="#" title="Reply"><i class="fa fa-reply"></i></a></div><p>'+comment+'</p></div></li>';
		$(comment_HTML).insertBefore(parent);
		jQuery(this).val('');
	}
}); 
	
//inbox page 	
//***** Message Star *****//  
    $('.message-list > li > span.star-this').on("click", function(){
    	$(this).toggleClass('starred');
    });


//***** Message Important *****//
    $('.message-list > li > span.make-important').on("click", function(){
    	$(this).toggleClass('important-done');
    });

    

// Listen for click on toggle checkbox
	$('#select_all').on("click", function(event) {
	  if(this.checked) {
	      // Iterate each checkbox
	      $('input:checkbox.select-message').each(function() {
	          this.checked = true;
	      });
	  }
	  else {
	    $('input:checkbox.select-message').each(function() {
	          this.checked = false;
	      });
	  }
	});


	$(".delete-email").on("click",function(){
		$(".message-list .select-message").each(function(){
			  if(this.checked) {
			  	$(this).parent().slideUp();
			  }
		});
	});

// change background color on hover
	$('.category-box').hover(function () {
		$(this).addClass('selected');
		$(this).parent().siblings().children('.category-box').removeClass('selected');
	});
	
	
//------- offcanvas menu 

	const menu = document.querySelector('#toggle');  
	const menuItems = document.querySelector('#overlay');  
	const menuContainer = document.querySelector('.menu-container');  
	const menuIcon = document.querySelector('.canvas-menu i');  

	function toggleMenu(e) {
		menuItems.classList.toggle('open');
		menuContainer.classList.toggle('full-menu');
		menuIcon.classList.toggle('fa-bars');
		menuIcon.classList.add('fa-times');
		e.preventDefault();
	}

	if( menu ) {
		menu.addEventListener('click', toggleMenu, false);	
	}
	
// Responsive nav dropdowns
	$('.offcanvas-menu li.menu-item-has-children > a').on('click', function () {
		$(this).parent().siblings().children('ul').slideUp();
		$(this).parent().siblings().removeClass('active');
		$(this).parent().children('ul').slideToggle();
		$(this).parent().toggleClass('active');
		return false;
	});	
	

	var searchString  = getParam('text')
	
	if(searchString==null){
		searchString = '';
	}
	
	$('#search-text').val(searchString);

	var searchInputTagX = $('#tags_input_chosen').children('.chosen-drop').children('.chosen-search')[0];
	var searchInputTag = $(searchInputTagX).children()[0];
	
	//var searchInputTag = $('#tags_input_chosen').children('.chosen-drop').children('.chosen-search').children('.default')[0]
	
	$(searchInputTag).keyup(function(event){
		if (event.keyCode == 40) {
			
		} else if (event.keyCode == 38) {
			
		}else if (event.keyCode == 13) {
			try{
				if(tagInputEventCalled==true){
					tagInputEventCalled = false;
					return;
				}
			}catch (e) {
				// TODO: handle exception
			}
			
			var string = $('#tags-input').val();
			
			$('#tags-input').val('');
			var x = $('#tags_input_chosen').children('.chosen-single')[0]
			var y = $(x).children()[0];
			$(y).html('')
			
			if(string==null || string.trim()==''){
				string =$(this).val();
			}
			
			if(string==null || string.trim()==''){
				return;
			}
			
			$(searchInputTag).val('');
			
			var stringArr = string.split(',');
			
			$('#recent-post-tag').show();
			
			for(var i =0; i<stringArr.length; i++){
				var str = stringArr[i];
				
				//newpost.js
				addTagsVal(str);
				
			}
			
//			$.ajax({
//				url:'/opinion/addTags',
//				data:{'value':string},
//				method:'post',
//				success:function(response){
//					serchInputTagEvents();
//				}
//			});
			
			
			
			
			
			
	    }else{
	    	
	    	clearTimeout(typingTimer);
	    	typingTimer = setTimeout(function(){
	    		serchInputTagEvents();
	    	}, doneTypingInterval);
	    	
	    	//serchInputTagEvents();
	    }
	});
	
	var typingTimer;                //timer identifier
	var doneTypingInterval = 2000;  //time in ms, 5 second for example
	
	
	$(searchInputTag).on('keydown', function () {
		  clearTimeout(typingTimer);
		});
	
	/*$(searchInputTag).on('input',function(){
		serchInputTagEvents();
	});*/
	
var serchInputTagEvents = function(event){
	var string = $(searchInputTag).val();
	
	var optionsList = [];
	
	$("#tags-input option").each(function(){
		var x = $(this).html();
		optionsList.push(x);
	});
	
	
	
	//$(optResult).append(sOpt);
	//$('#tags-input').val(preValue).trigger('chosen:updated');
	
	$.ajax({
		url:'/opinion/tagsList',
		method:'post',
		data:{'value':string},
		success:function(response){
			console.info(response);
			
			/*var optResultUl = $('#tags_input_chosen').children('.chosen-drop').children('.chosen-results')[0]*/
			
			
			var s = '';
			
			var a = [];
			try{
				
				var obj = JSON.parse(response).data;
				
				var sss = '<option class="tagopt" value="'+string+'">'+string+'</option>';
				
				$('#tags-input').empty().append(sss);
				
				var sOpt = '';
				var optResult = $('#tags-input');
				var preValue = $('#tags-input').val();
				
				
				
				for(var i=0; i<obj.length;i++){
					
					if(optionsList.indexOf(obj[i].value)<0){
						sOpt+='<option value="'+obj[i].value+'">'+obj[i].value+'</option>';
					}
					
					
					
					
				}
				
				
				
				$(optResult).append(sOpt);
				$('#tags-input').val(preValue).trigger('chosen:updated');
				$(searchInputTag).val(string)
				/*$(optResultUl).html(s);
				$(optResult).html(sOpt);
				
				$(optResult).tagsinput('refresh');
				
				debugger;*/
				
			}catch (e) {
				
			}
			
		}
	});
};
	
	/*$('#search-text').on('click',function(){
		searchOff = false;
	    $('.header-search').show();
	    
	});*/
	
	
	
	
	
});//document ready end




var getParam = function(name){
	if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
	      return decodeURIComponent(name[1]);
};

function copyUrl(copyText,tooltipId) {
	var loc = window.location.hostname+":"+window.location.port+copyText
	
	const el = document.createElement('textarea');
	  el.value = loc;
	  document.body.appendChild(el);
	  el.select();
	  document.execCommand('copy');
	  document.body.removeChild(el);
	  
	  $('#'+tooltipId).show('slow');
	  
	  setTimeout(function () {
		  $('#'+tooltipId).hide('slow');
		}, 2000);
	   
	}

function getSearchParameters() {
    var prmstr = window.location.search.substr(1);
    //return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
    return prmstr;
}

function transformToAssocArray( prmstr ) {
  var params = {};
  var prmarr = prmstr.split("&");
  for ( var i = 0; i < prmarr.length; i++) {
      var tmparr = prmarr[i].split("=");
      if(params[tmparr[0]] == null){
    	  params[tmparr[0]] = tmparr[1];  
      }else{
    	  var x = params[tmparr[0]]
    	  if(Array.isArray(x)){
    		 x.push(tmparr[1]);
    		 params[tmparr[0]] = x
    	  }else{
    		  params[tmparr[0]] = [x,tmparr[1]]; 
    	  }
      }
      
  }
  return params;
}

function chkOpc(opc,userId){
	
	for(let i=0;i<opc.choiceVotes.length;i++){
		if(opc.choiceVotes[i].user.id==userId){
			return true;
		}
	}
	
	/*opc.choiceVotes.forEach(function(cv){
		if(cv.user.id==userId){
			return true;
		}
	});*/
	
	return false;
}

function alert(msg){
	
	$('#alert-msg').html(msg);
	$('#alert-msg').show();
	setTimeout(function(){
		$('#alert-msg').html('');
		$('#alert-msg').hide(); 
	}, 3000);
	
	
}
