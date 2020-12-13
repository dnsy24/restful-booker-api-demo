####Using fetch() API in devtools for endpoint validation
 - getting response status code

`fetch('https://restful-booker.herokuapp.com/booking').then(response => response.status).then(status => console.log(status))`

![status_code](https://user-images.githubusercontent.com/60865783/102002968-c40cc480-3ccf-11eb-874b-a60a89f33545.png)

 - getting all booking id's with GET 

`fetch('https://restful-booker.herokuapp.com/booking').then(response => response.json()).then(json => console.log(json))`

![booking](https://user-images.githubusercontent.com/60865783/102002960-b9522f80-3ccf-11eb-88f6-7054a90ab8e9.png)

 - getting single booking details by booking id with GET

`fetch('https://restful-booker.herokuapp.com/booking/1').then(response => response.json()).then(json => console.log(json))`

![booking_by_id](https://user-images.githubusercontent.com/60865783/102002965-bf481080-3ccf-11eb-919d-5a2e36220164.png)

 - getting a new auth token to use for access to the PUT and DELETE /booking

`fetch('https://restful-booker.herokuapp.com/auth', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({'username': 'admin', 'password': 'password123'})}).then(response => response.json()).then(json => console.log(json))`

![get_token](https://user-images.githubusercontent.com/60865783/102002970-cb33d280-3ccf-11eb-8b7d-0302375f38fa.png)
