const URL = 'http://localhost:7000';


(()=>{
    let apiUrl = `${URL}/logout`;
    fetch(apiUrl)
    .catch(error => console.log(error) );
})();