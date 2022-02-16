const URL = 'http://localhost:7000';

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
        cell3.innerHTML = "<a href='http://localhost:7000/specificUser.html' onClick = 'SetCookie(`username`, `" + userParam + "`)'>" + 
        userParam + "</a>";
        
        cell4.innerHTML = `${itm.email}`;


    }

}

(()=>{
    let apiUrl = `${URL}/people`;
    fetch(apiUrl)
        .then((res) => res.json())
        .then((data) => {
            populateEmployeeTable(data);
        });
})();
