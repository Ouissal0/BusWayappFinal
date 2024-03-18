package Vue;
import Modele.BO.*;
import Controlleur.SystemeRes;

import java.time.LocalDate;
import java.time.LocalTime;


public class Main {

    public static void main(String[] args) {
        // Création d'un système de réservation
        SystemeRes systemeRes = new SystemeRes();

        // Assume we already know the latitude and longitude for the stations
        double lat1 = 12.3456, lon1 = 45.1234; // Example coordinates for Station 1
        double lat4 = 9.6789, lon4 = 48.4567;  // Example coordinates for Station 4
        double lat7 = 6.9012, lon7 = 51.7890;  // Example coordinates for Station 7
        double lat2 = 11.4567, lon2 = 46.2345; // Example coordinates for Station 2
        double lat3 = 10.5678, lon3 = 47.3456; // Example coordinates for Station 3

        Bus bus = new Bus("123ABC", 50);
        Bus bus1 = new Bus("123ABC1", 50);
        Conducteur conducteur = new Conducteur("John", "123456789");
        Conducteur conducteur1 = new Conducteur("Jack", "126456686");
        LocalDate dateVoyage = LocalDate.of(2024, 3, 16);
        LocalDate dateVoyage1 = LocalDate.of(2024, 3, 16);
        Station stationDepart = new Station("Station 1", lat1, lon1);
        Station stationArrivee = new Station("Station 4", lat4, lon4);
        Station stationDepart1 = new Station("Station 4", lat4, lon4);
        Station stationArrivee1 = new Station("Station 7", lat7, lon7);

        Voyage voyage = new Voyage(1, dateVoyage, conducteur, bus);
        Voyage voyage1 = new Voyage(2, dateVoyage1, conducteur1, bus1);
        voyage.setStationDepart(stationDepart);
        voyage.setStationArr(stationArrivee);

        voyage1.setStationDepart(stationDepart1);
        voyage1.setStationArr(stationArrivee1);

        Station station2 = new Station("Station 2", lat2, lon2);
        Station station3 = new Station("Station 3", lat3, lon3);

        Arret arretDepart = new Arret(voyage, stationDepart, LocalTime.of(8, 0));
        Arret arretArrivee = new Arret(voyage, stationArrivee, LocalTime.of(8, 30));
        voyage.getArrets().add(arretDepart);
        voyage.getArrets().add(arretArrivee);

        Arret arret2 = new Arret(voyage, station2, LocalTime.of(8, 5));
        Arret arret3 = new Arret(voyage, station3, LocalTime.of(8, 15));
        voyage.getArrets().add(arret2);
        voyage.getArrets().add(arret3);

        Arret arretDepart1 = new Arret(voyage1, stationDepart1, LocalTime.of(9, 0));
        Arret arretArrivee1 = new Arret(voyage1, stationArrivee1, LocalTime.of(9, 35));
        voyage1.getArrets().add(arretDepart1);
        voyage1.getArrets().add(arretArrivee1);

        Arret arret21 = new Arret(voyage1, station2, LocalTime.of(9, 25));
        Arret arret31 = new Arret(voyage1, station3, LocalTime.of(9, 15));
        voyage1.getArrets().add(arret31);
        voyage1.getArrets().add(arret21);

        systemeRes.addVoyage(voyage);
        systemeRes.addVoyage(voyage1);

        systemeRes.afficherVoyages();
        systemeRes.ajouterRelationsArrets();
    }
}
