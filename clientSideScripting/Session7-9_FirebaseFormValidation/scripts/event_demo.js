/**
    Main Function is invoked when the html 
    page has loaded

**/
function main() {
    //reference an html element 
    var myButton = document.getElementById("button");
     //myButton holds a reference to our button element 
    //we can now add a on click event 
    myButton.addEventListener("click", buttonClicked)

}

/**
  buttonClicked call back function 
**/

function buttonClicked() {
   //get the value entered into the user name     
   var myUserName = document.getElementById("userName").value 
   //ouput to our p tag 
   document.getElementById("output").innerHTML = "Hello " + myUserName;
}