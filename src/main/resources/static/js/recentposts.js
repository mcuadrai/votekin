$(document).ready(function(){
	
	/*initialize_Location(function(){
		
		newpost.selectlocation();
		
	});*/
	
	$('.btn-load-more').on('click',function(){
		console.info('clicked');
		loadMoreClick();
	});
	
	$('.filter-switch').change(function(){
		addPrefered();
	});
	
	
	
	catFilter.init();
	
	
	
	try{
		filterArr.forEach(function(f){
			if(f==1){
				$('#onPositive').prop('checked', true);
			}
			if(f==2){
				$('#onNegative').prop('checked', true);
			}
			if(f==3){
				$('#onInteresting').prop('checked', true);
			}
			if(f==9){
				$('#onPopulist').prop('checked', true);
			}
			if(f==11){
				$('#onAnnoying').prop('checked', true);
			}
			if(f==12){
				$('#onViolent').prop('checked', true);
			}
			});
		
		updateThemeFilters();
		
	}catch (e) {
		// TODO: handle exception
	}
	
	console.info(page);
	makeRecentPostUI();
});


var pageNumber = 0;

function deleteAllTheme(){
	themeFilters = null
	$.ajax({
		url:'/opinion/addThemePrefered',
		method:'post',
		data:{'theme':themeFilters},
		success:function(response){
			updateThemeFilters();
			var div = $('#loadMoreDiv');
			div.html('');
			pageNumber=0;
			makeRecentPostUI();
		}
	});
}

function updateThemeFilters(){
	try{
		
		var _vfc = $('.filter-cat')
		
		//var thul = $('#filter-cat')
		var s1 = '';
		var s2 = '';
		if(themeFilters!=null && themeFilters!=''){
		
		var thf= JSON.parse(themeFilters);
		
		
		thf.forEach(function(t){
			s1+='<li><a href="javascript:;" title="" class="theam-filters" data-id="'+t.id+'" data-name="'+t.name+'" >'+t.name+'<span class="close-span"><i class="fa fa-times" aria-hidden="true"></i></span></a></li>';
			s2+='<li><a href="javascript:;" title="" class="theam-filters theam-filters-mob" data-id="'+t.id+'" data-name="'+t.name+'" >'+t.name+'<span class="close-span"><i class="fa fa-times" aria-hidden="true"></i></span></a></li>';
		});
		
		
		
		}
		
		$(_vfc[0]).html(s1);
		$(_vfc[1]).html(s2);
		
		
		
		
		
		$( ".theam-filters").unbind( "click" );
		
		$('.theam-filters').click(function(){
			var j = {
					id:$(this).data('id'),
					name:$(this).data('name')
			}
			removeThemeFilter(j);
		});
		
	}catch (e) {
		// TODO: handle exception
	}
	
	
	
}

function removeThemeFilter(tj){
	var thf = [];
	
	var tf = $('.theam-filters');
	
		
		var idx = null;
		
		for(var i = 0; i<tf.length;i++){
			if(!$(tf[i]).hasClass('theam-filters-mob')){
				var _id = $(tf[i]).data('id');
				var _name = $(tf[i]).data('name');
				if(_id != tj.id){
					var j = {
							id:_id,
							name:_name
					}
					
					thf.push(j)
				}
			}
			
		}
		
		if(thf.length>0){
			themeFilters = JSON.stringify(thf);
		}else{
			themeFilters = null;
		}
		
		
		
		$.ajax({
			url:'/opinion/addThemePrefered',
			method:'post',
			data:{'theme':themeFilters},
			success:function(response){
				updateThemeFilters();
				var div = $('#loadMoreDiv');
				div.html('');
				pageNumber=0;
				makeRecentPostUI();
			}
		});
		
	 
	
	
	
}

function addThemeFilter(tj){
	var thf = [];
	if(themeFilters !=null && themeFilters != ''){
		var thf= JSON.parse(themeFilters);
		
		for(var i = 0; i<thf.length;i++){
			if(thf[i].id == tj.id){
				return;
			}
		}
		
		
	} 
	
	thf.push(tj)
	
	themeFilters = JSON.stringify(thf);
	
	$.ajax({
		url:'/opinion/addThemePrefered',
		method:'post',
		data:{'theme':themeFilters},
		success:function(response){
			updateThemeFilters();
			var div = $('#loadMoreDiv');
			div.html('');
			pageNumber=0;
			makeRecentPostUI();
		}
	});
}



