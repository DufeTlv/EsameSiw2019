var image_url = '';
var deletehash = '';

$(document).ready(setClickListeners());

function setClickListeners(){
	
	var saveBtn = document.getElementById("salvaricetta");
	if(saveBtn != null)
		saveBtn.addEventListener('click', function(){
			uploadRicetta(0);
		});
	
	var updBtn = document.getElementById("aggiornaricetta");
	if(updBtn != null)
		updBtn.addEventListener('click', function(){
			uploadRicetta(1);
		});
	
	var delBtn = document.getElementById("eliminaricetta");
	if(delBtn != null)
		delBtn.addEventListener('click', function(){
			$.ajax({
				type: "POST",
				url : 'EliminaRicetta',
				success : function(responseText) {
						alert("ricetta eliminata con successo");
						window.history.back();
				}
			});
		});
	
	document.getElementById("aggIngr").addEventListener('click', function(){
		aggiungiIngr();
	});
	
	var aggBtn = document.getElementById("aggRic");
	if(aggBtn != null)
		aggBtn.addEventListener('click', function(){
			showForm(); 
			popolaSelectIngredienti();
		});
	
	var modBtn = document.getElementById("modRic");
	if(modBtn != null)
		modBtn.addEventListener('click', function(){
			showForm();
		});
	
}

function clearForm(){
	
	document.getElementById("titolo").value = '';
	document.getElementById("tempopreparazione").value = '';
	document.getElementById("difficolta").value = 0;
	document.getElementById("procedimento").innerHTML = '';
	
	while(document.getElementsByClassName("selectDiv").length > 1)
		rimuoviIngr();
	
	document.getElementsByClassName("selectIngr")[0].value = 0; 
	document.getElementsByClassName("selectUdm")[0].value = 0;
	
}

// mostra il form per la creazione e il caricamento delle ricette
function showForm(){
	var y = document.getElementsByClassName("myInput");
	for(var i = 0; i < y.length; i++){
		y[i].style.display = "block";
	}
	
	$('body').css('overflow-y', 'hidden');
}

// nasconde il form per la creazione e il caricamento delle ricette
function hideForm(){
	var i;
	var y = document.getElementsByClassName("myInput");
	for(i = 0; i < y.length; i++){
		y[i].style.display = "none";
	}
	
	$('body').css('overflow-y', 'auto');
	
	clearForm();
}

function aggiungiIngr(){
	var newChild = document.getElementsByClassName("selectDiv")[0].cloneNode(true);
	newChild.style.marginTop = "10px";

	document.getElementsByClassName("div_ingr")[0].insertBefore(newChild, document.getElementById("aggIngr"));
	
	if(document.getElementById("rimIngr") == null){
		
		var rimBtn = document.createElement("button");
		rimBtn.id = "rimIngr";
		rimBtn.type = "button";
		rimBtn.className = "my-button";
		rimBtn.style.marginTop = "10px";
		rimBtn.style.width = "30px";
		rimBtn.style.height = "30px";
		rimBtn.style.borderRadius = "20px";
		rimBtn.style.fontSize = "12px";
		rimBtn.innerHTML = '<i class="fas fa-minus"></i>';
		
		rimBtn.addEventListener('click', function(){
			rimuoviIngr();
		});
		
		document.getElementsByClassName("div_ingr")[0].appendChild(rimBtn);
	}
}

function rimuoviIngr(){
	document.getElementsByClassName("selectDiv")[document.getElementsByClassName("selectDiv").length-1].remove();
	
	if(document.getElementsByClassName("selectDiv").length < 2 && document.getElementById("rimIngr") != null)
		document.getElementById("rimIngr").remove();
}

