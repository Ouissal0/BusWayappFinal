package Modele.BO;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

public class BusWay {
    private List<Bus> LesBus;
    private List<Station> Stations;


    public BusWay() {
        this.LesBus = new ArrayList<>();
        this.Stations = new ArrayList<>();

        // Récupération des bus depuis Neo4j
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12398765"))) {
            try (Session session = driver.session()) {
                String query = "MATCH (bus:BUS) RETURN bus.matricule AS matricule, bus.capacite AS capacite,bus.nom as nom";
                Result result = session.run(query);
                while (result.hasNext()) {
                    Record record = result.next();
                    String matricule = record.get("matricule").asString();
                    int capacite = record.get("capacite").asInt();
                    String nom=record.get("nom").asString();
                    Bus bus = new Bus(matricule, capacite,nom);
                    LesBus.add(bus);
                }
            }
        }

        // Récupération des stations depuis Neo4j
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12398765"))) {
            try (Session session = driver.session()) {
                String query = "MATCH (station:STATION) RETURN station.nom AS nom, station.longitude AS longitude, station.latitude AS latitude, station.end AS estFinale";
                Result result = session.run(query);
                while (result.hasNext()) {
                    Record record = result.next();
                    String nom = record.get("nom").asString();
                    double longitude = record.get("longitude").asDouble();
                    double latitude = record.get("latitude").asDouble();

                    // Convertir l'entier en booléen
                    int estFinale = record.get("estFinale").asInt();

                    Station station = new Station(nom, longitude, latitude, estFinale);
                    Stations.add(station);
                }
            }
        }
    }

    public void afficherListeBus() {
        System.out.println("Liste des bus :");
        for (Bus bus : LesBus) {
            System.out.println("Matricule : " + bus.getMatricule() + ", Capacité : " + bus.getNbrPlacesLimite());
        }
    }

    public void afficherListeStations() {
        System.out.println("\nListe des stations :");
        for (Station station : Stations) {
            System.out.println("Nom : " + station.getStation() + ", Longitude : " + station.getLongitude() + ", Latitude : " + station.getLatitude() + ", Est finale : " + station.isEstFinale());
        }
    }

    public static void main(String[] args) {
        BusWay busWay = new BusWay();
        busWay.afficherListeBus();
        busWay.afficherListeStations();
    }
}
