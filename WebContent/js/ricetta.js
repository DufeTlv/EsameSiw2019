


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

		document.body.appendChild(newChild); 

	}
}

/*function getCommenti(){
	
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
					}
			}
		}
	});
}


function aggiungiCommento(){	
	var jsonObj = '{"ricetta_id":"' + ${ricetta_id} + '", "account_id": "passhione@ghirga.bucciarati", "descrizione":"' + document.getElementById("commDiv").innerHTML + '"}';

	$.ajax({
		type: "POST",
		url : 'AggiungiCommento',
		datatype: "json",
		data: jsonObj,
		success : function(responseText) {
			alert("commento caricato con successo");
		}
	});
}*/

$(document).ready(
		getCommenti(),
		//getValNutr(),
		popolaSelectIngredienti(),
		popolaForm(),
		editButton(),
		translate()
		
);

function editButton(){

	var rChild = document.createElement("div");
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
	
/*function popolaForm(){
		
	document.getElementById("titolo").value = "${titolo}";
	document.getElementById("tempopreparazione").value = "${tempo}";
	document.getElementById("difficolta").value = ${difficolta};
	document.getElementById("procedimento").innerHTML = "${procedimento}";
	
	var lis = document.getElementsByClassName("edamamAPI");
	for(var i=0; i < lis.length; i++){
		if(i > 0)
			aggiungiIngr();
		
		var ingr = document.getElementsByClassName("selectDiv");
		//alert(ingr[ingr.length-1].childNodes[1].childNodes.length);
		ingr[ingr.length-1].childNodes[1].value = lis[i].childNodes[9].innerHTML;
		ingr[ingr.length-1].childNodes[3].value = lis[i].childNodes[1].innerHTML;
		ingr[ingr.length-1].childNodes[5].value = lis[i].childNodes[7].innerHTML;
		
		alert(lis[i].childNodes[9].innerHTML + " " + lis[i].childNodes[7].innerHTML);
	}	
	
}*/