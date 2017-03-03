package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@NamedQueries({
//	@NamedQuery(name="Weather.findAll", query="SELECT w FROM Weather w ORDER BY w.firstName"),
//	@NamedQuery(name="Weather.findByEmail", query="SELECT w FROM Weather w where w.email=:pEmail")
//})
public class Wind {
	
	@Id
	private String id;
	private double speed;
	private int degree;
	
	public Wind(){
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "Wind [id=" + id + ", speed=" + speed + ", degree=" + degree + "]";
	}

	
}
