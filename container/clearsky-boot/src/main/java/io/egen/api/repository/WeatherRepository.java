package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import io.egen.api.entity.Weather;

public interface WeatherRepository extends Repository<Weather, String>{
	
	public List<Weather> findAll();
	
	public Optional<Weather> findOne(String id);
	
	//for update and insert
	public Weather save(Weather weather); 
	
	public void delete(Weather id);
	
	//can use different implementation
	@Query("SELECT DISTINCT(w.city) from Weather w")
	public List<String> findCityList();
	
	@Query("SELECT w FROM Weather w WHERE w.city=?1 ORDER BY w.unixTime desc")
	public List<Weather> latestWeatherOfCity(String city);
	
	@Query("SELECT w FROM Weather w WHERE city = ?1 AND unixTime > ?2")
	public List<Weather> hourlyWeatherOfCity(String city, long htime);
	
	@Query("SELECT w FROM Weather w WHERE city = ?1 AND unixTime > ?2")
	public List<Weather> dailyWeatherOfCity(String city, long dtime);

}