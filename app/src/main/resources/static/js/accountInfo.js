
const URL = 'http://localhost:7000';


let updateButton = document.getElementById('updateButton');
let updateOtherInfo = document.getElementById('updateOtherInformation');
let submitUpdateButton = document.getElementById('submitUpdateButton');

updateButton.addEventListener('click', toggleOtherInfoForm);

let updatePassButton = document.getElementById('updatePassButton');
let updatePass = document.getElementById('updatePassword');
let submitPassButton = document.getElementById('submitPassButton');

let oldPass = document.getElementById('oldPass');
let newPass = document.getElementById('newPass');

updatePassButton.addEventListener('click', togglePassForm);


function handleErrors(response) {
    if (!response.ok) {
        if (response.status == 403){
            window.location.href = "forbiddenError.html"
        }else if (response.status == 500){
            window.location.href = "internalServerError.html"
        }
        throw Error(response.statusText);
    } 
    return response;
}



function toggleOtherInfoForm(){
    if (updateOtherInfo.hasAttribute('hidden')){
        updateOtherInfo.removeAttribute('hidden');
        submitUpdateButton.removeAttribute('hidden');
    } else {
        updateOtherInfo.setAttribute('hidden', true);
        submitUpdateButton.setAttribute('hidden', true);
    }
}

function togglePassForm(){
    if (updatePass.hasAttribute('hidden')){
        updatePass.removeAttribute('hidden');
        submitPassButton.removeAttribute('hidden');
        oldPass.setAttribute('required', true);
        newPass.setAttribute('required', true);
    } else {
        updatePass.setAttribute('hidden', true);
        submitPassButton.setAttribute('hidden', true);
        oldPass.removeAttribute('required');
        newPass.removeAttribute('required');
    }
}

// Start by creating an anonymous function which fills out the inner html
// with the information from the user

var firstNameParam;
var lastNameParam;
var usernameParam;
var emailParam;

// Start by creating an anonymous function which fills out the inner html
// with the information from the user

(()=>{
    let apiUrl = `${URL}/user/information/`;
    console.log("i retrieved user info");
    fetch(apiUrl)
    .then(handleErrors)
        .then((res) => res.json())
        .then((data) => {
            document.getElementById('navbar').removeAttribute('hidden')
            document.getElementById('welcomeMessage').removeAttribute('hidden')
            document.getElementById('mainContainer').removeAttribute('hidden')
            document.getElementById('footer').removeAttribute('hidden')
            document.getElementById('submitRequest').removeAttribute('hidden')
            document.getElementById('accountInfo').removeAttribute('hidden')
            document.getElementById('cell1').innerHTML = "Employee ID: " + `${data.userId}`
            document.getElementById('cell2').innerHTML = "First Name: " + `${data.firstName}`
            firstNameParam = `${data.firstName}`;
            document.getElementById('cell3').innerHTML = "Last Name: " + `${data.lastName}`
            lastNameParam = `${data.lastName}`;
            document.getElementById('cell4').innerHTML = "Username: " + `${data.username}`
            usernameParam = `${data.username}`;
            document.getElementById('cell5').innerHTML = "Email: " + `${data.email}`
            emailParam = `${data.email}`;
        });
})();



// We need to set it up so when the submit changes button is clicked
// the changes get sent, start with the other information

// We should start by creating a function called putOtherInfo
// this will look very similar to the stuff in the submitRequest.js file
// Remember, if a field is left blank the update should just send the old
// value, like if the email form is left blank, the put request should 
// send the old email, I'd do this with an if statement



let changeAccountInfo = async () => {
    let FirstName;
    if (document.getElementById('firstName').value == ""){
        FirstName = firstNameParam;
    }else{
        FirstName = document.getElementById('firstName').value;
    }
    
    let LastName;
    if(document.getElementById('lastName').value==""){
        LastName= lastNameParam;
    }else{
        LastName = document.getElementById('lastName').value;
    }

    let Username;
    if(document.getElementById('username').value==""){
        Username=usernameParam;
    }else{
        Username=document.getElementById('username').value;
    }
        
    let email;
    if(document.getElementById('email').value==""){
        email=emailParam;
    }else{
        email=document.getElementById('email').value;
    }



    let  updateInformationObject = {
        FirstName,
        LastName,
        Username,
        email 
    };

    console.log("i changed accountinfo");
    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updateInformationObject )
    };

    let req= await fetch(`${URL}/user/information/`, requestOptions )
    .then(handleErrors)
    .then((res)=> {
        if (res.status == 200) {
            window.location.href = "accountInfo.html"
          }else {
            throw Error(response.statusText);
          }
    })
    .catch(error => console.log(error) );
    
}
// We need to set it up so when the submit changes button is clicked
// the changes get sent, start with the other information


// We should start by creating a function called putOtherInfo
// this will look very similar to the stuff in the submitRequest.js file
// Remember, if a field is left blank the update should just send the old
// value, like if the email form is left blank, the put request should 
// send the old email, I'd do this with an if statement
// We need to set it up so when the submit changes button is clicked
// the changes get sent, start with the other information




// After that function is created, we need one to update the password
// This should be a function again like the submitRequest.js file, but
// we need to send the old password and the new password in a put request


// var oldPassParam;
// var nerPassParam;

// (()=>{
//     let apiUrl = `${URL}/user/information/`;
//     console.log("i retrieved password ");
//     fetch(apiUrl)
//         .then((res) => res.json())
//         .then((data) => {
//             document.getElementById('oldPass').innerHTML = "oldPass: " + `${data.oldPass}`
//             oldPassParam = `${data.oldPass}`;
//         });
// })();
    

let changePassword = async () => {
    let oldPass= document.getElementById('oldPass').value;
    
    let newPass= document.getElementById('newPass').value;
   

    let updateInformationPasswordObject = {
        oldPass  ,
        newPass 
    };

    

    const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updateInformationPasswordObject)
        };
    
        let req= await fetch(`${URL}/user/information/password`, requestOptions)
        .then(handleErrors)
        .then((res)=> {
            if (res.status == 200) {
                console.log("i changed password");
                window.location.href = "accountInfo.html"
              }else {
                throw Error(response.statusText);
              }
        })
        .catch(error => console.log(error) );
    
    }





// Finally, we need to create event listeners that listen for clicks on
// the submit buttons, these have been put into variables already at the
// top, just create listeners that listen for a click and run the


// respective functions you created above.

console.log("get value of password" + newPass );

let updateInfo= document.getElementById('submitUpdateButton').addEventListener('click',changeAccountInfo);


let updatePassword= document.getElementById('submitPassButton').addEventListener('click',changePassword);

