const URL = 'http://localhost:7000';
const USERNAME = getCookie('reimbId');

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


(()=>{
    let apiUrl = `${URL}/reimbursement-by-id`;
    fetch(apiUrl)
        .then((res) => res.json())
        .then((data) => {
            document.getElementById('cell1').innerHTML = "Reimbursement Id: " + `${data.reimbId}`
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