$(document).ready(function(){
	
	initialize_Location(function(){
		
		newpost.selectlocation();
		
	});
	
	$('.btn-load-more').on('click',function(){
		console.info('clicked');
		loadMoreClick();
	});
	
	$('.filter-switch').change(function(){
		addPrefered();
	});
	
	$('#filter-cat-list').on('change', function() {
		var tj = JSON.parse($(this).val());
		addThemeFilter(tj);
		var x = $('#filter_cat_list_chosen').children('.chosen-single')[0]
		var y = $(x).children()[0];
		$(y).html('')
		
	});
	
	try{
		filterArr.forEach(function(f){
			if(f==1){
				$('#onPositive').prop('checked', true);
			}
			if(f==2){
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
		
		console.info(page);
		makeRecentPostUI();
		
		
		
		
		
		
		
		
	}catch (e) {
		// TODO: handle exception
	}
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
		var thul = $('#filter-cat')
		var s = '';
		if(themeFilters!=null && themeFilters!=''){
		
		var thf= JSON.parse(themeFilters);
		
		
		thf.forEach(function(t){
			s+='<li><a href="javascript:;" title="" class="theam-filters" data-id="'+t.id+'" data-name="'+t.name+'" >'+t.name+'<span class="close-span">x</span></a></li>';
		});
		
		
		
		}
		
		thul.html(s);
		
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
		
	 NProgress.start();
	 var np= this
	var u='/opinion/recentpostsAjax?pageNumber='+pageNumber+"&";
	
	if(page == "debate"){
		u = '/debate/ajax/'+opId;
	}else{
		var p = getSearchParameters();
		if(p==''){
			
			try{
				var tf = $('.theam-filters');
				
				tf.each(function(t,e){
					console.info($(e).data('name'));
					u+='theams='+$(e).data('name')+'&';
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
			
			var div = $('#loadMoreDiv');
			
			var s='';
			if(obj.status=="SUCCESS"){
				
				if(obj.data.length==0){
					pageNumber-=1;
					NProgress.done();
					return;
				}
				
				
				
				for(var i=0;i<obj.data.length;i++){
					var d = obj.data[i];
					s+='<div class="central-meta item" style="display: none;">';
					s+='	<div class="user-post">';
					s+='		<div class="friend-info">';
					s+='			<figure>';
					if(d.user.ciudadano!=null && d.user.ciudadano.fotoAvatarDebate != null){
						
						s+='				<img src="/users/uploads/'+d.user.ciudadano.fotoAvatarDebate+'" alt="">';
					}else{
						s+='				<img src="/images/resources/friend-avatar10.jpg" alt="">';
					}
					
					s+='			</figure>';
					s+='			<div class="post-meta">';
					s+='				<ins>';
					s+='					<a href="#" title="">'+d.user.name+'</a>';
					s+='					<div class="published-date">';
					s+='						<span>'+formateDate(d.creationDate)+'</span>';
					s+='					</div>';
					s+='				</ins>';
					s+='				<ins class="user-userid">';
					s+=						d.user.accountName;
					s+='				</ins>';
					s+='				<div class="description" style="word-wrap: break-word">';
					var postText = d.text.replace(/\n/g, "<br />");
					
					s+='					<p style="color:black">'+postText+'</p>';
					s+='				</div>';
					if(d.image!=null){
						s+='				<img src="/users/uploads/' + d.image+'"  alt="" style="width: 250px;">';
					}
					
					s+='			</div>';
					s+='			<div class="friend-name">';
					
					if(d.tags!=null){
						s+='				<div class="friend-locate"><img src="/images/resources/tag.png"  title="Add Categoty"  alt="" /><span>'+d.tags+'</span></div>';
					}
					
					
					
					if(d.themes!=null){
						let th = '';
						
						d.themes.forEach(function(idx,v){
							if(v<d.themes.length-1)
								th+=idx.name+',';
							else
								th+=idx.name;
							
						});
						
						s+='				<div class="friend-locate"><img src="/images/resources/category.png" title="Add Categoty" alt=""><span>'+th+'</span></div>';
					}
					
					if(d.location!=null){
						s+='				<div class="friend-locate"><img src="/images/resources/location.png" title="Add Categoty" alt=""><span>'+d.location+'</span></div>';
					}
					
						
					s+='				<div class="friend-locate"><span><a href="/debate/'+d.id+'" title="" class="see-debates" data-ripple="">See Debate</a></span></div>';
					s+='			</div>';
					//s+='			<div class="published-date">';
					//s+='				<span>'+d.creationDate.date.year+'-'+d.creationDate.date.month+'-'+d.creationDate.date.day+'T'+d.creationDate.time.hour+':'+d.creationDate.time.minute+':'+d.creationDate.time.second+'</span>';
					//s+='			</div>';
					s+='			<div class="post-plans">';
					
										
										d.evaluacionesDeOpinion.forEach(function(ev){
											
											let tp = ev.tipoEvaluacion.id;
											
											if(tp==4){
												s+='				<div id="postResultDiv" class="submit-btns aggrement">';
											}else{
												
												s+='				<div id="postResultDiv" class="submit-btns climet">';
											}
											
											
											
											
											
											ev.opinionChoices.forEach(function(oc){
												
												let isUserSame = chkOpc(oc,obj.currentUserId);
												
												
												
												if(isUserSame && tp==4){
													s+='					<a href="javascript:;" class="'+oc.choice.name+'-initial active Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
													s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
													s+='					</a>';	
												}else if(!isUserSame && tp==4){
													s+='					<a href="javascript:;" class="'+oc.choice.name+'-initial Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
													s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
													s+='					</a>';
												}else if(isUserSame && tp==1){
													s+='					<a href="javascript:;" class="'+oc.choice.name+'-initial active social Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
													s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
													s+='					</a>';
												}else{
													s+='					<a href="javascript:;" class="'+oc.choice.name+'-initial social Opvoto_'+oc.choice.codigoNemotecnico+'" id="Opvoto_'+oc.choice.codigoNemotecnico+'" formaction="/choice/op/'+oc.id+'" formmethod="get">';
													s+='						<span id="amount_'+oc.id+'">'+oc.amount+'</span>';
													s+='					</a>';
												}
												
												
											});
											s+='				</div>';
										});
					
					
					s+='			</div>';
					s+='		</div>';
					s+='		<div class="new_posr_toggle">';
					s+='			<nav class="navbar navbar-default" role="navigation">';
					s+='				<div class="navbar-header">';
					s+='					<a href="javascript:;" class="navbar-toggle x collapsed" data-toggle="collapse" data-target="#navbar-collapse-'+d.id+'" aria-expanded="false">';
					s+='						<i class="fa fa-chevron-down" aria-hidden="true"></i>';
					s+='					</a>';
					s+='				</div>';
					s+='				<div class="collapse navbar-collapse" id="navbar-collapse-'+d.id+'">';
					s+='					<ul class="nav navbar-nav navbar-right">';
					s+='						<li data-toggle="collapse" data-target="#navbar-collapse.in"><a href="javascript:copyUrl(\'/debate/'+d.id+'\',\'navbar-tooltip-'+d.id+'\');">Copy Link</a></li>';
					s+='						<li data-toggle="collapse" data-target="#navbar-collapse.in"><a href="#top">Edit</a></li>';
					s+='						<li data-toggle="collapse" data-target="#navbar-collapse.in"><a href="#top">Remove</a></li>';
					s+='					</ul>';
					s+='				</div><!-- .navbar-collapse -->';
					s+='				<span class="copied-content" id="navbar-tooltip-'+d.id+'" style="display: none">Copied</span>';
					s+='			</nav>';
					s+='		</div>';
					s+='	</div>';
					s+='</div>';
					
				}
			}
			
			if(pageNumber==0){
				div.html('');
			}
			
			div.append(s);
			
			NProgress.done();
			
			if ($.isFunction($.fn.loadMoreResults)) {
				
				let dpi = (10*(pageNumber+1))-3;
				$('.btn-load-more').remove();
				$('.loadMore').loadMoreResults({
					displayedItems: dpi,
					showItems: 3,
					button: {
					  'class': 'btn-load-more',
					  'text': 'Load More',
					  'id' : 'btn-load-more'
					}
				});	
			}
			
			Voto.createEvent();
			
			$('.btn-load-more').on('click',function(){
				console.info('clicked');
				loadMoreClick();
			});
		}
	  
	
	//NProgress.done();
	});
	
	/*
	*/
	
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
