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







// We need to set it up so when the submit changes button is clicked
// the changes get sent, start with the other information

// We should start by creating a function called putOtherInfo
// this will look very similar to the stuff in the submitRequest.js file
// Remember, if a field is left blank the update should just send the old
// value, like if the email form is left blank, the put request should 
// send the old email, I'd do this with an if statement







// After that function is created, we need one to update the password
// This should be a function again like the submitRequest.js file, but
// we need to send the old password and the new password in a put request




// Finally, we need to create event listeners that listen for clicks on
// the submit buttons, these have been put into variables already at the
// top, just create listeners that listen for a click and run the
// respective functions you created above.