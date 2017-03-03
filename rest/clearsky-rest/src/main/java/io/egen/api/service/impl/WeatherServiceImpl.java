package io.egen.api.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Weather;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	WeatherRepository repository;
	
	//@Autowired
	//WindRepository windrepo;

	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Weather> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findOne(String id) {
		Weather existing = repository.findOne(id);
		if(existing == null) {
			throw new NotFoundException("Record with id " + id + " not found.");
		} 
		return existing;
//		return repository.findOne(id)
//				.orElseThrow(() -> new NotFoundException("Weather reading with id " + id + " does not exist"));
	}

	@Override
	@Transactional
	public Weather create(Weather weather) {
		return repository.create(weather);
	}

	@Override
	@Transactional
	public Weather update(String id, Weather weather) {
		Weather existing = repository.findOne(id);
		if (existing == null) {
			throw new NotFoundException("Record with id:" + id + " not found");
		}
		return repository.update(weather);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Weather existing = repository.findOne(id);
		if (existing == null) {
			throw new NotFoundException("Record with id:" + id + " not found.");
		}
		repository.delete(existing);
	}

	@Override
	public List<String> listOfCities() {
		if(repository.findCityList().isEmpty()){
			throw new NotFoundException("No sensor readings found in the application.");
		};
		return repository.findCityList();
	}

	@Override
	public Weather latestWeatherOfCity(String city) {
		Weather existing = repository.latestWeatherOfCity(city);
		if(existing == null){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		return repository.latestWeatherOfCity(city);
	}

	@Override
	public String latestWeatherPropertyOfCity(String city, String property) {
		Weather existing = repository.latestWeatherPropertyOfCity(city,property);
//		for (Field field : existing.getClass().getDeclaredFields())
//		{
//			String temp = existing.getClass().getDeclaredFields().getClass().toString();
//			if(existing.getClass().getDeclaredFields().getClass().toString() == property){
//			
//			}
//		}
		if(existing == null){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		return null;
		//return repository.latestWeatherPropertyOfCity(city,property);
	}

	@Override
	public HashMap<String, Integer> hourlyWeatherOfCity(String city) {
		List<Weather> weathers = repository.hourlyWeatherOfCity(city);
		if(weathers == null){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		int humidityavg=0;
		int pressureavg=0;
		int tempavg=0;
		for(Weather weather: weathers){
			humidityavg += weather.getHumidity();
			pressureavg += weather.getPressure();
			tempavg += weather.getTemperature();
		}
		humidityavg =(humidityavg/weathers.size());
		pressureavg =(pressureavg/weathers.size());
		tempavg =(tempavg/weathers.size());
		
		HashMap<String,Integer> avgmap = new HashMap<String,Integer>();
		avgmap.put("Average humidity", humidityavg);
		avgmap.put("Average pressure", pressureavg);
		avgmap.put("Average temperature", tempavg);
		return avgmap;
	}

	@Override
	public HashMap<String,Integer> dailyWeatherOfCity(String city) {
		List<Weather> weathers = repository.hourlyWeatherOfCity(city);
		if(weathers == null){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		int humidityavg=0;
		int pressureavg=0;
		int tempavg=0;
		for(Weather weather: weathers){
			humidityavg += weather.getHumidity();
			pressureavg += weather.getPressure();
			tempavg += weather.getTemperature();
		}
		humidityavg =(humidityavg/weathers.size());
		pressureavg =(pressureavg/weathers.size());
		tempavg =(tempavg/weathers.size());
		
		HashMap<String,Integer> avgmap = new HashMap<String,Integer>();
		avgmap.put("Average humidity", humidityavg);
		avgmap.put("Average pressure", pressureavg);
		avgmap.put("Average temperature", tempavg);
		return avgmap;
	}
}