<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Search</title>
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
  	
  	<script type="text/javascript" src="js/gestioneFeed.js" ></script>
  	
</head>
<body class="cyan-shell-gradient-background it">

	<!-- side nav menu -->
	<div style="position:fixed; top:4%; left:10%; border: 1px solid transparent; color: #ff9d7d;">
		<div class="hexagon hex-left" style="left: 0%;"> <!-- "/myServlets/DBselTankList.java" -->
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;">F</div>
		</div>
		<div class="hexagon" style="left: 0%; margin-top:-15px; margin-left:60px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-search"></span></div>
		</div>
		<div onclick="this.childNodes[3].submit();" class="hexagon hex-left" style="left: 0%; margin-top:-15px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-user"></span></div>
			<form method="POST" action="DammiProfilo" style="display:none;"><input type="hidden" name="username" value='${username}'></form>
		</div>
		<div class="hexagon" style="left: 0%; margin-top:-15px; margin-left:60px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-cog"></span></div>
		</div>
		<div class="hexagon hex-left" style="left: 0%; margin-top:-15px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-sign-out-alt"></span></div>
		</div>
	</div>
	
	<div id="feed" class="top" style=" width:35%; border:solid 1px transparent;"> <!-- border-color -->
		<div class="container my-container" style="width:100%; margin: 20px 0 20px 0;">
			<div style="width:40%; padding: 20px;">
				<p id="username-label" style="font-family: 'Pacifico';">${username}</p>
			</div>
			<div style=" width:100%; display:grid; grid-template-columns: 93% auto; clear:both;">
				<input id="ricerca" class="my-input" type="text" maxlength="auto" placeholder="cosa stai cercando?" style="width:100%; height:100%; border: 1px solid gray; border-radius: 20px; color: #333333;" required >
				<button onclick="search()" class="my-slidingbutton" style="float:right; margin-left:5px; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;"><span class="fas fa-search"></span></button>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(document.getElementById("ricerca").value = "");
		
		function clearFeed(){
			if(document.getElementsByClassName("feedForms").length > 0){
				var fms = document.getElementsByClassName("feedForms");
				while(fms.length > 0)
					fms[0].remove();
			}			
		}
	
		function search(){
			
			clearFeed();
			
			var jsonObj = '{ "search": "' + document.getElementById("ricerca").value + '"}';
						
			$.ajax({
				type: "POST",
				url: "DammiRicettaRicerca",
				datatype: "json",
				data: jsonObj,
				success: function (resp){
					//alert(JSON.stringify(resp));
					var ricette = JSON.parse(resp);
					
					if(ricette.ricetta.length > 0){
						aggiungiPost(ricette.ricetta.length);
						
						var forms = document.getElementsByClassName("feedForms");	
						
						var i = 0;
						
						for(; i < forms.length; i++){
							forms[i].id = ricette.ricetta[i].id;
							forms[i].childNodes[1].value = ricette.ricetta[i].id;
							
							forms[i].childNodes[0].childNodes[0].childNodes[0].innerHTML = ricette.ricetta[i].titolo;
							forms[i].childNodes[0].childNodes[0].childNodes[1].innerHTML = "<span style='color:#ff9d7d;'>Difficoltà: </span>" + ricette.ricetta[i].difficolta;
							forms[i].childNodes[0].childNodes[0].childNodes[2].innerHTML = "<span style='color:#ff9d7d;'>Tempo: </span>" + ricette.ricetta[i].tempo;
							
							if(ricette.ricetta[i].imageurl != null)
								forms[i].childNodes[0].childNodes[1].childNodes[0].src = ricette.ricetta[i].imageurl;
							
							if(ricette.ricetta[i].preferito == 1){
								forms[i].childNodes[0].childNodes[1].childNodes[1].style.borderColor = "#ff9d7d";
								forms[i].childNodes[0].childNodes[1].childNodes[1].style.borderBottomColor = "transparent";
							}
						}
					}else{
						alert("la ricerca non ha prodotto risultati");
					}
				}
			});
		}
	</script>

</body>
</html>