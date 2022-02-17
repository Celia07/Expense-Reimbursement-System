
const URL = 'http://localhost:7000';

function handleErrors(response) {
    if (!response.ok) {
        if (response.status == 403){
            window.location.href = "forbiddenError.html"
        }else if (response.status == 500){
            window.location.href = "internalServerError.html"
        }
        document.getElementById('badLogin').removeAttribute('hidden')
        throw Error(response.statusText);
    } 
    return response;
}


let post = async () => {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    

    let loginObj = {
        username,
        password
    };


    let req = await fetch(`${URL}/login`, {
        method: 'POST',
        // headers: {
        //     'Content-Type': 'application/json',
        //     'Access-Control-Expose-Headers': 'id',
        //     'Access-Control-Expose-Headers': 'loggedIn',
        //     'Access-Control-Expose-Headers': 'userRole'
        // },
        body: JSON.stringify(loginObj)
    })
    .then(handleErrors)
    .then((res)=> {
        if (res.status == 200) {
            return res.json();
          }else {
            throw Error(response.statusText);
          }
    })
    .then((res)=> {
        console.log(res)
        window.location.href = "http://localhost:7000/homePage.html"
    })
    .catch(error => console.log(error) );
    
    
    
    // if (req.status == 200){
    //     let res = await req.json();
    //     console.log(res);
    //     // window.location.href = "C:\\Users\\brygu\\Desktop\\Java Training Stuff Revature\\HTML Stuff\\AJAX Handson\\pokemon.html"
    // } else {
    //     document.getElementById('badLogin').style.display = 'block';
    // }
    // console.log(res);

    // console.log(req.headers.get('id'));
    // console.log(req.headers.get("loggedIn"));
    // console.log(req.headers.get("userRole"));
    
    
    
}

let loginSubmit = document.getElementById('login').addEventListener('click', post);



