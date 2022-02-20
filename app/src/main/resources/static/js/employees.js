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


function SetCookie(c_name,value)
	{
		document.cookie=c_name+ "=" + value;
	}

let employeeTable = document.getElementById("employees")

function populateEmployeeTable(data){
    for(itm of data){
        var row = employeeTable.insertRow(-1);
        // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);

        var userParam = `${itm.username}`

        // Add some text to the new cells:
        cell1.innerHTML = `${itm.userId}`;

        // <a href="#" onClick="SetCookie('COOKIENAME','COOKIEVALUE','1')"></a>
        cell2.innerHTML = `${itm.firstName}` + ' ' + `${itm.lastName}`


        cell3.innerHTML = "<a href='specificUser.html' onClick = 'SetCookie(`username`, `" + userParam + "`)'>" + 
        userParam + "</a>";
        
        cell4.innerHTML = `${itm.email}`;


    }

}

(()=>{
    let apiUrl = `${URL}/people`;
    fetch(apiUrl)
    .then(handleErrors)
        .then((res) => res.json())
        .then((data) => {
            document.getElementById('navbar').removeAttribute('hidden')
            document.getElementById('welcomeMessage').removeAttribute('hidden')
            document.getElementById('mainContainer').removeAttribute('hidden')
            document.getElementById('footer').removeAttribute('hidden')
            document.getElementById('viewEmployees').removeAttribute('hidden')
            populateEmployeeTable(data);
        })
        .catch(error => console.log(error) );
})();