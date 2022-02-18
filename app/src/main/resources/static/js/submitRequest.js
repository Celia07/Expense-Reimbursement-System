
const URL = 'http://localhost:7000';

function handleErrors(response) {
    if (!response.ok) {
<<<<<<< HEAD
        throw Error(response.statusText);
    }
=======
        if (response.status == 403){
            window.location.href = "forbiddenError.html"
        }else if (response.status == 500){
            window.location.href = "internalServerError.html"
        }
        throw Error(response.statusText);
    } 
>>>>>>> f864a9f942a78921379ea6cf95ab56f0c899ecf9
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
<<<<<<< HEAD
            window.location.href = "http://localhost:7000/homePage.html"
=======
            window.location.href = "homePage.html"
>>>>>>> f864a9f942a78921379ea6cf95ab56f0c899ecf9
          }else {
            throw Error(response.statusText);
          }
    })
    .catch(error => console.log(error) );
    
    
}

<<<<<<< HEAD
=======
(()=>{
    document.getElementById('navbar').removeAttribute('hidden')
    document.getElementById('welcomeMessage').removeAttribute('hidden')
    document.getElementById('mainContainer').removeAttribute('hidden')
    document.getElementById('footer').removeAttribute('hidden')
    document.getElementById('submitRequest').removeAttribute('hidden')
    document.getElementById('accountInfo').removeAttribute('hidden')
})();

>>>>>>> f864a9f942a78921379ea6cf95ab56f0c899ecf9
let submitRequest = document.getElementById('submitRequest').addEventListener('click', post);



