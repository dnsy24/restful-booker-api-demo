####Using fetch() API in devtools for endpoint validation
 - getting response status code

`fetch('https://restful-booker.herokuapp.com/booking').then(response => response.status).then(status => console.log(status))
`
 - getting all booking id's with GET 

`fetch('https://restful-booker.herokuapp.com/booking').then(response => response.json()).then(json => console.log(json))`

 - getting single booking details by booking id with GET

`fetch('https://restful-booker.herokuapp.com/booking/1').then(response => response.json()).then(json => console.log(json))`

 - getting a new auth token to use for access to the PUT and DELETE /booking

`fetch('https://restful-booker.herokuapp.com/auth', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({'username': 'admin', 'password': 'password123'})}).then(response => response.json()).then(json => console.log(json))`