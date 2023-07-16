package it.polito.tdp.nyc.model;

public class LocationNumberNeighbour {
	
	private String location;
	private int numeroVicini;
	
	
	public LocationNumberNeighbour(String location, int numeroVicini) {
		super();
		this.location = location;
		this.numeroVicini = numeroVicini;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getNumeroVicini() {
		return numeroVicini;
	}


	public void setNumeroVicini(int numeroVicini) {
		this.numeroVicini = numeroVicini;
	}
	
	

}
