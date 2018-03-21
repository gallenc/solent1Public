//get reference to database
var database = firebase.database();

//onload callback function
function main() {
	console.log("in main function");
	var myForm  = document.getElementById("addDetails");

	// add listner for form
	myForm.addEventListener("submit",validateForm);

	// add listener for database records updated
	database.ref('details/').on('value', recordsUpdated);
}

//validate callback function 
function validateForm(event) {
	console.log("in validateForm function");

	var formValid = true;
	var myForm = document.getElementById("addDetails"); 

	var name= myForm.name.value;
	// "name"
	// "nameRequiredError"
	if (myForm.name.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("nameRequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("nameRequiredError").style.display = "none";
	}

	var age = myForm.age.value;
	// "age"
	// "ageRequiredError"
	if (myForm.age.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("ageRequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("ageRequiredError").style.display = "none";
	}

	var location = myForm.location.value; 
	// "location"
	// "locationRequiredError"
	if (myForm.location.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("locationRequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("locationRequiredError").style.display = "none";
	}

	var email = myForm.email.value;
	// "email"
	// "emailRequiredError"
	if (myForm.email.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("emailRequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("emailRequiredError").style.display = "none";
	}

	var phone_number = myForm.phone_number.value;
	// "phone_number"
	// "phone_numberRequiredError"
	if (myForm.phone_number.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("phone_numberRequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("phone_numberRequiredError").style.display = "none";
	}

	var address_line_1 = myForm.address_line_1.value;
	// "address_line_1"
	// "address_line_1_RequiredError"
	if (myForm.address_line_1.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("address_line_1_RequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("address_line_1_RequiredError").style.display = "none";
	}

	var post_code = myForm.post_code.value;
	// "post_code"
	// "post_code_RequiredError"
	if (myForm.post_code.value == "") {
		formValid = false;
		//display error message 
		document.getElementById("post_code_RequiredError").style.display = "block";
		//stop form from submitting
		event.preventDefault();
	} else {
		document.getElementById("post_code_RequiredError").style.display = "none";
	}

	// if form valid save to firebase
	if (formValid){
		console.log("form valid - saving to firebase");
		//grab a reference to our firebase database
		var key = database.ref().child('details').push().key;

		//the key is then appended to our database reference and set
		database.ref('details/' + key).set({
			name: name,
			age: age,
			location: location, 
			email: email,
			phone_number: phone_number,
			address_line_1: address_line_1,
			post_code : post_code
		})

	}

}


function recordsUpdated(snap) {
	
	//using line
	var contacts = '';  
	snap.forEach(function(record) {
		contacts = contacts + '<p>' 
		+ 'name: ' + record.val().name 
		+ ' ' + 'age: '  + record.val().age 
		+ ' ' + 'location: '  + record.val().location 
		+ ' ' + 'email: '  + record.val().email 
		+ ' ' + 'phone_number: '  + record.val().phone_number 
		+ ' ' + 'address-line_1: '  + record.val().address_line_1
		+ ' ' + 'post_code: '  + record.val().post_code 
		+  ' ' +'</p>';
	});

	document.getElementById('contact-list').innerHTML = contacts;


//	example using table

	var table = document.getElementById("contact-table"); 
	snap.forEach(function(record) {
		// create new row
		var tr = document.createElement("tr");

		// create and add name element
		var txt = document.createTextNode(record.val().name );
		var td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		// create and add age element
		txt = document.createTextNode(record.val().age );
		td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		// create and add location element
		txt = document.createTextNode(record.val().location );
		td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		// create and add email element
		txt = document.createTextNode(record.val().email );
		td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		// create and add phone element
		txt = document.createTextNode(record.val().phone_number );
		td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		// create and add address-line_1 element
		txt = document.createTextNode(record.val().address_line_1 );
		td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		// create and add address-line_1 element
		txt = document.createTextNode(record.val().post_code );
		td = document.createElement("td");
		td.appendChild(txt);
		tr.appendChild(td);

		table.appendChild(tr);
	});


}