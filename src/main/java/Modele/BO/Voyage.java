package Modele.BO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class Voyage
{
	private int NumV;
	private  Bus B;
	private Station StationDepart;
	private Station StationArr;
	private LocalDate DateV;
	private Conducteur C;
	private List<Arret> Arrets;

	public Voyage(int numV, LocalDate dateV, Conducteur c,Bus b) {
		NumV = numV;
		DateV = dateV;
		C = c;
		B=b;
		Arrets=new ArrayList<Arret>();
	}

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

	public Bus getB()
	{
		return B;
	}

	public void setB(Bus b)
	{
		B = b;
	}

	public int getNumV()
	{
		return NumV;
	}

	public void setNumV(int numV)
	{
		NumV = numV;
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

	public List<Arret> getArrets() {
		return Arrets;
	}

	public void setArrets(List<Arret> arrets) {
		Arrets = arrets;
	}

	// Méthode pour afficher toutes les informations du voyage
	public void afficherVoyage() {
		System.out.println("Numéro de voyage: " + NumV);
		System.out.println("Date du voyage: " + DateV);
		System.out.println("Conducteur: " + C.getNom()); // Supposant que la classe Conducteur a une méthode getNom()
		System.out.println("Bus: " + B.getMatricule()); // Supposant que la classe Bus a une méthode getMatricule()
		System.out.println("Station de départ: " + StationDepart.getStation()); // Supposant que la classe Station a une méthode getNom()
		System.out.println("Station d'arrivée: " + StationArr.getStation()); // Supposant que la classe Station a une méthode getNom()

		System.out.println("Arrets:");
		for (Arret arret : Arrets) {
			System.out.println("Heure: " + arret.getHeureArret() + ", Station: " + arret.getS().getStation());
		}
	}


}
