
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Profilo</title>
	<!-- Bootstrap Core CSS -->
	<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS: You can use this stylesheet to override any Bootstrap styles and/or apply your own styles --> 
	<link href="css/custom.css" rel="stylesheet">

	<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/v4-shims.css">
  	
	<link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Pacifico&display=swap" rel="stylesheet"> 

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body class="cyan-shell-gradient-background it">
	<% String username = request.getParameter("username");%> 
	
	<div style="position:fixed; top:4%; left:14%; border: 1px solid transparent; color: #ff9d7d;">
		<div class="hexagon hex-left" style="left: 0%;">
			<div style="position:absolute; z-index: 2;font-family: 'Pacifico'; left:38%; top: 15%;">F</div>
		</div>
		<div class="hexagon" style="left: 0%; margin-top:-15px; margin-left:60px;">
			<div style="position:absolute; z-index: 2;font-family: 'Pacifico'; left:38%; top: 15%;"><span class="fas fa-search"></span></div>
		</div>
		<div class="hexagon hex-left" style="left: 0%; margin-top:-15px;">
			<div style="position:absolute; z-index: 2;font-family: 'Pacifico'; left:38%; top: 15%;"><span class="fas fa-user"></span></div> <!-- glyphicon glyphicon-user -->
		</div>
		<div class="hexagon" style="left: 0%; margin-top:-15px; margin-left:60px;">
			<div style="position:absolute; z-index: 2;font-family: 'Pacifico'; left:38%; top: 15%;"><span class="fas fa-cog"></span></div>
		</div>
		<div class="hexagon hex-left" style="left: 0%; margin-top:-15px;">
			<div style="position:absolute; z-index: 2;font-family: 'Pacifico'; left:38%; top: 15%;"><span class="fas fa-sign-out-alt"></span></div>
		</div>
	</div>

	<div class="top" style="margin: 40px 0 20px 0; width:35%; border: 1px solid transparent;"> 
		<div class="container my-container" style="width:100%; padding: 0;">
			<div style="width:40%; padding: 20px;">
				<p id="username-label" style="font-family: 'Pacifico';">${username}</p>
			</div>
			<div style="display:inline-block; width:100%; height:40px; margin-bottom: -5px;">
				<button id="btnmie" onclick="mostraMie();" class="my-slidingbutton" style="float:left; width:50%; height:100%; margin: 0; font-family:'Pacifico'; background-color:#ffe1d8;">Le mie Ricette</button>
				<button id="btnpref" onclick="mostraPreferiti();" class="my-slidingbutton" style="float:right; width:50%; height:100%; margin: 0; font-family:'Pacifico'">Preferiti</button>
			</div>
			<div style="padding-bottom:6px; position: relative; background-color: #ffe1d8;">
				<div id="slider" style="border: 3px solid #ff9d7d; border-radius: 5px; width:50%; position:absolute;"></div>
			</div>
		</div>
	</div>
	
	
	<div id="mie" class="my-animated-div top" style=" width:35%; top: 180px; border:solid 1px transparent;">
		
	</div>
	
	<div id="preferite" class="my-animated-div top" style="display:none; width:35%; top: 180px; border:solid 1px transparent;">
		
	</div>

	<script type="text/javascript">
	
		// old blue color: "rgba( 71, 190, 214, 0.3)";
		// water green : rgba( 125, 255, 147, 1);  HEX: #7dff93; 
		// melon green : rgba( 160, 255, 125, 1);  HEX: #a0ff7d;
		// violet 	   : rgba( 125, 138, 255, 1);  HEX: #7d8aff;
		// cyan		   : rgba( 120, 255, 237, 1);  HEX: #78ffed;
		// limpet shell: rgba( 160, 224, 224, 1);  HEX: #a0e0e0;
		// coral 	 : rgba( 255, 157, 125, 0.3);  HEX: #ff9d7d; //hover: #ffe1d8;
	
		// <i class="fas fa-heart"></i>
		// <i class="far fa-heart"></i>
		
		// <i class="fas fa-bookmark"></i>
		// <i class="far fa-bookmark"></i>
		
		// <i class="fas fa-star"></i> 
		// <i class="far fa-star"></i>

		document.getElementById("mie").addEventListener("webkitAnimationEnd", function(){
			if( document.getElementById("mie").style.animationName == "leftout" ){
				document.getElementById("mie").style.display = "none"; 
				//alert("end of mie animation");
			}
		});

		document.getElementById("preferite").addEventListener("webkitAnimationEnd", function(){
			if( document.getElementById("preferite").style.animationName == "rightout" ){
				document.getElementById("preferite").style.display = "none"; 
				//alert("end of pref animation");
			}
		});
	
		var seldiv = 'mie';
		function mostraMie(){
			var mie  = document.getElementById("mie");
			var pref = document.getElementById("preferite");

			document.getElementById("btnpref").style.backgroundColor = "";

			mie.style.display = "block";

			mie.style.animation = "leftin 0.7s";
			pref.style.animation = "rightout 0.3s";

			$("#slider").animate({left: '0%'}, 200);

			document.getElementById("btnmie").style.backgroundColor = "#ffe1d8";
			
			seldiv = 'mie';

		}

		function mostraPreferiti(){
			var mie  = document.getElementById("mie");
			var pref = document.getElementById("preferite");

			document.getElementById("btnmie").style.backgroundColor = "";

			pref.style.display = "block";

			mie.style.animation = "leftout 0.3s";
			pref.style.animation = "rightin 0.7s";

			
			$("#slider").animate({left: '50%'}, 200);

			document.getElementById("btnpref").style.backgroundColor = "#ffe1d8";
			
			seldiv = 'preferite';
			
			if(document.getElementsByClassName('preferite').length == 0)
				getRicette();

		}
	
		function aggiungiPost(n){
			
			/* 
				<form>
					<div newChild>
						<div divChild0>
							<p pChild> </p>
							<p pChild0> </p>
							<p pChild1> </p>
						</div>
						<div divChild1>
							<img imgChild />
							<div favChild></div>
						</div>
						<div bubble></div>
						<div rChild></div>
					</div>
					<input type="hidden" value="id_ricetta"/>
				</form>
			*/
			
			for(var i=0; i<n; i++){
				var form = document.createElement("form");
				form.className = "feedForms " + seldiv;
				form.action = "DammiRicettaCompleta";
				form.method = "POST";
				form.style.width = "100%";
				form.style.marginTop = "10px";
				form.style.marginBottom = "10px";
				
				var hiddenInput = document.createElement("input");
				hiddenInput.type = "hidden";
				hiddenInput.name = "id_ricetta";
				
				var newChild = document.createElement("div");
				newChild.className = "container my-container feedDivs";
				newChild.style.position = "relative";
				newChild.style.width = "100%";
				newChild.style.padding = "0 0 0 0";
				newChild.style.clear = "both";
				
				var divChild0 = document.createElement("div");
				divChild0.style.position = "relative";
				divChild0.style.width = "50%";
				divChild0.style.margin= "0 0 0 0";
				divChild0.style.height = "100%";
				divChild0.style.display = "inline-block";
				
				var divChild1 = divChild0.cloneNode(true);
				divChild1.style.cssFloat = "right";
				divChild1.style.overflow = "hidden";
				
				divChild0.style.padding = "10px 20px 10px 20px";
				
				var pChild = document.createElement('p');
				pChild.style.fontFamily = "Pacifico";
				pChild.style.wordWrap = "break-word";
				pChild.style.fontSize = "20px";
				
				var pChild1 = pChild.cloneNode(true);
				var pChild2 = pChild.cloneNode(true);
				
				var imgChild = document.createElement("img");				
				imgChild.style.height = "200px";
				imgChild.style.width = "300px";
				imgChild.style.cssFloat = "right";
				imgChild.style.borderRadius = "0 5px 5px 0";
				imgChild.style.backgroundColor = "black";
				
				var favChild = document.createElement("div");
				favChild.className = "my-bookmark-button";
				favChild.style.borderTopColor = "white";

				favChild.addEventListener('click', function(){
					if(this.style.borderTopColor == "white"){
						var t = this;
						var jsonObj = '{"action":"add", "account_id":"'+ document.getElementById("username-label").innerHTML +'", "ricetta_id":"'+ t.parentNode.parentNode.parentNode.childNodes[1].value +'"}';
						$.ajax({
							type: "POST",
							url: "AggiungiRimuoviPreferiti",
							datatype: "json",
							data: jsonObj,
							success: function (){
								t.parentNode.parentNode.childNodes[2].style.display = "block";
								t.parentNode.parentNode.childNodes[2].style.animation = "pop 0.7s";
								
								t.style.borderColor = "#ff9d7d";
								t.style.borderBottomColor = "transparent";
							}
						});
					}else{
						var t = this;
						var jsonObj = '{"action":"remove", "account_id":"'+ document.getElementById("username-label").innerHTML +'", "ricetta_id":"'+ t.parentNode.parentNode.parentNode.childNodes[1].value +'"}';
						$.ajax({
							type: "POST",
							url: "AggiungiRimuoviPreferiti",
							datatype: "json",
							data: jsonObj,
							success: function (){
								t.style.borderColor = "white";
								t.style.borderBottomColor = "transparent";
							}
						});	
					}
				});

				var bubble = document.createElement("div");
				bubble.className = "my-bookmark-bubble";

				bubble.addEventListener('webkitAnimationEnd', function(){
					this.style.display = "none";
					this.style.animation = "";
				});				
				
				var rChild = document.createElement("div");
				rChild.className = "my-expand-button";
				rChild.style.fontFamily = "Pacifico";
				rChild.onmouseleave = function(){this.childNodes[1].style.display = 'none'; this.childNodes[1].style.animation = '';};
				rChild.innerHTML = "<span class='fas fa-arrow-circle-right' style='position:absolute; top:4px; left:4px; font-size:30px;'></span>"
								   + "<p style='position:absolute; right:20px; top: 6px; font-size:16px; font-family: Pacifico; display: none;'>mostra ricetta</p>";

				rChild.addEventListener('webkitAnimationEnd', function(){
					this.childNodes[1].style.display = 'inline-block'
					this.childNodes[1].style.animation = 'opacitybutton 0.5s';
				});

				rChild.addEventListener('click', function(){
					this.parentNode.parentNode.submit();
				});
				
				// sezione d'assemblaggio
				divChild0.append(pChild);
				divChild0.append(pChild1);
				divChild0.append(pChild2);
				
				divChild1.append(imgChild);
				divChild1.append(favChild);
				
				newChild.append(divChild0);
				newChild.append(divChild1);
				newChild.append(bubble);
				newChild.append(rChild);
				
				form.append(newChild);
				form.append(hiddenInput);	
				
				var div = document.getElementById(seldiv);
				
				div.appendChild(form);
		
			}
		}
		
		function getRicette(){	
			
			var jsonObj
			
		    if(seldiv == "mie"){
		    	var id;
				if(document.getElementsByClassName(seldiv).length < 1){
					id = -1;
				}else{
					id = document.getElementsByClassName(seldiv)[(document.getElementsByClassName(seldiv).length)-1].id;
				}
				jsonObj = '{"id":' + id + '}';
			}else{
				jsonObj = '{"account_id":"' + document.getElementById("username-label").innerHTML + '", "offset":' + document.getElementsByClassName(seldiv).length + '}';
			}
				
			$.ajax({
				type: "POST",
				url : (seldiv == "mie")? 'DammiRicetta':'DammiRicettePreferite',
				datatype: "json",
				data: jsonObj,
				success : function(responseText) {
					
					var ricette = JSON.parse(responseText);
					
					if(ricette.ricetta.length > 0){
						
						aggiungiPost(ricette.ricetta.length);
						
						var forms = document.getElementsByClassName(seldiv);	
						
						var i = 0;
						var j = 0;
						if(id > -1) i = ((forms.length)-ricette.ricetta.length);
						
						for(; i < forms.length; i++, j++){
							
							forms[i].id = ricette.ricetta[j].id;
							forms[i].childNodes[1].value = ricette.ricetta[j].id;
							
							forms[i].childNodes[0].childNodes[0].childNodes[0].innerHTML = ricette.ricetta[j].titolo;
							forms[i].childNodes[0].childNodes[0].childNodes[1].innerHTML = "Difficoltà: " + ricette.ricetta[j].difficolta;
							forms[i].childNodes[0].childNodes[0].childNodes[2].innerHTML = "Tempo: " + ricette.ricetta[j].tempo;
							
							if(ricette.ricetta[j].imageurl != null)
								forms[i].childNodes[0].childNodes[1].childNodes[0].src = ricette.ricetta[j].imageurl;
							
							if(ricette.ricetta[j].preferito == 1){
								forms[i].childNodes[0].childNodes[1].childNodes[1].style.borderColor = "#ff9d7d";
								forms[i].childNodes[0].childNodes[1].childNodes[1].style.borderBottomColor = "transparent";
							}
						}
					}
				}
			});
		}
		
		// all'avvio e durante lo scorrimento
		$(document).ready(getRicette());
		
		$(window).scroll(function() {   
			   if($(window).scrollTop() + $(window).height() == $(document).height()) {
				   getRicette();
			   }
		});
	
	</script>
</body>
</html>
