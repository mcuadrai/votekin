//Prevent enter button event;
$(document).ready(function() {
  $(window).keydown(function(event){
	if(event.keyCode == 13) {
		if(event.originalEvent.srcElement.className.indexOf("do-enter")<0){
			event.preventDefault();
		    return false;
		}
      
    }
  });
  
  //dropdown-menu-custom
  
  $(document).on('click',function(){
	  $('.dropdown-menu-custom').hide();
  });
  
});

function getSearchParameters() {
    var prmstr = window.location.search.substr(1);
    //return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
    return prmstr;
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

/*************************Loader Script******************************/

function showLoader() {
	$('#myProgress').show();
	  var elem = document.getElementById("myBar");   
	  var width = 1;
	  loaderIntervel = setInterval(frame, 50);
	  
	  function frame() {
	    if (width >= 90) {
	      clearInterval(loaderIntervel);
	    } else {
	      width++; 
	      elem.style.width = width + '%'; 
	    }
	  }
}

function hideLoader(){
	var elem = document.getElementById("myBar");
	elem.style.width ='100%';
	try{
		clearInterval(loaderIntervel);
	}catch (e) {
		// TODO: handle exception
	}
	
	$('#myProgress').hide();
}

/*************************Loader Script End******************************/

/*************************Search Dialog Script******************************/

var doneTypingInterval = 2000;  //time in ms, 5 second for example
var typingTimerSearchTag;

$(document).ready(function() {
	
	//Theme filter into search
	
	$.get('/opinion/findAllTheams',function(res){
		
		
		try{  
			var _v = JSON.parse(res).data;
		cat_app = new Bloodhound({
			  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
			  queryTokenizer: Bloodhound.tokenizers.whitespace,
			  local: _v
			});
		cat_app.initialize();
		
		$('#category-search').tagsinput({
			  itemValue: 'id',
			  itemText: 'name',
			  typeaheadjs: {
				  name: 'cat_app',
				  displayKey: 'name',
				    source: cat_app.ttAdapter()
				  }
			});
	
	
	
	
	}catch(e){}
	
	});
	
//Tag filter
	
	searchInputTagSearch =$('#tag-search'); 
	
	searchInputTagSearch.tagsinput();
	
	tagSearchInput = searchInputTagSearch.tagsinput('input');
	
	/*tagSearchInput.on('keyup',function(){
		if (event.keyCode == 40) {
			
		} else if (event.keyCode == 38) {
			
		}else if (event.keyCode == 13) {
			//Enter Event
			$('#tag-search').tagsinput('remove',$(this).val());
			$('#tag-search').tagsinput('add',$(this).val());
			$('#tag-search').tagsinput('refresh');
		}
	});*/
	
	
	/*tagSearchInput = searchInputTagSearch.tagsinput('input');
	
	tagSearchInput.on('keyup',function(){
		if (event.keyCode == 40) {
			
		} else if (event.keyCode == 38) {
			
		}else if (event.keyCode == 13) {
			//Enter Event
//			$('#tag-search').tagsinput('remove',$(this).val());
//			$('#tag-search').tagsinput('add',$(this).val());
//			var tagSearchInput = $('#tag-search').tagsinput('refresh');
		}else{
			
			 
			
			
			clearTimeout(typingTimerSearchTag);
			
			typingTimerSearchTag = setTimeout(function(){
	    		serchInputTagEventsSearch();
	    	}, doneTypingInterval);
			
		}
		
		
		
	});
	
		
	$(searchInputTagSearch).on('keydown', function () {
		  clearTimeout(typingTimerSearchTag);
	});*/
}); //Document Redy end


var serchInputTagEventsSearch = function(event){
	var _v = tagSearchInput.val();
//	var s = '<option value="'+_v+'"> '+_v+'</option> ';
//	searchInputTagSearch.html(s);
//	$('#tag-search').tagsinput('refresh');
	
	
	//$(optResult).append(sOpt);
	//$('#tags-input').val(preValue).trigger('chosen:updated');
	
	$.ajax({
		url:'/opinion/tagsList',
		method:'post',
		data:{'value':_v},
		success:function(response){
			console.info(response);
			
			/*var optResultUl = $('#tags_input_chosen').children('.chosen-drop').children('.chosen-results')[0]*/
			
			
			var s = '';
			
			var a = [];
			try{
				
				var obj = JSON.parse(response).data;
				
				searchInputTagSearch.tagsinput('destroy');
				tag_app = new Bloodhound({
					  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
					  queryTokenizer: Bloodhound.tokenizers.whitespace,
					  local: obj
					});
				tag_app.initialize();
				
				
				searchInputTagSearch.tagsinput({
					  itemValue: 'name',
					  itemText: 'name',
					  typeaheadjs: {
						  name: 'tag_app',
						  displayKey: 'name',
						    source: tag_app.ttAdapter()
						  }
					});
				
				
				tagSearchInput.val(_v);
				
				//searchInputTagSearch.tagsinput('refresh');
				
				/*$(optResultUl).html(s);
				$(optResult).html(sOpt);
				
				$(optResult).tagsinput('refresh');
				
				debugger;*/
				
			}catch (e) {
				
			}
			
		}
	});
};


/*************************Search Dialog Script End******************************/