function loadMoreClick(){
	pageNumber+=1;
	makeRecentPostUI()
}

function makeRecentPostUI(){
		
	 //NProgress.start();
	showLoader();
	 var np= this
	var u='/opinion/recentpostsAjax?pageNumber='+pageNumber+"&";
	
	if(page == "debate"){
		u = '/debate/ajax/'+opId+'/'+pageNumber;
	}else{
		var p = getSearchParameters();
		if(p==''){
			
			try{
				var tf = $('.theam-filters');
				
				tf.each(function(t,e){
					if(!$(e).hasClass('theam-filters-mob')){
						console.info($(e).data('name'));
						u+='theams='+$(e).data('name')+'&';
					}
					
				});
			}catch (e) {
				// TODO: handle exception
			}
			
			//choiceId
			try{
				var chks = $('input[name="seeonly[]"]:checked');
				for(var k = 0; k<chks.length;k++ ){
					var x = chks[k];
					u+='choiceId='+$(x).val()+'&';
				}
			}catch (e) {
				// TODO: handle exception
			}
			
			
			//Theams
			
			
			
			
		}
		u+=p;
	} 
	 
	
	/*for (var key in p) {
	    // check if the property/key is defined in the object itself, not in parent
	    if (p.hasOwnProperty(key)) {           
	        console.log(key, p[key]);
	        
	        u+=key+'='+p[key]+'&';
	        
	    }
	}*/
	
	
	
		
	$.ajax({
		url:u,
		method:'get',
		success:function(response){
			
			
			//console.info(response);
			var obj = JSON.parse(response)
			console.info(obj);
			var div = $('#loadMoreDiv');
			
			var s='';
			if(obj.status=="SUCCESS"){
				
				if(page=='findComments'){
					
					var filter = obj.data.filter;
					
					$('#search-text').val(filter.text);
					
					//search.tagOpt
					
					var tagsArr = filter.tags;
					try{
					for(var i = 0; i<tagsArr.length; i++){
						
						var _s = tagsArr[i];
						
						if(_s!=null && _s.trim()!=''){
							search.tagOpt(tagsArr[i]);
						}
						
					}
					}catch (e) {
						// TODO: handle exception
					}
					
					//$('#searchChk-1').prop('checked', true);
					
					var choiceIdArr = filter.choiceId;
					
					try{
					for(var i = 0; i<choiceIdArr.length; i++){
						
						var _s = '#searchChk-'+choiceIdArr[i];
						
						$(_s).prop('checked', true);
						
					}
					}catch (e) {
						// TODO: handle exception
					}
					
					var theamsArr = filter.theams;
					try{
					for(var i = 0; i<theamsArr.length; i++){
						
						var _s = theamsArr[i];
						
						if(_s!=null && _s.trim()!=''){
							
							for(var i = 0; i<search.catArr.length; i++){
								var _valHtml = search.catArr[i];
								var _val = $(_valHtml).html();
							    if(_val == _s){
							    	
							    	var _val = $(_valHtml).html();
									var _id = $(_valHtml).data('id');
									search.catOpt(_val,_id);
							    	
							    }
							}
							
							
						}
						
					}
				}catch (e) {
					// TODO: handle exception
				}
					
				}
				
				
				
				
				
				
				for(var i=0;i<obj.data.list.length;i++){
					var d = obj.data.list[i];
					s+='<div class="friend-info debate-info">';
					s+='	<figure>';
					if(d.user.ciudadano!=null && d.user.ciudadano.fotoAvatarDebate != null){
						
						s+='				<img src="/users/uploads/'+d.user.ciudadano.fotoAvatarDebate+'" alt="">';
					}else{
						s+='				<img src="/images/resources/friend-avatar10.jpg" alt="">';
					}
					s+='	</figure>';
					s+='	<div class="post-meta">';
					s+='		<ins>';
					s+='			<a href="#" title="">'+d.user.name+'</a>';
					s+='			<div class="published-date">';
					s+='				<span>'+formateDate(d.creationDate)+'</span>';
					s+='			</div>';
					s+='		</ins>';
					s+='		<div class="description" style="word-wrap: break-word">';
					s+='			<p>'+d.user.accountName+'</p>';
					s+='		</div>';
					s+='	</div>';
					s+='	<div class="post-desc">';
					var postText = d.text.replace(/\n/g, "<br />");
					s+='		<p>'+postText+'</p>';
					s+='	</div>';
					if(d.image!=null){
						
						s+='	<div class="postimg">';
						s+='				<img src="/users/uploads/' + d.image+'"  alt="">';
						s+='	</div>';
					}
					
					s+='	<div class="post-detail">';
					if(d.tags!=null && d.tags!=''){
						s+='		<div class="post-details">';
						s+='			<img src="/images/tag_middle_part.png" title="Tag" alt="">';
						s+='			<span>'+d.tags+'</span>';
						s+='		</div>';
					}
					
					if(d.themes!=null && d.themes!=''){
						let th = '';
						
						d.themes.forEach(function(idx,v){
							if(v<d.themes.length-1)
								th+=idx.name+',';
							else
								th+=idx.name;
							
						});
						
						s+='		<div class="post-details">';
						s+='			<img src="/images/politics_middle_part.png" title="Politics"';
						s+='				alt=""> <span>'+th+'</span>';
						s+='		</div>';
					}
					
					if(d.location!=null && d.location!=''){
						s+='		<div class="post-details">';
						s+='			<img src="/images/location_middle_part.png" title="Location"';
						s+='				alt=""> <span>'+d.location+'</span>';
						s+='		</div>';
					}
					
					
					s+='		<div class="post-details">';
					s+='			<a href="/debate/'+d.id+'" class="see-debate">See Debate</a>';
					s+='		</div>';
					s+='	</div>';
					s+='	<div class="post-plans">';
					
					//Start 
					
					
					d.evaluacionesDeOpinion.forEach(function(ev){
						
						let tp = ev.tipoEvaluacion.id;
						
						if(tp==4){
							
							s+='		<div class="submit-btns climet  pull-right">';
						}else{
							
							s+='		<div class="submit-btns aggrement">';
						}
						
						
						
						
						
						ev.opinionChoices.forEach(function(oc){
							
							let isUserSame = chkOpc(oc,obj.currentUserId);
							
							
							
							if(isUserSame && tp==4){
								s+='					<a href="javascript:;" title="'+oc.choice.name+'" class="'+oc.choice.name+'-initial active Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
								s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
								s+='					</a>';	
							}else if(!isUserSame && tp==4){
								s+='					<a href="javascript:;" title="'+oc.choice.name+'" class="'+oc.choice.name+'-initial Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
								s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
								s+='					</a>';
							}else if(isUserSame && tp==1){
								s+='					<a href="javascript:;" title="'+oc.choice.name+'" class="'+oc.choice.name+'-initial active social Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
								s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
								s+='					</a>';
							}else{
								s+='					<a href="javascript:;" title="'+oc.choice.name+'" class="'+oc.choice.name+'-initial social Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
								s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
								s+='					</a>';
							}
							
							
						});
						s+='				</div>';
					});
					
					
					
					
					s+='	</div>';
					s+='	<div class="new_posr_toggle">';
					s+='		<a href="javascript:newpost.openMenu(\'#nav-item-post-'+d.id+'\');" class="pull-right"> <img';
					s+='			src="/images/down_arrow.png" alt="">';
					s+='		</a>';
					s+='		<ul class="post-menu nav-item-post dropdown-menu-custom" style="display:none" id="nav-item-post-'+d.id+'">';
					s+='			<li><a href="javascript:copyUrl(\'/debate/'+d.id+'\',\'navbar-tooltip-'+d.id+'\');">Copy Link</a></li>';
					s+='		</ul>';
					s+='		<span class="copied-content" id="navbar-tooltip-'+d.id+'" style="display: none">Copied</span>';		
					s+='	</div>';
					s+='</div>';
					
				}
			}
			
			if(pageNumber==0){
				div.html('');
			}
			
			if(obj.data.list.length==0){
				pageNumber-=1;
				
			}else{
				div.append(s);
				Voto.createEvent()
			}
			
			
			
			//NProgress.done();
			
			
			
			;
			
			/*$('.btn-load-more').on('click',function(){
				console.info('clicked');
				loadMoreClick();
			});*/
			hideLoader();
		}
	
	
	//NProgress.done();
	});
	
	/*
	*/
	
}