function popolaSelectIngredienti(){
	if(document.getElementsByClassName("selectIngr")[0].childNodes.length == 3){
		$.ajax({
			type: "POST",
			url : 'DammiIngredienti',
			success : function(responseText) {
				var resp = JSON.parse(responseText);
				
				var selectI = document.getElementsByClassName("selectIngr"); //ingredienti
				var selectU = document.getElementsByClassName("selectUdm");  //unita di misura				
				
				for(var i=0; i < resp.ingrediente.length; i++){
					var newChildI = document.createElement("option");
					newChildI.innerHTML = resp.ingrediente[i].nome;
					newChildI.value = resp.ingrediente[i].id;
					selectI[0].append(newChildI);			
				}		
				for(var i=0; i< resp.udm.length; i++){
					var newChildU = document.createElement("option");
					newChildU.innerHTML = resp.udm[i].nome;
					newChildU.value = resp.udm[i].id;
					selectU[0].append(newChildU);	
				}
				
			},
	        async: false
		});
	}
}

function uploadRicetta(comando){
	
	var titolo = document.getElementById("titolo").value;
	var tdp = document.getElementById("tempopreparazione").value;
	var diff = document.getElementById("difficolta").value;
	var proc = document.getElementById("procedimento").innerHTML;
	
	var jsonObj;
	
	if(titolo != '' && tdp != '' && proc != ''){ //&& ((comando == 0 && image_url != '') || comando == 1 )){
		
		jsonObj = '{"comando":"' + comando + '", "titolo":"' + titolo + '" , "tempopreparazione": "' + tdp + '", "difficolta":' + diff + ', "procedimento":"' + proc + '", "image_url":"' +image_url+ '", "deletehash":"' +deletehash+ '", ';

		var ingredienti = document.getElementsByClassName("selectDiv");
		var array = '"ingr" : [';
		
		for(var i = 0; i < ingredienti.length; i++){
			var id		 = ingredienti[i].childNodes[1].value;
			var quantita = ingredienti[i].childNodes[3].value;
			var udm      = ingredienti[i].childNodes[5].value;

			var ingr = '{ "ingrediente_id":' + id + ', "quantita":' + quantita + ', "udm":' + udm + '}';	
			
			array += ingr;

			if(i < ingredienti.length-1)
				array += ', ';
		}	

		array += ']}';
		
		jsonObj += array;
		
		$.ajax({
			type: "POST",
			url: "AggiungiModificaRicetta",
			datatype: "json",
			data: jsonObj,
			success: function (responseText){
				//alert(responseText);
				alert("ricetta caricata con successo");
				hideForm();
			}
		});
	
	} else {
		alert(" uno dei campi è vuoto o incompleto ");
	}
}

//per il caricamento delle immagini
$("document").ready(imgur());

function imgur() {

	$('input[type=file]').on("change", function() {

		var $files = $(this).get(0).files;

		if ($files.length) {

			// Reject big files
			if ($files[0].size > $(this).data("max-size") * 1024) {
				console.log("Please select a smaller file");
				return false;
			}			

			// Begin file upload
			console.log("Uploading file to Imgur..");

			// Replace ctrlq with your own API key
			var apiUrl = 'https://api.imgur.com/3/image';
			var apiKey = 'a81cd68a56ce0b1';

			var settings = {
				async: false,
				crossDomain: true,
				processData: false,
				contentType: false,
				type: 'POST',
				url: apiUrl,
				headers: {
					Authorization: 'Client-ID ' + apiKey,
					Accept: 'application/json'
				},
				mimeType: 'multipart/form-data'
			};

			var formData = new FormData();
			formData.append("image", $files[0]);
			settings.data = formData;

			// Response contains stringified JSON
			// Image URL available at response.data.link
			$.ajax(settings).done(function(response) {
				console.log(response);
				var jsonObj = JSON.parse(response);
				
				alert("immagine caricata con successo");
				//alert(jsonObj.data.link + ' ' + jsonObj.data.deletehash); //JXBhlV6uJHS20ST, LP8nDOJj8Mp0gbV
				image_url = jsonObj.data.link;
				deletehash = jsonObj.data.deletehash;
			});

		}
	});
}
