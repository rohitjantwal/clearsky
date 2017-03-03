package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherRepository {

	public List<Weather> findAll();
	
	public List<String> findCityList();

	public Weather latestWeatherOfCity(String city);
	
	public Weather latestWeatherPropertyOfCity(String city, String property);
	
	public List<Weather> hourlyWeatherOfCity(String city);
	
	public List<Weather> dailyWeatherOfCity(String city);
	
	public Weather findOne(String id);

	public Weather findByEmail(String email);

	public Weather create(Weather weather);

	public Weather update(Weather weather);

	public void delete(Weather weather);
}