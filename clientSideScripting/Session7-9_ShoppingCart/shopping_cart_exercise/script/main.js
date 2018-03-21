
//get reference to database
var database = firebase.database();

var validForm=false;

function main() {
	console.log("main called");
	//set up reference to our form, since 
	var form = document.getElementById("productForm"); 
	form.addEventListener("change", formChanged);
	//reference an html element 
	var checkout = document.getElementById("checkoutBtn");
	//myButton holds a reference to our button element 
	//we can now add a on click event 
	checkout.addEventListener("click", checkoutClicked);

}

function checkoutClicked(event) {
	console.log("checkout clicked");
	var form = document.getElementById("productForm"); 
	var key = database.ref().child('shoppingCart').push().key;
	if(validForm) {
		console.log("form valid saving data");
		for(var i = 0; i < form.product.length; i++) {
			var prodName = form.product[i].value;
			var numberElement = prodName+'-quantity';
			// note array returned 
			var number = document.getElementsByName(numberElement)[0].value; 
			console.log('saving shopping cart index '+i+' prodname '+prodName+ ' numberElement ' +numberElement+ ' number '+number);
			document.getElementsByName(numberElement)[0].style.backgroundColor="white";
			if (form.product[i].checked){
				var unitPrice =  parseInt(form.product[i].dataset.price);
				var numberTotal = unitPrice * number;
				var item = {
						productName: prodName,
						quantity: number,
						pricePerUnit:unitPrice,
						totalCost: numberTotal
				};
				//console.log('saving item:'+JSON.stringify(item,null,4) ); // pretty print
				console.log('saving item:'+JSON.stringify(item) );
				//the key is then appended to our database reference and set
				database.ref('shoppingCart/' + key).set(item)
			}
		}
	}
}

/**
  onChange callback function
 **/
function formChanged(event) {
	validForm=true;
	console.log("the form has changed");
	var form = document.getElementById("productForm");

	var subTotal = 0;
	var shipping = 0;
	var taxes = 0;
	var total = 0;
	var summary ='';

	for(var i = 0; i < form.product.length; i++) {
		var prodName = form.product[i].value;
		var numberElement = prodName+'-quantity';
		// note array returned better to use id
		var number = document.getElementsByName(numberElement)[0].value; 
		console.log('index '+i+' prodname '+prodName+ ' numberElement ' +numberElement+ ' number '+number);

		if(number >10){
			validForm=false;
			document.getElementsByName(numberElement)[0].style.backgroundColor="red";
		} else {
			document.getElementsByName(numberElement)[0].style.backgroundColor="white";
			if (form.product[i].checked){
				var unitPrice =  parseInt(form.product[i].dataset.price);
				var numberTotal = unitPrice * number;
				document.getElementById(prodName+'-total').innerHTML =  "£" + numberTotal;
				subTotal = subTotal +  numberTotal;
				console.log('unitPrice '+unitPrice+ ' number '+number + ' numberTotal '+numberTotal );
				var localSummary = '<p>'+number+' x '+prodName+'</p>';
				summary = summary+localSummary;
			} else {
				document.getElementById(prodName+'-total').innerHTML =  '£0';
			}
		}
	}

	//After the loop, check to see if the subTotal is less than £100. If this is the case, set shipping = £10
	if(subTotal<100 && subTotal>0) shipping=10;
	//Set the variable taxes = 20% of subTotal
	taxes=subTotal * 0.2;
	//Set the variable total = subTotal + taxes + shipping
	total = subTotal+shipping+taxes;    

	document.getElementById("sub-total").innerHTML =  "£" + subTotal;
	document.getElementById("shipping").innerHTML =  "£" + shipping;
	document.getElementById("taxes").innerHTML =  "£" + taxes;
	document.getElementById("total").innerHTML = "£" + total;
	document.getElementById("orderSummary").innerHTML = summary;
}