package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class LocationCoords {
	
	String location;
	LatLng coords;
	
	
	public LocationCoords(String location, LatLng coords) {
		super();
		this.location = location;
		this.coords = coords;
	}


	public String getLocation() {
		return location;
	}


	public LatLng getCoords() {
		return coords;
	}
	
	
	
	
}