
const URL = 'http://localhost:7000';

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
    });
    
    
    
    let res = await req.json();
    if (req.status == 200){
        console.log(res);
        // window.location.href = "C:\\Users\\brygu\\Desktop\\Java Training Stuff Revature\\HTML Stuff\\AJAX Handson\\pokemon.html"
    } 
    // console.log(res);

    console.log(req.headers.get('id'));
    // console.log(req.headers.get("loggedIn"));
    // console.log(req.headers.get("userRole"));
    
    
    
}

let loginSubmit = document.getElementById('login').addEventListener('click', post);



