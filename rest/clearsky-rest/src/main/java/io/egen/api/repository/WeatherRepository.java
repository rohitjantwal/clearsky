package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherRepository {

	public List<Weather> findAll();
	
	public List<String> findCityList();

	public Optional<Weather> latestWeatherOfCity(String city);
		
	public Optional<List<Weather>> hourlyWeatherOfCity(String city);
	
	public Optional<List<Weather>> dailyWeatherOfCity(String city);
	
	public Optional<Weather> findOne(String id);

	public Weather create(Weather weather);

	public Weather update(Weather weather);

	public void delete(Weather weather);
}