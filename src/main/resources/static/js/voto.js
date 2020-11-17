

$(document).ready(function() {

	
	$("button[id^='voto']").click(function(event) {

		//stop submit the form, we will post it manually.
		//event.preventDefault();

		var request = $.ajax({
			url : $(this).attr('formaction') ,
			dataType : "json",
			method : "GET",
			data : {}

		});
		request.done(function (response) {
			//console.log( "done="+response);


			$("#positiveAmount_"+response.id).html(response.positiveAmount)
			$("#negativeAmount_"+response.id).html(response.negativeAmount)
			$("#interestingAmount_"+response.id).html(response.interestingAmount)
		});


		request.fail(function (msg) {
			console.log("fail="+msg);
		});

	});

	$("button[id^='Opvoto']").click(function(event) {

		//stop submit the form, we will post it manually.
		event.preventDefault();

		var request = $.ajax({
			url : $(this).attr('formaction') ,
			dataType : "json",
			method : "GET",
			data : {}

		});
		request.done(function (response) {
			console.log( "done="+response);

			if (response.sonExcluyentes)
				for (i in response.choices) {
					$("#amount_"+response.choices[i].id).html(response.choices[i].amount)
				} else {
					$("#amount_"+response.id).html(response.amount)	
				}
		});


		request.fail(function (msg) {
			console.log( "fail="+msg);
		});

	});
	
		  
	  $("#form_origin_post").submit(function(event) {

		    //stop submit the form, we will post it manually.
		    event.preventDefault();
		  console.log( "formaction de origin post. id_themes es ="+ $("#id_themes").val() );
		    
		    var formData = {
		    		text   : $("#textArea").val(),
		    		themes : $("#id_themes").val() 
			}
			
		    var header = "X-CSRF-TOKEN";
		    var token = $("input[name='_csrf']").val();
		    
		    
		    
			var request = $.ajax({
				url : $("#b_origin_post").attr('formaction') ,
				dataType : "json",
				method : "POST",
				contentType : "application/json",
				headers: {
		                "Content-Type": "application/json"//,
		                //"Accept": "application/json"
		        },
				//data   : formData,
				data: JSON.stringify(formData),
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
			    success : function(result) {
			    	console.log("id es ="+result[0].id+":json.lenght="+result.length);
			    	var listado = ""
			    	for (i=0;i<result.length;i++) {
			    		// leer plantilla
			    		// escribir valores a variables de la plantilla
			    		// agregar a listado
//			    		var linea = $("#plantillaItemsFactura").html();
//						
//						linea = linea.replace(/{ID}/g, ui.item.value);
//						linea = linea.replace(/{NOMBRE}/g, ui.item.label);
//						linea = linea.replace(/{PRECIO}/g, ui.item.precio);
//						
//						$("#cargarItemProductos tbody").append(linea);
			    		
			    	}
			    	//asignar a div el valor de listado
			    	
				//console.log(result);
			   },
			   error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			   }
			});
	  });		
			
	  $("#form_respuesta").submit(function(event) {

		  //stop submit the form, we will post it manually.
		  event.preventDefault();
		  console.log( "formaction de respuesta, opinionId: "+ $("#opinionId").val());

		  var formData = {
				  opinionId      : $("#opinionId").val(),
				  originAnswerId : $("#originAnswerId").val(),
				  levelId        : $("#levelId").val(),
				  texto          : $("#text1").val()
		  }

		  var header = "X-CSRF-TOKEN";
		  var token = $("input[name='_csrf']").val();


		  var request = $.ajax({
			  url : $("#b_respuesta").attr('formaction') ,
			  dataType : "json",
			  method : "POST",
			  data   : formData,
			  beforeSend: function(xhr){
				  xhr.setRequestHeader(header, token);
			  },
			  success : function(result) {


				  console.log(result);
			  },
			  error : function(e) {
				  alert("Error!")
				  console.log("ERROR: ", e);
			  }
		  });

	  });	
	

	  Voto.createEvent();
	  
		
		  /**	  
		  $("#form_respuesta").submit(function(event) {

			    //stop submit the form, we will post it manually.
			    event.preventDefault();
			 // console.log( "formaction de respuesta ="+ $(this).attr('formaction'));
			    
			    var formData = {
			    		opinionId : $("#opinionId").val(),
						originAnswerId : $("#originAnswerId").val(),
						levelId : $("#levelId").val(),
						texto :   $("#text1").val()
				}
				
			    var header = "X-CSRF-TOKEN";
			    var token = $("input[name='_csrf']").val();
			    
			    
				var request = $.ajax({
					url : $("#b_respuesta").attr('formaction') ,
					dataType : "json",
					method : "POST",
					data   : formData,
	                beforeSend: function(xhr){
	                    xhr.setRequestHeader(header, token);
	                },
				    success : function(result) {
				    	
				    
					console.log(result);
				   },
				   error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				   }
				});
				
				
			    
		 });
         */		  
});

