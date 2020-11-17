var search = {}

search.catArr = [];

search.typingTimer; // timer identifier
search.doneTypingInterval = 2000; // time in ms, 5 second for example

$(document).ready(function(){
	
	$('#search-form').submit(function(){
		
		var _cat = $('#cat-search-result').children();
		var _catS = '';
		var l = _cat.length;
		for(var i=0; i<_cat.length;i++){
			_catS+= $(_cat[i]).data('val')+',';
		}
		
		if(_catS!=''){
			
			var input = $("<input>")
            .attr("type", "hidden")
            .attr("name", "theams").val(_catS);
						
			$(this).append(input);
		}
		
		var _tag = $('#tag-search-result').children();
		var _tagS = '';
		for(var i=0; i<_tag.length;i++){
			_tagS+= $(_tag[i]).data('val')+',';
		}
		
		if(_tagS!=''){
			
			var input = $("<input>")
            .attr("type", "hidden")
            .attr("name", "tags").val(_tagS);
						
			$(this).append(input);
			
			
		}
		
	});
	
	search.init();
	
	search.catDropShow(false);
	search.tagDropShow(false);
	
	$('#tag-search-result').hide();
	$('#cat-search-result').hide();
	
	$('#cat-search-text').on('keyup',function(){
		search.cat();
	});
	
	search.searchInputTag = $("#tag-search-text");
	
	search.searchInputTag.on('keyup',function(event){
		
		
		
		if (event.keyCode == 40) {

		} else if (event.keyCode == 38) {

		} else if (event.keyCode == 13) {
			
		}else{
			
			clearTimeout(search.typingTimer);
			
			search.typingTimer = setTimeout(function() {
				
				search.tag();
				
			}, search.doneTypingInterval);
		}
		
		search.tagCreateList($('#tag-search-text').val())
		
		
	});
	
	$('.cat-search-opt').on('click',function(event){
		var _val = $(this).html();
		var _id = $(this).data('id');
		search.catOpt(_val,_id);
		 event.preventDefault();
	     return false;
	});
	
	
	
});

/*Submit Form*/




/* Tags */

search.tag = function(){
	var _v = $('#tag-search-text').val();
	
	search.tagDropShow(true);
	
	if(_v.length>0){
		
		//Call Ajax API
		
		$.ajax({
			url : '/opinion/tagsList',
			method : 'post',
			data : {
				'value' : _v
			},
			success : function(response) {
				console.info(response);
				try{
					var obj = JSON.parse(response);
					if(obj.status=='SUCCESS'){
						
						$('.comm-tag-opt').remove();
						
						for(var i = 0; i<obj.data.length; i++){
							var _d = obj.data[i];
							
							search.tagCreateList(_d.value,1);
						}
					}
				}catch(e){
					
				}
				
			}
		});
	
	}
}

search.tagCreateList = function(_val,mode){
	var _c = 'prime-tag-opt';
	if(mode==1){
		var _c = 'comm-tag-opt';
	}
	
	search.tagDropShow(true);
	
	
	if(mode!=1){
		$('.prime-tag-opt').remove();
	}
	
	
	if(_val!=null && _val.trim()!=''){
		var s = '<li class="dropdown-link recent-post-tag-list tag-search-opt '+_c+'">'+_val+'</li>';
		
		$('#tag-search-result-dropdown').append(s);
	}
	
	
	$('.recent-post-tag-list').unbind();
	
	$('.recent-post-tag-list').on('click',function(){
		search.tagOpt($(this).html());
	})
	
}

search.tagOpt=function(_val){
	console.info(_val);
	$('#tag-search-result').show();
	var s = '';
	
	s+='<span class="badge badge-info tag-search-result-opt-pr" data-val="'+_val+'">'+_val+' <a class="tag-search-result-opt" ';
	s+='href="javascript:;"><i class="fa fa-times-circle"';
	s+='aria-hidden="true"></i></a>';
	s+='</span>';
	
	$('#tag-search-result').append(s);
	
	$('.tag-search-result-opt').unbind();
	
	$('.tag-search-result-opt').on('click',function(){
		search.tagDeleteResultOpt(this);
	})
	
};

search.tagDeleteResultOpt=function(_v){
	
	$($(_v).parent()).remove();
	
	var _c = $('#tag-search-result').children()
	if(_c.length==0){
		$('#tag-search-result').hide();
	}
}

search.tagDropShow = function(isShow){
	if(isShow){
		$('#tag-search-result-block').show();
	}else{
		$('#tag-search-result-block').hide();
		
	}
};



/* Category  */

search.catOpt=function(_val,_id){
	console.info(_val);
	$('#cat-search-result').show();
	var s = '';
	
	s+='<span class="badge badge-info cat-search-result-opt-pr" data-val="'+_val+'" id="cat-search-result-opt-pr-'+_id+'">'+_val+' <a class="cat-search-result-opt" ';
	s+='href="javascript:search.deleteCatResultOpt('+_id+');"><i class="fa fa-times-circle"';
	s+='aria-hidden="true"></i></a>';
	s+='</span>';
	
	$('#cat-search-result').append(s);
	
	
	
};

search.deleteCatResultOpt=function(_v){
	var id = '#cat-search-result-opt-pr-'+_v;
	$(id).remove();
	var _c = $('#cat-search-result').children()
	if(_c.length==0){
		$('#cat-search-result').hide();
	}
}

search.init = function(){
	
	$('#cat-search-result').hide();
	
	search.catArr = $('#cat-search-result-dropdown').children()
	
	
}



search.catDropShow = function(isShow){
	if(isShow){
		$('#cat-search-result-block').show();
	}else{
		$('#cat-search-result-block').hide();
		
	}
};

search.cat = function(){
	var _v = $('#cat-search-text').val();
	
	search.catDropShow(true);
	
	if(_v.length>0){
		
	
		var s = '';
		for(var i = 0; i<search.catArr.length; i++){
			var _valHtml = search.catArr[i];
			var _val = $(_valHtml).html();
		    if(_val.toLowerCase().search(_v.toLowerCase())>=0){
		    	$(_valHtml).removeClass('list-hide');
		    }else{
		    	$(_valHtml).removeClass('list-hide');
		    	$(_valHtml).addClass('list-hide');
		    }
		}
		
		
		
	
	}else{
		
		for(var i = 0; i<search.catArr.length; i++){
			var _valHtml = search.catArr[i];
			
			$(_valHtml).removeClass('list-hide');
			
		}
		
		
	}
}