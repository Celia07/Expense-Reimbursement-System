const URL = 'http://localhost:7000';
const USERNAME = getCookie('reimbId');


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

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
        c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
        }
    }
    return "";
}

let reimbId;

(()=>{
    let apiUrl = `${URL}/reimbursement-by-id`;
    fetch(apiUrl)
<<<<<<< HEAD
=======
    .then(handleErrors)
    document.getElementById('navbar').removeAttribute('hidden')
    document.getElementById('welcomeMessage').removeAttribute('hidden')
    document.getElementById('mainContainer').removeAttribute('hidden')
    document.getElementById('footer').removeAttribute('hidden')
    document.getElementById('viewEmployees').removeAttribute('hidden')
>>>>>>> f864a9f942a78921379ea6cf95ab56f0c899ecf9
        .then((res) => res.json())
        .then((data) => {
            document.getElementById('cell1').innerHTML = "Reimbursement Id: " + `${data.reimbId}`
            reimbId = `${data.reimbId}`;
            document.getElementById('cell2').innerHTML = "Amount: $" + `${data.amount}`
            if (data.reimbDescription == null){
                document.getElementById('cell3').innerHTML = 'Description: No Description Given';
            } else {
                document.getElementById('cell3').innerHTML = "Description: " + `${data.reimbDescription}`;
            }
            document.getElementById('cell4').innerHTML = "Type: " + `${data.reimbType}`
            document.getElementById('cell5').innerHTML = "Employee: " + `${data.reimbAuthor.firstName}` + " " + `${data.reimbAuthor.lastName}`
        });
})();

let put = async () => {
    let reimbStatus = document.getElementById('reimbStatus').value;
    

    let updateReimbObj = {
        reimbId,
        reimbStatus
    };


    await fetch(`${URL}/requests/requests-pending`, {
        method: 'PUT',
        body: JSON.stringify(updateReimbObj)
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

let updateRequest = document.getElementById('updateRequest').addEventListener('click', put);