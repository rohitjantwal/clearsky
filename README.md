# clearsky-seed	
seed for the J2EE training project: clearsky 	

## directory structure:	
**`rest`** [*module-rest*]: contains SpringMVC based REST API	
**`container`** [*module-container*]: contains SpringBoot version of the same API with Docker configuration

### For the final module submission [*module-complete*], update following urls for your app:    
**`EC2 Jenkins URL`**: `http://ec2-54-175-205-175.compute-1.amazonaws.com:8080`   
**`API URL`**: `http://rohitjantwal.com/api/weather`    
**`Swagger URL`**: `http://rohitjantwal.com/api/swagger-ui.html`  


| REQUEST METHOD  |  URL (with example) |  Description  |  
| ------------- |:-------------:| -----:|
| GET  |  http://rohitjantwal.com/api/weather  |  Find all weather readings | 
| POST  |  http://rohitjantwal.com/api/weather  |  Receive weather reading from sensor  | 
| GET  |  http://rohitjantwal.com/api/weather/city/Chicago  |  Find latest weather for a given city | 
| GET  |  http://rohitjantwal.com/api/weather/citylist  |  Find all cities that have weather readings | 
| GET  |  http://rohitjantwal.com/api/weather/daily/Chicago  |  Find average daily weather for a given city | 
| GET  |  http://rohitjantwal.com/api/weather/hourly/Chicago |  Find average hourly weather for a given city | 
| GET  |  http://rohitjantwal.com/api/weather/{id}  |  Find weather reading by Id | 
| PUT  |  http://rohitjantwal.com/api/weather/{id}  |  Update weather reading on the app | 
| GET  |  http://rohitjantwal.com/api/weather/humidity/Chicago  |  Find latest weather property for a given city | 
| DELETE  |  http://rohitjantwal.com/api/weather/{id}  |  Delete weather reading on app | 