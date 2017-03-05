package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	//to get the whole list
	@NamedQuery(name="Weather.findAll", query="SELECT w FROM Weather w ORDER BY w.unixTime"),
	//1. to get unique list of cities
	@NamedQuery(name="Weather.findCityList", query="SELECT DISTINCT(w.city) from Weather w"),
	//2. to get latest the weather of city
	@NamedQuery(name="Weather.findCityWeather", query="SELECT w FROM Weather w WHERE w.city=:pCity ORDER BY w.unixTime desc"),	
	//3. use same as above for weather property and extract property from object

	//4. to get daily average. Use these objects and calculate average in service layer
	@NamedQuery(name="Weather.findCityWeatherHourlyList", query = "SELECT w FROM Weather w WHERE city = :pCity AND unixTime > :hUnixTime"),
	//5. to get daily average. Use these objects and calculate average in service layer.
	@NamedQuery(name="Weather.findCityWeatherDailyList", query = "SELECT w FROM Weather w WHERE city = :pCity AND unixTime > :dUnixTime")

})
public class Weather {

	@Id
	private String id;
	private long unixTime;
	private String city;
	private String description;
	private int humidity;
	private int pressure;
	private int temperature;
	private String timestamp;
	
	@OneToOne
	private Wind wind;
	
	public Weather() {
		this.id = UUID.randomUUID().toString();
		this.unixTime = System.currentTimeMillis();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", unixTime=" + unixTime + ", city=" + city + ", description=" + description
				+ ", humidity=" + humidity + ", pressure=" + pressure + ", temperature=" + temperature + ", timestamp="
				+ timestamp + ", wind=" + wind + "]";
	}
	
}
