package io.egen.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Weather;
import io.egen.api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Weather> findAll() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		return query.getResultList();
	}

	@Override
	public Weather findByEmail(String email) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findCityList", Weather.class);
		query.setParameter("pEmail", email);
		List<Weather> users = query.getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Weather findOne(String id) {
		return em.find(Weather.class, id);
	}

	@Override
	public Weather create(Weather weather) {
		em.persist(weather.getWind());
		em.persist(weather);
		return weather;
	}

	@Override
	public Weather update(Weather weather) {
		return em.merge(weather);
	}

	@Override
	public void delete(Weather weather) {
		em.remove(weather);
	}


	@Override
	public List<String> findCityList() {
		TypedQuery<String> query = em.createNamedQuery("Weather.findCityList", String.class);
		return query.getResultList();
	}


	@Override
	public Weather latestWeatherOfCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findCityWeather", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> weather = query.getResultList();
		if(weather.size() == 0){
			return null;
		}
		return weather.get(0);
	}


	@Override
	public Weather latestWeatherPropertyOfCity(String city, String property) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findCityWeather", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> weather = query.getResultList();
		if(weather.size() == 0){
			return null;
		}
		return weather.get(0);
	}


	@Override
	public List<Weather> hourlyWeatherOfCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findCityWeatherHourlyList", Weather.class);
		query.setParameter("pCity", city);
		query.setParameter("hUnixTime", System.currentTimeMillis()-3600000);
		List<Weather> weather = query.getResultList();
		if(weather.size() == 0){
			return null;
		}
		return weather;
	}


	@Override
	public List<Weather> dailyWeatherOfCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findCityWeatherHourlyList", Weather.class);
		query.setParameter("pCity", city);
		query.setParameter("dUnixTime", System.currentTimeMillis()-86400000);
		List<Weather> weather = query.getResultList();
		if(weather.size() == 0){
			return null;
		}
		return weather;
	}


	
}