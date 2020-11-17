mypost = {}

$(document).ready(function(){
	mypost.createMyPostView();
	mypost.createRecentPostView();
});

mypost.createMyPostView = function(){
	$.ajax({
		url:'/opinion/mypostsAjax',
		success:function(response){
			try{
			var obj = JSON.parse(response);
			
			if(obj.status == 'SUCCESS'){
				
				s='';
				
				for(var i=0; i<5;i++){
					var d = obj.data[i];
					var text = d.text;
					
					if(text.length>25){
						text = text.substr(0,25)+'...';
					}
					s+='<li><a href="/debate/'+d.id+'" title="">'+text+'</a></li>';
					
				}
				
				$('#aside-mypost').html(s);
			}
			}catch (e) {
				// TODO: handle exception
			}	
			
		}
	});
}


mypost.createRecentPostView = function(){
	$.ajax({
		url:'/opinion/recentpostsAjax',
		success:function(response){
			try{
			var obj = JSON.parse(response);
			
			if(obj.status == 'SUCCESS'){
				
				s='';
				
				for(var i=0; i<5;i++){
					var d = obj.data.list[i];
					var text = d.text;
					
					if(text.length>25){
						text = text.substr(0,25)+'...';
					}
					s+='<li><a href="/debate/'+d.id+'" title="">'+text+'</a></li>';
					
				}
				
				$('#aside-recentpost').html(s);
				
				//recentpost
			}
			
			}catch (e) {
				// TODO: handle exception
			}
		}
	});
}