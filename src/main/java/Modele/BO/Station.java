package Modele.BO;

import java.util.List;

public class Station {

	private String Station;
	private double longitude;
	private double latitude;
	private int estFinale; // true si c'est une station finale, false sinon
	private List<Voyage> VoyagesDepart;
	private List<Voyage> VoyagesArrives;


	public Station(String station, double longitude, double latitude, int estFinale) {
		super();
		Station = station;
		this.longitude = longitude;
		this.latitude = latitude;
		this.estFinale = estFinale;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public int isEstFinale() {
		return estFinale;
	}


	public void setEstFinale(int estFinale) {
		this.estFinale = estFinale;
	}


	public String getStation() {
		return Station;
	}

	public void setStation(String station) {
		Station = station;
	}

	@Override
	public String toString() {
		String s = Station + "\n" + latitude + "\n" + longitude;
		return s;
	}
}
