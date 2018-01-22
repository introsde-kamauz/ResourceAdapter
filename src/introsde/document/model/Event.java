package introsde.document.model;

//import introsde.document.dao.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Event implements Serializable{
	
    private String idEvent;
	private String name; // in kg
	private String description; // in m
	private String type;
	private String startdate;
	private double latitude;
	private double longitude;
	private String venue;
	private String city;
	private String duration;
	private String distance;
	private String precipitations;
	private Double max_temperature;
	private Double min_temperature;

	public Event(String name, String description, String place, String startdate,
			double latitude, double longitude, String venue, String city, String precipitations, Double max_temperature, Double min_temperature) {
		this.name = name;
		this.description = description;
		this.startdate = startdate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.venue = venue;
		this.city = city;
		this.precipitations = precipitations;
		this.max_temperature = max_temperature;
		this.min_temperature = min_temperature;
		
	}

	public Event() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type=type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getVenue() {
		return this.venue;
	}
	
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getIdEvent() {
		return idEvent;
	}
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getPrecipitations() {
		return precipitations;
	}

	public void setPrecipitations(String precipitations) {
		this.precipitations = precipitations;
	}

	public Double getMax_temperature() {
		return max_temperature;
	}

	public void setMax_temperature(Double max_temperature) {
		this.max_temperature = max_temperature;
	}

	public Double getMin_temperature() {
		return min_temperature;
	}

	public void setMin_temperature(Double min_temperature) {
		this.min_temperature = min_temperature;
	}
		
}