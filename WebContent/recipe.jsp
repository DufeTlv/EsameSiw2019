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

	<nav class="navbar navbar-inverse my-navbar" style="position: relative; margin-top:20px; font-family: 'Pacifico'">
	 	<div style=" padding: 0 0 0 0 ;"> <!-- class="container-fluid" -->
			
			<ul class="nav navbar-nav">
				<li class="my-slidingbutton" tab-index="0" ><a>chi siamo?</a>
				</li>
			</ul>

			<div class="navbar-header" style="position: absolute; left: 50%; transform: translatex(-50%);" > <!--#080808  #FA7268-->
				<a style="font-family: 'Pacifico'; font-size:25px; color: #ff9d7d;" class="navbar-brand active" href="./index.html"> -FoodBalance- </a>
			</div>

			<ul class="nav navbar-nav navbar-right" style="position: absolute; right: 0.9%;">
				<li class="my-slidingbutton"><a href="./createuser.html"><span class="glyphicon glyphicon-user"></span> Log Out</a></li>
			</ul>
	  	</div>
	</nav>

	<div style="margin: 20px 0 20px 0;  border: 1px solid transparent;"> 
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
	
	<!--brightness-->
	<div class="myInput" style="border: solid 0px white; position: fixed; top:0; left:0; background-color: rgba(0,0,0, 0.5); width: 100%; height: 100%; display: none;"> </div>

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
						<div contenteditable="true" id="procedimento" class="myInput" placeholder=" Procedimento" style="margin-top:-6px; scrollbar-width: none; width:100%; height:90%; display: none; color: #333333; overflow-y: scroll;" required ></div>
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
				<button id="aggiornaricetta" type="button" class="my-slidingbutton" style="float:right; width:30%; margin:0; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">aggiorna ricetta <i class="fa fa-feather"></i></button>
			</div>
		</div>
	</div>
	
	<div style="position:absolute; right:30%; margin-top: -20px;" id="google_translate_element"></div>
	
	<div id="commenti" style="margin: 10px 0 200px 0; border: 1px solid black;"> 
	
		<div id="formCommento"class="container my-container" style="width:40%; padding: 0;">
			<div style="width:100%; padding: 10px 20px;">
				<div style="width:40%; padding: 2px;">
					<p id="username-label" style="font-family: 'Pacifico';">Nome Utente</p>
				</div>
				<div contenteditable="true" id="commDiv" class="my-input" placeholder=" scrivi un commento" style="font-family: 'Pacifico'; font-size: 18px;  border:1px solid #AAAAAA; border-radius:4px; width:100%; height:40px; color: #333333; overflow-y: hidden;" required ></div>
				
				<div  style="width:100%; margin:10px 0; display:grid; grid-template-columns: 65% 35%; clear:both;">
					
					<div id="divGif" class="my-expand-div" style=" width:35px; height:34px; margin:0; display:grid; grid-template-columns: 8% 84% 10%; clear:both; border: 1px solid #ff9d7d; border-radius: 20px;">
						<button class="my-slidingbutton" onclick="openDiv();" style=" width:32px; border-radius: 20px; margin-left:1px;"><span class="gif-icon"></span></button>
						<input id="ricercaGIF" type="text" maxlength="auto" placeholder="cerca GIF" style="margin:0; border-radius: 20px; color: #333333; border: none; display:none;" required >
						<button onclick="giphy();" class="my-slidingbutton" style="float:right; margin:0; margin-left:5px; width:32px; font-size: 12px; font-family:'Pacifico'; border-radius: 20px; display:none;"><span class="fas fa-search"></span></button>
					</div>
					
					<button class="my-slidingbutton" onclick="aggiungiCommento();" style="float:right; width:100%; margin-left:10px; font-family:'Pacifico'; border:1px solid #ff9d7d; border-radius: 20px;">pubblica</button>
					
				</div>
			</div>
		</div>
		
		
	</div>
	
	<!-- <script type="text/javascript">
		function googleTranslateElementInit() {
		  new google.translate.TranslateElement({pageLanguage: 'it'}, 'google_translate_element');
		}
	</script> -->
		
	<!-- <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script> -->
	
	<script type="text/javascript" src="js/gestioneFormRicette.js" ></script>
	<!-- <script type="text/javascript" src="js/ricetta.js" ></script> -->
	
	<script>
	
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
			
			var jsonObj = '{"ricetta_id":"' + ${ricetta_id} + '"}';
			
			$.ajax({
				type: "POST",
				url : 'DammiCommento',
				datatype: "json",
				data: jsonObj,
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
				
			var jsonObj = '{"ricetta_id":"' + ${ricetta_id} + '", "account_id": "passhione@ghirga.bucciarati", "descrizione":"' + document.getElementById("commDiv").innerHTML 
				+ '", "gif_url":"' + gifUrl + '", "gif_url_still":"' + gifUrlStill + '"}';
			
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
		
		$(document).ready(
				getCommenti(),
				//getValNutr(),
				popolaSelectIngredienti(),
				popolaForm(),
				editButton()
				
		);
		
		function editButton(){
			
			var rChild = document.createElement("div");
			rChild.id = "modRic";
			rChild.className = "my-expand-button";
			rChild.style.fontFamily = "Pacifico";
			rChild.style.position = "absolute";
			rChild.style.top = "12%";
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
		
		var energia, grassi, carb, prot, zucch, fibre;
		var cnt;
		function getValNutr(){
			var lis = document.getElementsByClassName("edamamAPI");
			
			energia = 0; grassi = 0; carb = 0; prot = 0; zucch = 0; fibre = 0;
			
			cnt = lis.length;
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
		
		function openDiv(){
			
			var node = document.getElementById("divGif");
		
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
		
		function gifDiv(){
			
			if(document.getElementById("results") == null){
				var divChild = document.createElement("div");
				divChild.id = "results";
				divChild.className = "container my-container";
				divChild.style.backgroundColor = "#ffe1d8";
				divChild.style.width = "100%";
				divChild.style.padding= "20px";
				divChild.style.maxHeight = "300px";
				divChild.style.overflowY = "scroll";
				
				var inputChild = document.createElement("input");
				inputChild
				
				document.getElementById("commenti").childNodes[1].appendChild(divChild);
				
				giphy();
			}
			
		}
		
		function clearGifDiv(){
			
			while(document.getElementsByClassName("gif-box").length>0){
				document.getElementsByClassName("gif-box")[document.getElementsByClassName("gif-box").length-1].remove();
			}
		}
		
		function giphy(){
			gifDiv();
			clearGifDiv();
			
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
			          // "width": "200px",
			          "src": gif.fixed_height_still.url,
			          "class": "gif",
			          "data-animate": gif.fixed_height.url,
			          "data-still": gif.fixed_height_still.url,
			          "data-state": "still"			          
			        });
			        // $div.attr("id", "gif-" + i);
			        $div.addClass("gif-box");
			        $div.append($img);
			        $("#results").append($div);
			      }
			
			      $(".gif").on("click", function() {
			    	  openDiv();
			    	  
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
		
	</script>

</body>
</html>