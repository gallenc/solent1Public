function main() {
	var form = document.getElementById("carCleaning");
	form.addEventListener("change", formChanged);
}

function formChanged(event) {
	console.log("the form has changed");
	var total = 0; 
	var choices  = "";
	var form = document.getElementById("carCleaning");

	for(var i = 0; i < form.extras.length; i++) {

		if (form.extras[i].checked){
			//convert the data attribute value from a string to a int
			itemPrice = parseInt(form.extras[i].dataset.price );
			//update total 
			total = total + itemPrice;
			//concatinate the new choice to our choices string 
			choices = choices + "<p>" + form.extras[i].dataset.humanDesc   +  "<p>";
		}
	}

	//update the display 
	document.getElementById("choices").innerHTML =    choices; 
	document.getElementById("price").innerHTML = itemPrice;
	document.getElementById("total").innerHTML = total;
}