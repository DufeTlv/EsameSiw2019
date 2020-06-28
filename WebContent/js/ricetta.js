$(document).ready(
		getCommenti(),
		//getValNutr(),
		//popolaSelectIngredienti(),
		//popolaForm(),
		editButton()//,
		//translate()
		
);

function aggiungiPost(n){
			
	/*
		<div newChild>
			<p pChild> </p>
			<p pChild1> </p>
		</div>
	*/
	for(var i=0; i<n; i++){
		
		var newChild = document.createElement("div");
		newChild.className = "container my-container comments";
		newChild.style.position = "relative";
		newChild.style.width = "40%";
		newChild.style.padding = "10px 20px 10px 20px";
		newChild.style.marginTop = "10px"; 
		
		var pChild = document.createElement('p');
		pChild.style.fontFamily = "Pacifico";
		pChild.style.wordWrap = "break-word";
		pChild.style.fontSize = "15px";
		
		var pChild1 = pChild.cloneNode(true);
		pChild1.style.fontSize = "18px";
		
		pChild.innerHTML = "Nome Utente";
		pChild1.innerHTML = "Commento assolutamente generico";
			
		newChild.append(pChild);
		newChild.append(pChild1);

		document.getElementById("commenti").appendChild(newChild); 

	}
}

function getCommenti(){
	
	$.ajax({
		type: "POST",
		url : 'DammiCommento',
		datatype: "json",
		success : function(responseText) {
			
			var commenti = JSON.parse(responseText);
			
			if(commenti.commento.length > 0){
				aggiungiPost(commenti.commento.length);
				
				var comm = document.getElementsByClassName("comments");	
					
				for(var i = 0; i < comm.length; i++){
					comm[i].childNodes[0].innerHTML = commenti.commento[i].username;
					comm[i].childNodes[1].innerHTML = commenti.commento[i].descrizione;
					
					if(commenti.commento[i].gif_url != ''){
						var x = $(document.createElement("img"));
				        
						x.attr({
							"class": "gif",
							"style" : "margin-top:10px; border-radius:5px;",
						    "src": commenti.commento[i].gif_url,
						    "data-animate": commenti.commento[i].gif_url,
						    "data-still": commenti.commento[i].gif_url_still,
						    "data-state": "animate"					          
				        });
						
						x.insertAfter(comm[i].childNodes[1]);
					}
				}
			}
		}
	});
}


function aggiungiCommento(){
	var gifUrl = '', gifUrlStill = '';
	
	if(document.getElementById("editorGIF") != null){
		gifUrl = document.getElementById("editorGIF").src;
		gifUrlStill = $(document.getElementById("editorGIF")).attr("data-still");
	}
	
	var jsonObj = '{"descrizione":"' + document.getElementById("commDiv").innerHTML + '", "gif_url":"' + gifUrl + '", "gif_url_still":"' + gifUrlStill + '"}';
	
	$.ajax({
		type: "POST",
		url : 'AggiungiCommento',
		datatype: "json",
		data: jsonObj,
		success : function(responseText) {
			alert("commento caricato con successo");
			document.getElementsByClassName("editor")[0].remove();
			document.getElementById("commDiv").innerHTML = '';
		}
	});
}

function editButton(){
	
	var rChild = document.createElement("div");
	rChild.id = "modRic";
	rChild.className = "my-expand-button";
	rChild.style.fontFamily = "Pacifico";
	rChild.style.position = "absolute";
	rChild.style.top = "6.5%";
	rChild.style.right = "32.5%";
	rChild.onmouseleave = function(){this.childNodes[1].style.display = 'none'; this.childNodes[1].style.animation = '';};
	rChild.innerHTML = "<span class='fas fa-pencil-alt' style='position:absolute; top:7px; left:8px; font-size:22px;'></span>"
					   + "<p style='position:absolute; right:20px; top: 6px; font-size:16px; font-family: Pacifico; display: none;'>modifica ricetta</p>";

	rChild.addEventListener('webkitAnimationEnd', function(){
		this.childNodes[1].style.display = 'inline-block'
		this.childNodes[1].style.animation = 'opacitybutton 0.5s';
	});
	
	rChild.addEventListener('click', function(){
		showForm();
	});
	
	document.getElementById("titoloR").after(rChild);
	
}

