package io.egen.api.service;

import java.util.HashMap;
import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public List<Weather> findAll();

	public Weather findOne(String id);
	
	public List<String> listOfCities();
	
	public Weather latestWeatherOfCity(String city);
	
	public String latestWeatherPropertyOfCity(String city, String property);
	
	public HashMap<String,Integer> hourlyWeatherOfCity(String city);
	
	public HashMap<String,Integer> dailyWeatherOfCity(String city);

	public Weather create(Weather weather);

	public Weather update(String id, Weather weather);

	public void delete(String id);
}