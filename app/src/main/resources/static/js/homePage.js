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

let pendingTable = document.getElementById("pending")

function populatePendingTable(data){
    for(itm of data){
        var row = pendingTable.insertRow(-1);

        var idParam = `${itm.reimbId}`
        // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);

        // Add some text to the new cells:

        cell1.innerHTML = idParam;

        
        cell2.innerHTML = "$"+`${itm.amount}`;
        

        const milliseconds = itm.reimbSubmitted;

        const dateObject = new Date(milliseconds);

        const humanDateFormat = dateObject.toLocaleString("en-us", {month: "numeric",
        day: "numeric", year: "numeric"});

        cell3.innerHTML = humanDateFormat;

        if (itm.reimbDescription == null){
            cell4.innerHTML = 'No Description Given';
        } else {
            cell4.innerHTML = `${itm.reimbDescription}`;
        }
        
        cell5.innerHTML = `${itm.reimbAuthor.firstName}` + ' ' + `${itm.reimbAuthor.lastName}`;
        cell6.innerHTML = `${itm.reimbType}`;
    }


}

function populatePendingTableManager(data){
    for(itm of data){
        var row = pendingTable.insertRow(-1);

        var idParam = `${itm.reimbId}`
        // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);

        // Add some text to the new cells:

        
        cell1.outerHTML='<th scope = "row" style = `text-align:center`>' + "<a href='updateReimbursement.html' onClick = 'SetCookie(`reimbId`, `" + idParam + "`)'>" + 
        idParam + "</a>" + '</th>';

        // cell1.innerHTML = "<a href='http://localhost:7000/updateReimbursement.html' onClick = 'SetCookie(`reimbId`, `" + idParam + "`)'>" + 
        // idParam + "</a>"

        

        cell2.innerHTML = "$"+`${itm.amount}`;
        

        const milliseconds = itm.reimbSubmitted;

        const dateObject = new Date(milliseconds);

        const humanDateFormat = dateObject.toLocaleString("en-us", {month: "numeric",
        day: "numeric", year: "numeric"});

        cell3.innerHTML = humanDateFormat;

        if (itm.reimbDescription == null){
            cell4.innerHTML = 'No Description Given';
        } else {
            cell4.innerHTML = `${itm.reimbDescription}`;
        }
        
        cell5.innerHTML = `${itm.reimbAuthor.firstName}` + ' ' + `${itm.reimbAuthor.lastName}`;
        cell6.innerHTML = `${itm.reimbType}`;
    }




}



let resolvedTable = document.getElementById("resolved")


function populateResolvedTableManager(data){
    for(itm of data){
        var row = resolvedTable.insertRow(-1);
        // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        var cell8 = row.insertCell(7);

        // Add some text to the new cells:
        cell1.outerHTML='<th scope = "row" style = `text-align:center`>' + "$"+`${itm.amount}`+ '</th>';
        // cell1.innerHTML = "$"+`${itm.amount}`;
        

        const milliseconds = itm.reimbSubmitted;

        const dateObject = new Date(milliseconds);

        const humanDateFormat = dateObject.toLocaleString("en-us", {month: "numeric",
        day: "numeric", year: "numeric"});

        cell2.innerHTML = humanDateFormat;
        
        if (itm.reimbDescription == null){
            cell3.innerHTML = 'No Description Given';
        } else {
            cell3.innerHTML = `${itm.reimbDescription}`;
        }

        cell4.innerHTML = `${itm.reimbAuthor.firstName}` + ' ' + `${itm.reimbAuthor.lastName}`;
        cell5.innerHTML = `${itm.reimbType}`;
        cell6.innerHTML = `${itm.reimbStatus}`;

        if(cell6.innerHTML == "APPROVED"){
            row.style.color = "green";
        } else if (cell6.innerHTML == "DENIED"){
            row.style.color = "red";
        }

        cell7.innerHTML = `${itm.reimbResolver.firstName}` + ' ' + `${itm.reimbResolver.lastName}`;

        const milli2 = itm.reimbResolved;

        const dateObject2 = new Date(milli2);

        const humanDateFormat2 = dateObject2.toLocaleString("en-us", {month: "numeric",
        day: "numeric", year: "numeric"});

        cell8.innerHTML = humanDateFormat2;

    }



}


