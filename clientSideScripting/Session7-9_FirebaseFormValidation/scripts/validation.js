//onload callback function
 function main() {

    console.log("in main function");
    var myForm  = document.getElementById("login");
    myForm.addEventListener("submit",validateForm);
  }

  //validate callback function 
  function validateForm(event) {
    var formValid = true;
    var myForm = document.getElementById("login"); 

    if (myForm.userName.value == "") {
        formValid = false;
        //display error message 
        document.getElementById("userNameRequiredError").style.display = "block";
        //stop form from submitting
        event.preventDefault();
    } else {

         document.getElementById("userNameRequiredError").style.display = "none";
    }

    if (myForm.password.value == "") {
        formValid = false;
        //display error message 
        document.getElementById("passwordRequiredError").style.display = "block";
        //stop form from submitting
        event.preventDefault();
    } else {

         document.getElementById("passwordRequiredError").style.display = "none";
    }

  }