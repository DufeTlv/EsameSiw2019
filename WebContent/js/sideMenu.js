/**
 * 
 */

$(document).ready(setSideMenuListener());

function setSideMenuListener(){
	
	var hexagonButtons = document.getElementsByClassName("hexagon");
	for(var i = 0; i < hexagonButtons.length; i++){
		hexagonButtons[i].addEventListener('click', function(){
			this.childNodes[3].submit();
		});
	}
}