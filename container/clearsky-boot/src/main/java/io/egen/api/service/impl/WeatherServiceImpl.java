package io.egen.api.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Weather;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.repository.WindRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	WeatherRepository repository;
	
	@Autowired
	WindRepository windrepo;

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
		return repository.findOne(id)
						 .orElseThrow(()->new NotFoundException("Record with id " + id + " not found."));
	}

	@Override
	@Transactional
	public Weather create(Weather weather) {
		windrepo.save(weather.getWind());
		return repository.save(weather);
	}

	@Override
	@Transactional
	public Weather update(String id, Weather weather) {
		windrepo.save(weather.getWind());
		repository.findOne(id)
				  .orElseThrow(()->new NotFoundException("Record with id " + id + " not found."));
		return repository.save(weather);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Weather existing = repository.findOne(id)
							.orElseThrow(()->new NotFoundException("Record with id " + id + " not found."));
		repository.delete(existing);
	}

	@Override
	public List<String> listOfCities() {
		if(repository.findCityList().isEmpty()){
			throw new NotFoundException("No weather readings found in the application.");
		};
		return repository.findCityList();
	}

	@Override
	public Weather latestWeatherOfCity(String city) {
		List<Weather> weather = repository.latestWeatherOfCity(city);
		if(weather.isEmpty()){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		return (weather.get(0));
		}

	@Override
	public HashMap<String,String> latestWeatherPropertyOfCity(String city, String property) {
		List<Weather> weatherlist = repository.latestWeatherOfCity(city);
		if(weatherlist.isEmpty()){
			throw new NotFoundException("Property "+ property +" for city " + city + " not found.");
		}
		Weather weather = weatherlist.get(0);
		HashMap<String,String> returnmap = new HashMap<String,String>();
		String str1 = "description";
		String str2 = "wind";
		String str3 = "humidity";
		String str4 = "pressure";
		String str5 = "temperature";
		String str6 = "timestamp";
		if(str1.equalsIgnoreCase(property)){
			returnmap.put(city + "-" + str1, weather.getDescription());
			return returnmap;
		}else if(str2.equalsIgnoreCase(property)){
			returnmap.put(city + "-" + str2, weather.getWind().toString());
			return returnmap;
		}else if(str3.equalsIgnoreCase(property)){
			returnmap.put(city + "-" + str3, String.valueOf(weather.getHumidity()));
			return returnmap;
		}else if(str4.equalsIgnoreCase(property)){
			returnmap.put(city + "-" + str4, String.valueOf(weather.getPressure()));
			return returnmap;
		}else if(str5.equalsIgnoreCase(property)){
			returnmap.put(city + "-" + str5, String.valueOf(weather.getTemperature()));
			return returnmap;
		}else if(str6.equalsIgnoreCase(property)){
			returnmap.put(city + "-" + str6, weather.getTimestamp());
			return returnmap;
		}
		else{
			throw new NotFoundException("Property "+ property +" for city " + city + " not found.");
		}
	}

	@Override
	public HashMap<String, Integer> hourlyWeatherOfCity(String city) {
		long htime =System.currentTimeMillis()-3600000;
		List<Weather> weathers = repository.hourlyWeatherOfCity(city, htime);
		if(weathers.isEmpty()){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		return avgproperty(weathers);
	}

	@Override
	public HashMap<String,Integer> dailyWeatherOfCity(String city) {
		long dtime =System.currentTimeMillis()-86400000;
		List<Weather> weathers = repository.dailyWeatherOfCity(city,dtime);
		if(weathers.isEmpty()){
			throw new NotFoundException("Record with city " + city + " not found.");
		}
		return avgproperty(weathers);
	}
	
	private HashMap<String,Integer> avgproperty(List<Weather> weathers){
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