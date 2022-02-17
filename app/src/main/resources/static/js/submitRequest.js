
const URL = 'http://localhost:7000';

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

let post = async () => {
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let reimbType = document.getElementById('reimbType').value;
    

    let createReimbObj = {
        amount,
        description,
        reimbType
    };


    await fetch(`${URL}/user/submit`, {
        method: 'POST',
        body: JSON.stringify(createReimbObj)
    })
    .then(handleErrors)
    .then((res)=> {
        if (res.status == 200) {
            window.location.href = "http://localhost:7000/homePage.html"
          }else {
            throw Error(response.statusText);
          }
    })
    .catch(error => console.log(error) );
    
    
}

let submitRequest = document.getElementById('submitRequest').addEventListener('click', post);


