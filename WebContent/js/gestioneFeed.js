var seldiv = 'feed';

$(document).ready(
		setProfileButtons(),
		getRicette()
		);

function setProfileButtons(){
	
	var mie = document.getElementById("mie");
	if(mie != null){
		mie.addEventListener("webkitAnimationEnd", function(){
			if( mie.style.animationName == "leftout" ){
				mie.style.display = "none"; 
				//alert("end of mie animation");
			}
		});
	}

	var pref = document.getElementById("preferite");
	if(pref != null){
		pref.addEventListener("webkitAnimationEnd", function(){
			if( pref.style.animationName == "rightout" ){
				pref.style.display = "none"; 
				//alert("end of pref animation");
			}
		});
	}
	
	if(document.getElementById("btnmie") != null){
		seldiv = 'mie';
		
		document.getElementById("btnmie").addEventListener('click', function(){
			mostraMie();
		});
	}
	
	if(document.getElementById("btnpref") != null){
		document.getElementById("btnpref").addEventListener('click', function(){
			mostraPreferiti();
		});
	}
}

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

function getRicette(){			    
	    
	
	var jsonObj;
	
	if(seldiv != "preferite"){
		var id;
		if(document.getElementsByClassName(seldiv).length < 1){
			id = -1;
		}else{
			id = document.getElementsByClassName(seldiv)[(document.getElementsByClassName(seldiv).length)-1].id;
		}
		jsonObj = '{"id":' + id + ', "action":' + ((seldiv == "feed")? 0 : 1 ) + '}';
	}else{
		jsonObj = '{"offset":' + document.getElementsByClassName(seldiv).length + '}';
	}
	
	$.ajax({
		type: "POST",
		url : (seldiv == "feed")? 'DammiRicette': (seldiv == "mie")? 'DammiRicette' : 'DammiRicettePreferite',
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
					forms[i].childNodes[0].childNodes[0].childNodes[1].innerHTML = "<span style='color:#ff9d7d;'>Difficolta: </span>" + ricette.ricetta[j].difficolta;
					forms[i].childNodes[0].childNodes[0].childNodes[2].innerHTML = "<span style='color:#ff9d7d;'>Tempo: </span>" + ricette.ricetta[j].tempo;
					
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
		newChild.className = "container my-container"; //feedDivs
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
			var t = this;
			var jsonObj = '{"ricetta_id":"'+ t.parentNode.parentNode.parentNode.childNodes[1].value +'"}';
			$.ajax({
				type: "POST",
				url: "AggiungiRimuoviPreferiti",
				datatype: "json",
				data: jsonObj,
				success: function (responseText){
					var p = JSON.parse(responseText);
					if(p.p == "false"){
						t.parentNode.parentNode.childNodes[2].style.display = "block";
						t.parentNode.parentNode.childNodes[2].style.animation = "pop 0.7s";
						
						t.style.borderColor = "#ff9d7d";
						t.style.borderBottomColor = "transparent";
					} else {
						t.style.borderColor = "white";
						t.style.borderBottomColor = "transparent";
					}
				}
			});
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
		rChild.innerHTML = "<span class='fas fa-arrow-circle-right' style='position:absolute; top: 4px; left:4px; font-size:30px;'></span>"
						   + "<p style='position:absolute; right:20px; top: 6px; font-size:16px; font-family: Pacifico; display: none;'>mostra ricetta</p>";

		rChild.addEventListener('webkitAnimationEnd', function(){
			this.childNodes[1].style.display = 'inline-block'
			this.childNodes[1].style.animation = 'opacitybutton 0.5s';
		});
		
		rChild.addEventListener('click', function(){
			this.parentNode.parentNode.submit();
		});
		
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

		document.getElementById(seldiv).appendChild(form);
	}
}
