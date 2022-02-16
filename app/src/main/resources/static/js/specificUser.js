const URL = 'http://localhost:7000';
const USERNAME = getCookie('username');

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

        cell1.innerHTML = "<a href='http://localhost:7000/updateReimbursement.html' onClick = 'SetCookie(`reimbId`, `" + idParam + "`)'>" + 
        idParam + "</a>"

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
    let apiUrl2 = `${URL}/${USERNAME}/pending`;
    fetch(apiUrl2)
    .then((res) => res.json())
    .then((data) => populatePendingTable(data));
    let apiUrl3 = `${URL}/${USERNAME}/resolved`;
    fetch(apiUrl3)
    .then((res) => res.json())
    .then((data) => populateResolvedTable(data));
})();