function populateResolvedTable(data){
    for(itm of data){
        var row = resolvedTable.insertRow(-1);
        // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        var cell8 = row.insertCell(7);

        // Add some text to the new cells:

        // cell1.outerHTML='<th scope = "row" style = `text-align:center`>' + "$"+`${itm.amount}`+ '</th>';

        cell1.innerHTML = "$"+`${itm.amount}`;
        

        const milliseconds = itm.reimbSubmitted;

        const dateObject = new Date(milliseconds);

        const humanDateFormat = dateObject.toLocaleString("en-us", {month: "numeric",
        day: "numeric", year: "numeric"});

        cell2.innerHTML = humanDateFormat;
        
        if (itm.reimbDescription == null){
            cell3.innerHTML = 'No Description Given';
        } else {
            cell3.innerHTML = `${itm.reimbDescription}`;
        }

        cell4.innerHTML = `${itm.reimbAuthor.firstName}` + ' ' + `${itm.reimbAuthor.lastName}`;
        cell5.innerHTML = `${itm.reimbType}`;
        cell6.innerHTML = `${itm.reimbStatus}`;

        if(cell6.innerHTML == "APPROVED"){
            row.style.color = "green";
        } else if (cell6.innerHTML == "DENIED"){
            row.style.color = "red";
        }

        cell7.innerHTML = `${itm.reimbResolver.firstName}` + ' ' + `${itm.reimbResolver.lastName}`;

        const milli2 = itm.reimbResolved;

        const dateObject2 = new Date(milli2);

        const humanDateFormat2 = dateObject2.toLocaleString("en-us", {month: "numeric",
        day: "numeric", year: "numeric"});

        cell8.innerHTML = humanDateFormat2;

    }





}

(()=>{
    let apiUrl = `${URL}/user/information`;
    fetch(apiUrl)

    .then(handleErrors)
        .then((res) => res.json())
        .then((data) => {
            document.getElementById('navbar').removeAttribute('hidden')
            document.getElementById('welcomeMessage').removeAttribute('hidden')
            document.getElementById('welcomeMessage').innerHTML = "Welcome Back " 
            + `${data.firstName}` + " " + `${data.lastName}`+"!";
            document.getElementById('mainContainer').removeAttribute('hidden')
            document.getElementById('footer').removeAttribute('hidden')
            if(data.userRole == "EMPLOYEE"){
                document.getElementById('submitRequest').removeAttribute("hidden");
                document.getElementById('accountInfo').removeAttribute("hidden");
                let apiUrl2 = `${URL}/user/user-pending`;
                fetch(apiUrl2)
                .then(handleErrors)

                .then((res) => res.json())
                .then((data) => populatePendingTable(data));
                let apiUrl3 = `${URL}/user/user-resolved`;
                fetch(apiUrl3)

                .then(handleErrors)
                .then((res) => res.json())
                .then((data) => populateResolvedTable(data));
            } else if (data.userRole == "MANAGER"){
                document.getElementById('viewEmployees').removeAttribute("hidden");
                let apiUrl2 = `${URL}/requests/requests-pending`;
                fetch(apiUrl2)
                .then(handleErrors)
                .then((res) => res.json())
                .then((data) => populatePendingTableManager(data));
                let apiUrl3 = `${URL}/requests/requests-resolved`;
                fetch(apiUrl3)
                .then(handleErrors)
                .then((res) => res.json())
                .then((data) => populateResolvedTableManager(data));
            }
        })
        .catch(error => console.log(error) );
})();




// (()=>{
//     let apiUrl = `${URL}/user/user-pending`;
//     fetch(apiUrl)
//         .then((res) => res.json())
//         .then((data) => populatePendingTable(data));
// })();

// (()=>{
//     let apiUrl = `${URL}/user/user-resolved`;
//     fetch(apiUrl)
//         .then((res) => res.json())
//         .then((data) => populateResolvedTable(data));
// })();