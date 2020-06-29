<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Impostazioni</title>
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
	<% String username = request.getParameter("username"); %>
	<input id="username" type="hidden" value="${username}">
	
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
	
	<div class="top" style="margin: 40px 0 20px 0; width:40%; height:500px; border: 1px solid transparent;"> 
		<div class="container my-container" style="width:100%; height:100%; padding: 0 0 0 0">
			<div style="float:left; width:40%; height:100%;">
				<p style="margin:10px 0 0 0; padding-bottom:10px; border-bottom: 1px dotted black; text-align:center; font-size: 20px; font-family:'Pacifico';">Impostazioni: </p>
				<button id="accountBtn" class="my-slidingbutton" style="font-family:'Pacifico'; height:40px; border-radius:0px;">Account: </button>
				<p style="margin: 10px 40px; font-family:'Pacifico';">Generale: </p>
				<button id="aspettoBtn" class="my-slidingbutton" style="font-family:'Pacifico'; height:40px; border-radius:0px;">Aspetto: </button>
			</div>
			
			<div id="account" style="float: right; width:60%; height:100%; border-left: 1px dotted black;">
				<button id="datiAnagraficiBtn" class="my-slidingbutton" style="font-family:'Pacifico'; height:40px; border-radius:0px;">Dati Anagrafici<span class='fas fa-angle-right' style='position:relative; top: 2px; float:right; font-size:25px;'></span></button>
				<button id="passwordBtn" class="my-slidingbutton" style="font-family:'Pacifico'; height:40px; border-radius:0px;">Password<span class='fas fa-angle-right' style='position:relative; top: 2px; float:right; font-size:25px;'></span></button>
			</div>
			
			<div id="datiAnagrafici" style="float: right; width:60%; height:100%; border-left: 1px dotted black; display: none;">
				<p class="my-alert-banner success">dati salvati con successo</p>
				<p class="my-alert-banner error">uno o più campi non sono validi</p>
				<div class="my-circle-button backAccount" style="margin:10px 10px;">
					<span class='fas fa-angle-left' style='position:relative; top: 2px; left:4px; font-size:25px;'></span>
					<p style='position:relative; left:10px; font-size:16px; font-family: Pacifico; display:inline-block;'>indietro</p>
				</div>
				<div style="padding:0px 40px;">
					<p for="nome" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Nome:</p>
					<input id="nome" class="my-input" type="text" size="10" maxlength="auto" placeholder="Nome" required >
					<p for="cognome" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Cognome:</p>
					<input id="cognome" class="my-input" type="text" size="10" maxlength="auto" placeholder="Cognome" required >
					<p for="sesso" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Sesso:</p>
					<select id="sesso">
						<option value="">Seleziona</option>
						<option value="M">M</option>
						<option value="F">F</option>
					</select>
				</div>
				<div class="my-circle-button aggiornaDati" style="position:relative; left:35%; top:20%; width:120px; margin:10px 10px;">
					<p style='position:relative; left:10px; font-size:16px; font-family: Pacifico; display:inline-block;'>Aggiorna Dati</p>
				</div>
			</div>
			
			<!-- <div id="indirizzoEmail" style="float: right; width:60%; height:100%; border-left: 1px dotted black; display: none;">
				<p class="my-alert-banner success">dati salvati con successo</p>
				<p class="my-alert-banner error">uno o più campi non sono validi</p>
				<div class="my-circle-button backAccount" style="margin:10px 10px;">
					<span class='fas fa-angle-left' style='position:relative; top: 2px; left:4px; font-size:25px;'></span>
					<p style='position:relative; left:10px; font-size:16px; font-family: Pacifico; display:inline-block;'>indietro</p>
				</div>
				<div style="padding:20px 40px;">
					<p for="email" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Email:</p>
					<input id="email" class="my-input" type="text" size="10" maxlength="auto" placeholder="Indirizzo Email" required >
				</div>
				<div class="my-circle-button aggiornaDati" style="position:relative; left:35%; top:20%; width:120px; margin:10px 10px;">
					<p style='position:relative; left:10px; font-size:16px; font-family: Pacifico; display:inline-block;'>Aggiorna Dati</p>
				</div>
			</div>-->
			
			<div id="password" style="float: right; width:60%; height:100%; border-left: 1px dotted black; display: none;">
				<p class="my-alert-banner success">dati salvati con successo</p>
				<p class="my-alert-banner error">uno o più campi non sono validi</p>
				<div class="my-circle-button backAccount" style="margin:10px 10px;">
					<span class='fas fa-angle-left' style='position:relative; top: 2px; left:4px; font-size:25px;'></span>
					<p style='position:relative; left:10px; font-size:16px; font-family: Pacifico; display:inline-block;'>indietro</p>
				</div>
				<div style="padding:20px 40px;">
					<p for="oldpassword" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Vecchia Password:</p>
					<input id="oldpassword" class="my-input" type="text" size="10" maxlength="auto" placeholder="Vecchia Password" required >
					<p for="newpassword" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Nuova Password:</p>
					<input id="newpassword" class="my-input" type="text" size="10" maxlength="auto" placeholder="Nuova Password" required >
					<p for="confnewpassword" style="font-family: 'Pacifico'; font-size: 20px; color: #333333; margin: 0 0 0 0;">Conferma Nuova Password:</p>
					<input id="confnewpassword" class="my-input" type="text" size="10" maxlength="auto" placeholder="Conferma Nuova Password" required >
				</div>
				<div class="my-circle-button aggiornaDati" style="position:relative; left:35%; top:20%; width:120px; margin:10px 10px;">
					<p style='position:relative; left:10px; font-size:16px; font-family: Pacifico; display:inline-block;'>Aggiorna Dati</p>
				</div>
			</div>
			
			<div id="aspetto" style="float: right; width:60%; height:100%; border-left: 1px dotted black; display: none;">
				<button class="my-slidingbutton" style="font-family:'Pacifico'; height:40px; border-radius:0px;">Modalita scura<span class='fas fa-angle-right' style='position:relative; top: 2px; float:right; font-size:25px;'></span></button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/sideMenu.js" ></script>
	<script type="text/javascript" src="js/impostazioni.js" ></script>
</body>

</html>