newpost.openMenu = function(_id){
	$('.nav-item-post').hide();
	$(_id).show('slow');
}

function addPrefered(){
	var chks = $('input[name="seeonly[]"]:checked');
	var p = '';
	for(var k = 0; k<chks.length;k++ ){
		var x = chks[k];
		p+=$(x).val()+',';
		
	}
	
	$.ajax({
		url:'/opinion/addPrefered',
		method:'post',
		data:{'choices':p},
		success:function(response){
			console.info(response);
			var div = $('#loadMoreDiv');
			div.html('');
			pageNumber=0;
			makeRecentPostUI();
		}
	});
}

function formateDate(d){
	
	//: 12-December-2018 2:15pm 
	//{"date":{"year":2019,"month":2,"day":13},"time":{"hour":16,"minute":22,"second":54,"nano":0}}
	var _date = d.date.day+"-";
	
	var _month= ["January", "February", "March", "April", "May", "June",
		"July", "August", "September", "October", "November", "December"][d.date.month-1];
	
	_date+=_month+'-'+d.date.year;
	
	var _am = 'am';
	var _time = d.time.hour;
	if(d.time.hour>11){
		_am = 'pm';
		if(d.time.hour>12){
			_time = d.time.hour - 12; 
		}
	}
	
	_date+=' '+_time+':'+d.time.minute+_am;
	
	return _date;
	
}



