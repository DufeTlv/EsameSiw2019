
$(document).ready(setClickListeners(), resettaForm());
	
function setClickListeners(){
	
	document.getElementById("accountBtn").addEventListener('click', function(){
		mostraAccount();
	});
	
	document.getElementById("aspettoBtn").addEventListener('click', function(){
		mostraAspetto();
	});
	
	var bkA = document.getElementsByClassName("backAccount");
	for(var i = 0; i < bkA.length; i++){
		bkA[i].addEventListener('click', function(){
			mostraAccount();
			resettaForm();
		});
	}
	
	document.getElementById("datiAnagraficiBtn").addEventListener('click', function(){
		hideAllDivs();
		document.getElementById('datiAnagrafici').style.display = 'block';
	});
	
	document.getElementById("passwordBtn").addEventListener('click', function(){
		hideAllDivs();
		document.getElementById('password').style.display = 'block';
	});
	
	var upd = document.getElementsByClassName("aggiornaDati");
	for(var j = 0; j < upd.length; j++){
		upd[j].addEventListener('click', function(){
			var jsonObj = '{';
			jsonObj += '"account_id":"' 	 + document.getElementById("username").value   		+ '",';
			jsonObj += '"nome":"'       	 + document.getElementById("nome").value       		+ '",';
			jsonObj += '"cognome":"'    	 + document.getElementById("cognome").value	   		+ '",';
			jsonObj += '"sesso":"'    		 + document.getElementById("sesso").value	   		+ '",';
			jsonObj += '"email":"'    		 + document.getElementById("email").value	   		+ '",';
			jsonObj += '"oldpassword":"'     + document.getElementById("oldpassword").value	   	+ '",';
			jsonObj += '"newpassword":"'     + document.getElementById("newpassword").value	   	+ '",';
			jsonObj += '"confnewpassword":"' + document.getElementById("confnewpassword").value + '"}';
			
			alert(jsonObj);
			hideBanners();
			
			var t = this;
			$.ajax({
				type: "POST",
				url: "AggiornaAccount",
				datatype: "json",
				data: jsonObj,
				success: function(){
					t.parentNode.childNodes[1].style.display = 'block';
				},
				error: function (){
					t.parentNode.childNodes[3].style.display = 'block';
				}
			});
		});
	}
}

function resettaForm(){
	document.getElementById("nome").value = "";
	document.getElementById("cognome").value = "";
	document.getElementById("sesso").selectedIndex = 0;
	document.getElementById("email").value = "";
	document.getElementById("oldpassword").value = "";
	document.getElementById("newpassword").value = "";
	document.getElementById("confnewpassword").value = "";
}

function mostraAccount(){
	hideAllDivs();
	document.getElementById('account').style.display = 'block';
}

function mostraAspetto(){
	hideAllDivs();
	document.getElementById('aspetto').style.display = 'block';
}

function hideBanners(){
	var bns = document.getElementsByClassName("my-alert-banner");
	for(var i = 0; i < bns.length; i++){
		bns[i].style.display = 'none';
	}
}

function hideAllDivs(){
	document.getElementById("account").style.display        = 'none';
	document.getElementById("datiAnagrafici").style.display = 'none';
	document.getElementById("indirizzoEmail").style.display = 'none';
	document.getElementById("password").style.display       = 'none';
	document.getElementById("aspetto").style.display        = 'none';
	
	hideBanners();
}