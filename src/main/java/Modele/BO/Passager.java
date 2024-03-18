package Modele.BO;

public class Passager
{
    private String Nom;
    private double Longitude;
    private double Latitude;


    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public Passager(String nom, double longitude, double latitude) {
        Nom = nom;
        Longitude=longitude;
        Latitude=latitude;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