var Voto = function(){};

Voto.createEvent = function(){
	
	$("a[id^='Opvoto']").click(function(event) {

		event.preventDefault();
		var reff = this;
		
		var classes = $(reff).attr('class');
		
		
		if(classes.indexOf('social')>0 ){
			//$(reff).attr('css', 'background-image');
			//$(reff).css('background-image','');
			
			var sp = $(reff).parent();
			
			var spch = $(sp).children();
			
			for(var i=0;i<spch.length;i++){
				var c = $(spch[i]).attr('class');
				if(c.indexOf('social')>0 ){
					$(spch[i]).removeClass('active');
				}
			}
			
			//$('.social').removeClass('active')
			
			if(classes.indexOf('Opvoto_i')>0 ){
				if(classes.indexOf('active')<0){
					animateScript(reff);
				}else{
					$(reff).removeClass('active');
				}
				
			}else{
				if(classes.indexOf('active')>0){
			  		$(reff).removeClass('active');
			  	  }else{
			  		$(reff).addClass('active');  
			  	  }
			}
			
		}else{
			if(classes.indexOf('active')>0){
		  		$(reff).removeClass('active');
		  	  }else{
		  		$(reff).addClass('active');  
		  	  }
		}
		/*if(classes.indexOf('Opvoto_i')>0 ){
			if(classes.indexOf('active')<0){
				animateScript(reff);
			}else{
				$(reff).css('background-image','url(/images/interesante.png)');
			}
			
		}else{
			if(c.indexOf('active')>0){
		  		$(reff).removeClass('active');
		  	  }else{
		  		$(reff).addClass('active');  
		  	  }
		}*/
		
		
		
		  //stop submit the form, we will post it manually.
		  

		  var request = $.ajax({
			  url : $(this).attr('formaction') ,
			  dataType : "json",
			  method : "GET",
			  data : {}

		  });
		  request.done(function (response) {
			  console.log( "done="+response);
			  
			  
			  
			  var c = $(reff).attr('class');
		  		
		  	  
			  if (response.sonExcluyentes){
				  //$('.social').removeClass('active')
				  
				  /*if(c.indexOf('active')>0){
				  		$(reff).removeClass('active');
				  	  }else{
				  		$(reff).addClass('active');  
				  	  }*/
				  
				  for (i in response.choices) {
					  $("#amount_"+response.choices[i].id).html(response.choices[i].amount)
				  }
				  
			  }else {
				  
				  /*if(c.indexOf('active')>0){
				  		$(reff).removeClass('active');
				  	  }else{
				  		$(reff).addClass('active');  
				  	  }*/
				  
					  $("#amount_"+response.id).html(response.amount)	
				  }
		  });

		  request.fail(function (msg) {
			  console.log( "fail="+msg);
		  });

	  });
	
}


function animateScript(reff) {

	
	//$(reff).css('background-image','url(/images/positive-loded.gif)');
	$(reff).addClass('animate');
	
	setTimeout(function(){
		//$(reff).css('background-image','url(/images/box.png)');
		$(reff).removeClass('animate');
		$(reff).addClass('active');
	},1200);
	
	



	}
