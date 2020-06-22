/**
 * 
 */
function sliding(){
	var slideIndex = 0;
	carousel();

	function carousel() {
	  var i;
	  var x = document.getElementsByClassName("mySlides");
	  var y = document.getElementsByClassName("centered");
	  for (i = 0; i < x.length; i++) {
	    x[i].style.display = "none"; 
	    y[i].style.display = "none";
	  }
	  slideIndex++;
	  if (slideIndex > x.length) {slideIndex = 1} 
	  x[slideIndex-1].style.display = "block"; 
	  y[slideIndex-1].style.display = "block";
	  setTimeout(carousel, 5000); 
	}

}