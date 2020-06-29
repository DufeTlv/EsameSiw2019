<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<!DOCTYPE html>

<%@page import="model.Quantita"%> 
<%@page import="model.Ingrediente"%> 
<%@page import="model.UnitaDiMisura"%> 
<%@page import="java.util.ArrayList"%> 

<html>
<head>

	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Recipe</title>

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

	<!-- side nav menu -->
	<div style="position:fixed; top:4%; left:14%; border: 1px solid transparent; color: #ff9d7d;">
		<div id="home" onclick="window.history.back();" class="hexagon hex-left" style="left: 0%;">
			<div style="position:absolute; z-index:2; font-family:'Pacifico'; left:38%; top:15%;"><i class="fas fa-arrow-left"></i></div>
		</div>
	</div>

	<div style="margin: 40px 0 20px 0; border: 1px solid transparent;"> 
		<div class="container my-container" style="width:40%; padding: 0 0 0 0">
			<div style="padding: 30px 50px 10px 50px">
				<p id="titoloR" style="font-family: 'Pacifico'; text-align: center" > ~${titolo}~ </p>
				<div style="height:100% width:100%; border-radius: 5px; border: 1px solid black; border-style: dotted; margin-top: 20px; padding: 10px;">
					<div style="display:inline-block; width:50%; border-right: 1px solid black; border-style: hidden dotted hidden hidden;">
						<p id="ingredientiR" style="font-family: 'Pacifico';">  Ingredienti: </p>
						<ul id="ingrUL" style="font-family: 'Pacifico';">
							<%
								ArrayList<Quantita> qnt = (ArrayList<Quantita>)request.getSession().getAttribute("quantitalist"); 
								ArrayList<Ingrediente> ingr = (ArrayList<Ingrediente>)request.getSession().getAttribute("ingrlist"); 
								ArrayList<UnitaDiMisura> udm = (ArrayList<UnitaDiMisura>)request.getSession().getAttribute("udmlist");
								if(qnt != null){
					        		//for(Quantita q:qnt){ 
					        		for(int i=0; i < qnt.size(); i++){
					        			out.println("<li class='edamamAPI'>");
										out.println("<p style='display:inline-block'>" + qnt.get(i).getValore() + "</p>");
										if(udm.get(qnt.get(i).getUnitaDiMisura_id()).getId() != 0)
											out.println(udm.get(qnt.get(i).getUnitaDiMisura_id()).getNome());
										out.println(ingr.get(i).getNome());
										
										out.println("<p style='display:none'>" + udm.get(qnt.get(i).getUnitaDiMisura_id()).getUri() + "</p>");
										out.println("<p style='display:none'>" + ingr.get(i).getApiFoodId() + "</p>");
										
										out.println("<p style='display:none'>" + qnt.get(i).getUnitaDiMisura_id() + "</p>");
										out.println("<p style='display:none'>" + ingr.get(i).getId() + "</p>");
										//out.println("<p style='display:none'>" + ingr.size() + "</p>");
										
										out.println("</li>");
									}
								}
							%>				
						</ul>
					</div>
					<div style="display:inline-block; width:50%; float:right;">
						<p id="difficoltaR" style="font-family: 'Pacifico'; padding-left:10px;" > Difficoltà: <% out.println((request.getSession().getAttribute("difficolta").equals(0))? "Facile" :(request.getSession().getAttribute("difficolta").equals(1))? "Media": "Difficile"); %> </p>
						<p id="tempoR" style="font-family: 'Pacifico'; padding-left:10px;" > Tempo di Preparazione: ${tempo} </p>
					</div>
				</div>
				
				<div id="valnut" style="height:100% width:100%; font-family: 'Pacifico'; border-radius: 5px; border: 1px solid black; border-style: dotted; margin-top: 20px; padding: 10px;">
						<p style="margin-top: -22px; padding-left: 10px; width:20%; background-color: white;"> valori nutrizionali: </p>
				</div>
				
				<div id="procedimentoR" style="font-family: 'Pacifico'; margin-top: 15px; margin-bottom: 15px;">${procedimento}</div>
			</div>
			<div style="height:500px; width:100%; overflow: hidden;">
				<c:if test="${imgurl != null}">
					<img src="${imgurl}" style="width:100%; border-radius: 0 0 5px 5px;">
				</c:if>
			</div>
			
			<!--<iframe width="560" height="315" src="https://www.youtube.com/embed/y881t8ilMyc" frameborder="0" allowfullscreen></iframe>-->
			
		</div>
	</div>
	
	<div id="capp">
	</div>
	
	
	<!--brightness-->
	<div id="bright" class="brightness-filter myInput"> </div>

	<!--  -->
	<div id="ness" class="my-container-div middle myInput" style="position: fixed; width: 40%; z-index: 1; display: none;"> 
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
					<!--#3.1.1-->
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
					<!--#3.1.2-->
					<div class="div_ingr" style="overflow-y: scroll; overflow-x: hidden; scrollbar-width: none; margin-top: 5px; height: 65%; border: 1px dotted #333333; border-radius: 5px;"> 
						<div class="selectDiv 0" style="width:100%; display:grid; grid-template-columns: 35% 30% 35%; font-size: 14px; color: #333333">
							<select class="selectIngr">
								<option value="0" selected="selected">Select ingrediente:</option>
							</select>
							<input class="my-input" type="number" min="1" pattern="[0-9]" title="utilizza solo caratteri numerici" size="20" maxlength="auto" placeholder="" name="unitamisura" style="margin: 0 5px 0 5px;" required >
							<select class="selectUdm">
								<option value="0" selected="selected">(unità):</option>
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
						<div contenteditable="true" id="procedimento" class="myInput" placeholder="Procedimento" style="margin-top:-6px; scrollbar-width: none; width:100%; height:90%; display: none; color: #333333; overflow-y: scroll;" required ></div>
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
				<button id="eliminaricetta"  type="button" class="my-slidingbutton" style="float:left; width:30%; margin:0; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">elimina ricetta <i class="fas fa-trash"></i></button>
				<button id="aggiornaricetta" type="button" class="my-slidingbutton" style="float:right; width:30%; margin:0; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">aggiorna ricetta <i class="fa fa-feather"></i></button>
			</div>
		</div>
	</div>
	
	<div id="commenti" style="margin: 10px 0 200px 0; border: 1px solid transparent;"> 
	
		<div id="formCommento"class="container my-container" style="width:40%; padding: 0;">
			<div style="width:100%; padding: 10px 20px;">
				<div style="width:40%; padding: 2px;">
					<p id="username-label" style="font-family: 'Pacifico';">${username}</p>
				</div>
				<div contenteditable="true" id="commDiv" class="my-input" placeholder=" scrivi un commento" style="font-family: 'Pacifico'; font-size: 18px;  border:1px solid #AAAAAA; border-radius:4px; width:100%; height:40px; color: #333333; overflow-y: hidden;" required ></div>
				
				<div  style="width:100%; margin:10px 0; display:grid; grid-template-columns: 65% 35%; clear:both;">
					
					<div id="gifSearchDiv" class="my-expand-div" style=" width:35px; height:34px; margin:0; display:grid; grid-template-columns: 8% 84% 10%; clear:both; border: 1px solid #ff9d7d; border-radius: 20px;">
						<button class="my-slidingbutton" onclick="searchDivController();" style=" width:32px; border-radius: 20px; margin-left:1px;"><span class="gif-icon"></span></button>
						<input id="ricercaGIF" type="text" maxlength="auto" placeholder="cerca GIF" style="margin:0; border-radius: 20px; color: #333333; border: none; display:none;" required >
						<button onclick="giphy();" class="my-slidingbutton" style="float:right; margin:0; margin-left:5px; width:32px; font-size: 12px; font-family:'Pacifico'; border-radius: 20px; display:none;"><span class="fas fa-search"></span></button>
					</div>
					
					<button class="my-slidingbutton" onclick="aggiungiCommento();" style="float:right; width:100%; margin-left:10px; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">pubblica</button>
					
				</div>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript" src="js/ricetta.js" ></script>
	<script type="text/javascript" src="js/gestioneFormRicette.js" ></script>
	
	<% if (((boolean)request.getSession().getAttribute("edit")) == true) { %>
	    <script id="oneTime" type="text/javascript">
	    	
	    	editButton();	    	
	    	
	    </script>
	<% }else{ %>
		<script id="oneTime" type="text/javascript">
	    	
			document.getElementById("bright").remove();
			document.getElementById("ness").remove();
			document.getElementById("oneTime").remove();
	    	
	    </script>		
	<% } %>
	
	<script>
	
		$(document).ready(
				popolaSelectIngredienti(),
				popolaForm()
		);
		
		function popolaForm(){
			
			document.getElementById("titolo").value = "${titolo}";
			document.getElementById("tempopreparazione").value = "${tempo}";
			document.getElementById("difficolta").value = ${difficolta};
			document.getElementById("procedimento").innerHTML = "${procedimento}";
			
			var lis = document.getElementsByClassName("edamamAPI");
			for(var i=0; i < lis.length; i++){
				if(i > 0)
					aggiungiIngr();
				
				var ingr = document.getElementsByClassName("selectDiv");
				ingr[ingr.length-1].childNodes[1].value = lis[i].childNodes[9].innerHTML;
				ingr[ingr.length-1].childNodes[3].value = lis[i].childNodes[1].innerHTML;
				ingr[ingr.length-1].childNodes[5].value = lis[i].childNodes[7].innerHTML;
				
			}	
			
		}
		
	</script>

</body>
</html>