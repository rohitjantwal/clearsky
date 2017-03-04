package io.egen.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.URI;
import io.egen.api.entity.Weather;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = URI.WEATHER)
@Api(tags = "weather")
public class WeatherController {

	private WeatherService service;

	public WeatherController(WeatherService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Find all weather readings", notes = "Returns full list of weather readings in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<Weather> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.ID)
	@ApiOperation(value = "Find weather reading by Id", notes = "Returns a weather reading by id if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}
	
	//implement
	@RequestMapping(method = RequestMethod.GET, value = URI.CITYLIST)
	@ApiOperation(value = "Find all cities that have weather readings", notes = "List all the cities whoes weather readings exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> listOfCities() {
		return service.listOfCities();
	}
	
	//implement
	@RequestMapping(method = RequestMethod.GET, value = URI.CITY)
	@ApiOperation(value = "Find latest weather for a given city", notes = "Gives the latest weather reading for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather latestWeatherOfCity(@PathVariable("city") String city) {
		return service.latestWeatherOfCity(city);
	}	

	//implement
	@RequestMapping(method = RequestMethod.GET, value = URI.PROPERTY)
	@ApiOperation(value = "Find latest weather property for a given city", notes = "Gives the latest weather property reading for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public HashMap<String,String> latestWeatherPropertyOfCity(@PathVariable("city") String city, @PathVariable("property") String property) {
		return service.latestWeatherPropertyOfCity(city, property);
	}	

	//implement and change uri
	@RequestMapping(method = RequestMethod.GET, value = "hourly/{city}")
	@ApiOperation(value = "Find average hourly weather for a given city", notes = "Gives the average hourly weather reading for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public HashMap<String,Integer> hourlyWeatherOfCity(@PathVariable("city") String city) {
		return service.hourlyWeatherOfCity(city);
	}	
		
	//implement and change uri
	@RequestMapping(method = RequestMethod.GET, value = "daily/{city}")
	@ApiOperation(value = "Find average daily weather for a given city", notes = "Gives the average daily weather reading for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public HashMap<String,Integer> dailyWeatherOfCity(@PathVariable("city") String city) {
		return service.dailyWeatherOfCity(city);
	}	
	//CRUD method
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Receive weather reading from sensor", notes = "Creates a weatehr reading in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather create(@RequestBody Weather weather) {
		return service.create(weather);
	}

	//update method just to satisfy all CRUD operations
	@RequestMapping(method = RequestMethod.PUT, value = URI.ID)
	@ApiOperation(value = "Update weather reading on the app", notes = "Updates an existing weather reading")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather update(@PathVariable("id") String id, @RequestBody Weather weather) {
		return service.update(id, weather);
	}
	//CRUD method
	@RequestMapping(method = RequestMethod.DELETE, value = URI.ID)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@ApiOperation(value = "Delete weather reading on app", notes = "Deletes an existing weather reading")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}