/* Category  */

var catFilter = {};
catFilter.catArr = [];
catFilter.catArrMob = [];




catFilter.init = function(){
	
	$('.filter-cat-list-li').on('click', function() {
		
		var tj = $(this).data('val');
		addThemeFilter(tj);
		catFilter.catDropShow(false);
		
	});
	
	$('#filter-cat-list-text').on('keyup',function(){
		catFilter.cat();
	});
	
	$('#filter-cat-list-text').on('click',function(event){
		catFilter.cat();
		event.preventDefault();
		return false;
	});
	
	$('#filter-cat-list-text-mob').on('keyup',function(){
		catFilter.cat(1);
	});
	
	$('#filter-cat-list-text-mob').on('click',function(event){
		catFilter.cat(1);
		event.preventDefault();
		return false;
	});
	
	//$('#filter-cat-list-ul').hide();
	
	catFilter.catArr = $('#filter-cat-list-ul').children()
	catFilter.catArrMob = $('#filter-cat-list-ul-mob').children()
	
	
}



catFilter.catDropShow = function(isShow,mode){
	if(isShow){
		if(mode==1){
			$('#filter-cat-list-span-mob').show();
		}else{
			$('#filter-cat-list-span').show();
		}
		
	}else{
		if(mode==1){
			$('#filter-cat-list-span-mob').hide();
		}else{
			$('#filter-cat-list-span').hide();
		}
		
		
	}
};

catFilter.cat = function(mode){
	
	var _v = '';
	var arr = null;
	if(mode==1){
		var _v = $('#filter-cat-list-text-mob').val();
		arr = $('#filter-cat-list-ul-mob').html();
	}else{
		var _v = $('#filter-cat-list-text').val();
		arr = $('#filter-cat-list-ul').html();
	}
	catFilter.catDropShow(true,mode);
	
	if(_v.length>0){
		
	
		var s = '';
		
		
		for(var i = 0; i<arr.length; i++){
			var _valHtml = arr[i];
			var _val = $(_valHtml).html();
			try{
				if(_val.toLowerCase().search(_v.toLowerCase())>=0){
			    	$(_valHtml).removeClass('list-hide');
			    }else{
			    	$(_valHtml).removeClass('list-hide');
			    	$(_valHtml).addClass('list-hide');
			    }
			}catch (e) {
				// TODO: handle exception
			}
		    
		}
		
		
		
	
	}else{
		
		for(var i = 0; i<arr.length; i++){
			var _valHtml = arr[i];
			try{
				$(_valHtml).removeClass('list-hide');
			}catch (e) {
				// TODO: handle exception
			}
			
			
		}
		
		
	}
}
