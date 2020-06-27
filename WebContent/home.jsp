<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>HomeFeed</title>
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
	<% String username = (String) request.getSession().getAttribute("username"); %> <!-- out.print(username); -->
	
	<!-- side nav menu -->
	<div style="position:fixed; top:4%; left:14%; border: 1px solid transparent; color: #ff9d7d;">
		<div id="home" class="hexagon hex-left" style="left: 0%;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;">F</div>
			<form method="POST" action="DammiHome" style="display:none;"></form>
		</div>
		<div id="search" class="hexagon" style="left: 0%; margin-top:-15px; margin-left:60px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-search"></span></div>
			<form method="POST" action="DammiRicerca" style="display:none;"></form>
		</div>
		<div id="profile" class="hexagon hex-left" style="left: 0%; margin-top:-15px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-user"></span></div>
			<form method="POST" action="DammiProfilo" style="display:none;"></form>
		</div>
		<div id="settings" class="hexagon" style="left: 0%; margin-top:-15px; margin-left:60px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-cog"></span></div>
			<form method="POST" action="DammiImpostazioni" style="display:none;"></form>
		</div>
		<div id="logout" class="hexagon hex-left" style="left: 0%; margin-top:-15px;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><span class="fas fa-sign-out-alt"></span></div>
			<form method="GET" action="Logout" style="display:none;"></form>
		</div>
	</div>
	
	<div id="feed" class="top" style=" width:35%; border:solid 1px transparent;"> <!-- border-color -->
		<div class="container my-container" style="width:100%; margin: 40px 0 20px 0;">
			<div style="width:40%; padding: 20px;">
				<p id="username-label" style="font-family: 'Pacifico';">${username}</p>
			</div>
			<button id="aggRic" class="my-slidingbutton" style="float:right; width:50%; margin:0; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">Aggiungi Ricetta</button>
		</div>
	</div>

	<div style="position: absolute; top:10%; right:10%;">			
		<c:if test="${username != null}">
			<p>Sei loggato come </p><p id="username" >${username}</p>
			<a href="./index.html">Logout</a>
		</c:if>			

		<p>
			User-name: <% out.print(username); %><br>
		</p>
	</div>

	<!--brightness-->
	<div class="myInput" style="border: solid 0px transparent; position: fixed; top:0; left:0; background-color: rgba(0,0,0, 0.4); width: 100%; height: 100%; display: none;"> </div>

	<!--  -->
	<div class="my-container-div middle myInput" style="position: fixed; width: 40%; z-index: 1; display: none;"> 
		<div class="container my-container" style="width:100%; padding: 40px; padding-top: 20px; border-radius: 20px;">
			<!--#1-->
			<div style="width:112%; margin: -1% 0 2% -6%; border-bottom: 1px dotted gray;"> 
				<button onclick="hideForm()" class="my-button" style="width:30px; height:30px; margin: 10px 10px 10px 0; position: relative; right: 42%; border-radius: 20px; padding-top: 1px;"><i class="fas fa-times"></i></button>
			</div>
			<!--#2-->
			<div style="width:100%;"> 
				<p for="titolo" style="font-family: 'Pacifico'; font-size: 20px; color: #ff9d7d; margin: 0 0 0 0;">Titolo:</p>
				<input id="titolo" class="my-input" type="text" size="40" maxlength="auto" placeholder="titolo" required >
			</div>
			<!--#3-->
			<div style=" width:100%; height: 100%; display:grid; grid-template-columns: 30% 70%; clear:both; margin-bottom:10px;">
				<!--#3.1-->
				<div style="float: left; width: 100%; height: 298px; ">
					
					<div style=" width:100%; display:grid; grid-template-columns: 46% 35%; clear:both;">
						<div>
							<p for="tempopreparazione" style="font-family: 'Pacifico'; font-size: 20px; color: #ff9d7d; margin: 0 0 0 0;">Tempo:</p>
							<input id="tempopreparazione" class="my-input" type="text" size="10" maxlength="auto" placeholder="tempo di preparazione" required >
							
						</div>
						<div style="float: left; margin-left:10px;">
							<p for="difficolta" style="font-family: 'Pacifico'; font-size: 20px; color: #ff9d7d; margin: 0 0 1px 0;">Difficoltà:</p>
							<select id="difficolta" class="selectDiff" style="height:51%; color:#333333;">
									<option value="0">Facile</option>
									<option value="1">Media</option>
									<option value="2">Difficile</option>
							</select>
						</div>							
					</div>
					
					<p for="ingredienti" style="font-family: 'Pacifico'; font-size: 20px; color: #ff9d7d; margin: 0 0 0 0;">Ingredienti:</p>
					<!--#3.1.1-->
					<div class="div_ingr" style="overflow-y: scroll; overflow-x: hidden; scrollbar-width: none; margin-top: 5px; height: 65%; border: 1px dotted #333333; border-radius: 5px;"> 
						<div class="selectDiv 0" style="width:100%; display:grid; grid-template-columns: 35% 30% 35%; font-size: 14px; color: #333333">
							<select class="selectIngr">
								<option value="0">Select ingrediente:</option>
								
							</select>
							<input class="my-input" type="number" min="1" pattern="[0-9]" title="utilizza solo caratteri numerici" size="20" maxlength="auto" placeholder="" name="unitamisura" style="margin: 0 5px 0 5px;" required >
							<select class="selectUdm">
								<option value="0">(unità):</option>
								
							</select>
						</div>
						
						<button id="aggIngr" type="button" class="my-button" style="margin-top:10px; width:30px; height:30px; border-radius: 20px; font-size:12px;"><i class="fas fa-plus"></i></button>
					</div>

				</div>
				<!--#3.2-->
				<div style="float: left;  width: 100%; height:100%; padding: 0 0 0 10px;">
					<p for="procedimento" style="font-family: 'Pacifico'; font-size: 20px; color: #ff9d7d; margin: 0 0 0 0;">Procedimento:</p>
					<div class="my-input" style="width:100%; height:269px; border-radius: 5px; border: 1px solid gray;">
						<div style="display:inline-block; width:100%; border-bottom: 1px solid gray;">
						
							<div  style="float:left; margin-left: 10px;">
								<button class="my-slidingbutton" onclick="document.execCommand('bold');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-bold"></span></button>
								<button class="my-slidingbutton" onclick="document.execCommand('italic');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-italic"></span></button>
								<button class="my-slidingbutton" onclick="document.execCommand('underline');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-underline"></span></button>
							</div>
							
							
							<div  style="float:left; margin-left: 20px;">
								<button class="my-slidingbutton" onclick="document.execCommand('justifyleft');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-align-left"></span></button>
								<button class="my-slidingbutton" onclick="document.execCommand('justifyCenter');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-align-justify"></span></button>
								<button class="my-slidingbutton" onclick="document.execCommand('justifyRight');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-align-right"></span></button>
							</div>
							
							<div  style="float:right; margin-right: 10px;">
								<button class="my-slidingbutton" onclick="document.execCommand('undo');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-undo"></span></button>
								<button class="my-slidingbutton" onclick="document.execCommand('redo');" style="width: 20px; color: #333333; font-size: 15px;"><span class="fa fa-redo"></span></button>
							</div>
													
						</div>
						<div contenteditable="true" id="procedimento" class="myInput" placeholder=" Procedimento" style="margin-top:-6px; scrollbar-width: none; font-family: 'Pacifico'; width:100%; height:90%; display: none; color: #333333; overflow-y: scroll;" required ></div>
					</div>
					
				</div>
			</div>
			<!--#4-->
			<div style="width:100%; border: 1px solid transparent;">
				<label for="file-upload" class="custom-file-upload">
					<input  id="file-upload" type='file'/>
				    <span class="fas fa-image" style="font-size: 20px;"></span>
				</label>
				
				<img id="myImg" src="" alt="your image" style="width:100px; height:100px;"> <!--<i class="fas fa-image"></i> https://i.imgur.com//Ee42FQR.png -->
			</div>
			<!--#5-->
			<div style="width:100%; border: 1px solid transparent;"> 
				<button id="salvaricetta" type="button" onclick="salvaRicetta()" class="my-slidingbutton" style="float:right; width:30%; margin:0; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">salva ricetta <i class="fa fa-feather"></i></button>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/sideMenu.js" ></script>
	<script type="text/javascript" src="js/gestioneFeed.js" ></script>
  	<script type="text/javascript" src="js/gestioneFormRicette.js" ></script>
	<script>	
	
		// all'avvio e durante lo scorrimento
		
		$(window).scroll(function() {   
			   if($(window).scrollTop() + $(window).height() == $(document).height()) {
				   getRicette();
			   }
		});		

	</script>
</body>
</html>
