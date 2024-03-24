package Modele.BO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import  java.time.LocalTime;

public class Voyage {
	private int NumV;
	private Bus B;
	private Station StationDepart;
	private Station StationArr;
	private LocalDate DateV;
	private Conducteur C;
	private List<Arret> Arrets;
	private LocalTime heureDepart;  // Supposant que vous voulez ajouter l'heure de départ du voyage
	private LocalTime heureArrivee;  // Supposant que vous voulez ajouter l'heure d'arrivée du voyage

	public Voyage(int numV, Bus b, LocalDate dateV, Conducteur c) {
		NumV = numV;
		DateV = dateV;
		C = c;
		B = b;
		Arrets = new ArrayList<>();
	}

	// Getters et setters pour heureDepart et heureArrivee
	public LocalTime getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(LocalTime heureDepart) {
		this.heureDepart = heureDepart;
	}

	public LocalTime getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(LocalTime heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	// Les autres getters et setters...

	public Station getStationDepart() {
		return StationDepart;
	}

	public void setStationDepart(Station stationDepart) {
		StationDepart = stationDepart;
	}

	public Station getStationArr() {
		return StationArr;
	}

	public void setStationArr(Station stationArr) {
		StationArr = stationArr;
	}

	@Override
	public String toString() {
		return "Voyage{" +
				"NumV=" + NumV +
				", Bus=" + B.getMatricule() +
				", Capacité=" + B.getNbrPlacesLimite() +
				", StationDepart=" + StationDepart.getStation() +
				", LatitudeDepart=" + StationDepart.getLatitude() +
				", LongitudeDepart=" + StationDepart.getLongitude() +
				", StationArr=" + (StationArr != null ? StationArr.getStation() : "Non défini") +
				", LatitudeArr=" + (StationArr != null ? StationArr.getLatitude() : "Non défini") +
				", LongitudeArr=" + (StationArr != null ? StationArr.getLongitude() : "Non défini") +
				", DateV=" + DateV +
				", Conducteur=" + (C != null ? C.getNom() : "Non défini") +
				'}';
	}

	public List<Arret> getArrets() {
		return Arrets;
	}

	public void setArrets(List<Arret> arrets) {
		Arrets = arrets;
	}

	public int getNumV() {
		return NumV;
	}

	public void setNumV(int numV) {
		NumV = numV;
	}

	public Bus getB() {
		return B;
	}

	public void setB(Bus b) {
		B = b;
	}

	public LocalDate getDateV() {
		return DateV;
	}

	public void setDateV(LocalDate dateV) {
		DateV = dateV;
	}

	public Conducteur getC() {
		return C;
	}

	public void setC(Conducteur c) {
		C = c;
	}
}