function getValNutr(){
	var lis = document.getElementsByClassName("edamamAPI");
	
	var energia = 0; grassi = 0; carb = 0; prot = 0; zucch = 0; fibre = 0;
	
	var cnt = lis.length;
	for(var i = 0; i < lis.length; i++){
		
		var quantita = lis[i].childNodes[1].innerHTML;
		var mURI = lis[i].childNodes[3].innerHTML;
		var id = lis[i].childNodes[5].innerHTML;
		
		var jsonObj = '{"ingredients" : [{ "quantity": ' + quantita + ', "measureURI": "' + mURI + '", "foodId": "' + id + '"}]}';
		
		$.ajax({
			url: "https://api.edamam.com/api/food-database/nutrients?app_id=5ca850b5&app_key=a7d18ae14b9a5aaab99710d65aabcd5b",
			method: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: jsonObj,
			success:function(resp)
			{						
				energia += resp.totalNutrients.ENERC_KCAL.quantity;
				grassi += resp.totalNutrients.FAT.quantity;
				carb += resp.totalNutrients.CHOCDF.quantity;
				prot += resp.totalNutrients.PROCNT.quantity;
				zucch += resp.totalNutrients.SUGAR.quantity;
				fibre += resp.totalNutrients.FIBTG.quantity;
				
				cnt--;
				if(cnt == 0)
					document.getElementById("valnut").innerHTML += "<div style='display:inline-block; width:50%; border-right: 1px dotted black;'><ul><li>energia: "+energia.toFixed()+" kcal</li><li> grassi: "+ grassi.toFixed(1)+" g</li><li> carb: "+carb.toFixed(1)
																  +" g</div><div style='display:inline-block; width:50%; padding-left: 40px; float:right;'></li><li> prot: "+prot.toFixed(1)+" g</li><li> zucch: "+zucch.toFixed(1)+" g</li><li> fibre: "+fibre.toFixed(1)+" g</li></ul></div>";
				
			},
		    error: function(XMLHttpRequest, textStatus, errorThrown) { 
		        alert("Status: " + textStatus); alert("Error: " + errorThrown); 
		    } 
	 	});
		
	}
	
}

function searchDivController(){
	
	var node = document.getElementById("gifSearchDiv");

	if(node.style.animationName != "expand-div"){
		node.style.width = "460px";
		node.style.animation = "expand-div 0.5s";
		node.childNodes[1].innerHTML = '<i class="fas fa-times"></i>';
	}else{
		node.childNodes[3].style.animation = '';
		node.childNodes[3].style.display = 'none'
		node.childNodes[5].style.animation = '';
		node.childNodes[5].style.display = 'none'
		
		node.style.animation = "";
		node.style.width = "35px";
		node.childNodes[1].innerHTML = '<span class="gif-icon"></span>';
		
		document.getElementById("results").remove();
		document.getElementById('ricercaGIF').value = '';
	}
	
	node.addEventListener("webkitAnimationEnd", function(){
		if(node.style.animationName == "expand-div"){
			node.childNodes[3].style.display = 'block'
			node.childNodes[3].style.animation = 'opacitybutton 0.2s';
			node.childNodes[5].style.display = 'block'
			node.childNodes[5].style.animation = 'opacitybutton 0.2s';
		}
	});
	
}

function createGifDiv(){
	
	if(document.getElementById("results") == null){
		
		var divChild = document.createElement("div");
		divChild.id = "results";
		divChild.className = "container my-container";
		divChild.style.backgroundColor = "#ffe1d8";
		divChild.style.width = "100%";
		divChild.style.padding= "20px";
		divChild.style.maxHeight = "300px";
		divChild.style.overflowY = "scroll";
		
		document.getElementById("commenti").childNodes[1].appendChild(divChild);
		
	}
	
}

function clearGifDiv(){
	
	while(document.getElementsByClassName("gif-box").length>0){
		document.getElementsByClassName("gif-box")[document.getElementsByClassName("gif-box").length-1].remove();
	}
}

function giphy(){
	createGifDiv();
	
	var queryURL = "https://api.giphy.com/v1/gifs/search?";
	var query = $('#ricercaGIF').val();
	var params = {
		    q: query,
		    limit: 6,
		    api_key: "aFFKTuSMjd6j0wwjpFCPXZipQbcnw3vB",
		    fmt: "json"
		  };
	
	$.ajax({
	    url: queryURL + $.param(params),
	    method: "GET",
	    success: function(r) {
	      for (i = 0; i < params.limit; i++) {
	        var $img = $("<img>");
	        var $div = $("<div>");
	        var gif = r.data[i].images;
	
	        // Image builder object
	        $img.attr({
	          "src": gif.fixed_height_still.url,
	          "class": "gif",
	          "data-animate": gif.fixed_height.url,
	          "data-still": gif.fixed_height_still.url,
	          "data-state": "still"			          
	        });
	        $div.addClass("gif-box");
	        $div.append($img);
	        $("#results").append($div);
	      }
	
	      $(".gif").on("click", function() {
	    	  searchDivController();
	    	  
	    	  if(document.getElementById("editorGIF") == null){
	    		  var x = $(document.createElement("img"));
	    		  var y = $(document.createElement("button"));
	    		  var z = $(document.createElement("div"));
	    		  
		          x.attr({
			          "id" : "editorGIF",
			          "style" : " border-radius:5px;",
			          "src": $(this).attr("data-animate"),
			          "data-still": $(this).attr("data-still")
			        });
		          y.attr({
		        	  "class" : "my-button",
		        	  "style" : "width:30px; height:30px; position: relative; left: 90%; border-radius: 20px;"
		          	});
		          y.html('<i class="fas fa-times"></i>');
		          y.on("click", function(){
					 this.parentNode.remove();
				  });
		          
		          z.addClass("gif-box editor");
		          z.append(y);
		          z.append(x);
		          z.insertAfter("#commDiv");
		          
	    	  }else{
	    		  document.getElementById("editorGIF").src = $(this).attr("data-animate");
	    	  }
	      });
	    }
	});
}