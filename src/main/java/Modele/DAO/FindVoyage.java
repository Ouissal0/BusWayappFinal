

package Modele.DAO;

import ConnectionDb.ConnectionDb;
import Modele.BO.Bus;
import Modele.BO.Station;
import Modele.BO.Voyage;
import org.neo4j.driver.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

    public class FindVoyage {
       /*
        private Driver driver;


        public FindVoyage() {
            ConnectionDb neo4j = new ConnectionDb();
            driver = neo4j.getDriver();
        }

        public List<Voyage> findAvailableVoyages(String departureStation, String arrivalStation) {


            List<Voyage> voyages = new ArrayList<>();

            try (Session session = driver.session()) {
                String query = """
                MATCH (departure:STATION {nom: $departureStation}),
                      (arrival:STATION {nom: $arrivalStation}),
                      (bus:BUS)-[arret_depart:ARRET]->(departure),
                      (bus)-[arret_arrivee:ARRET]->(arrival)
                WHERE bus.capacite > 0 AND arret_depart.heure < arret_arrivee.heure
                RETURN DISTINCT bus.matricule AS Matricule,
                                arret_depart.idVoyage AS VoyageID,
                                arret_depart.heure AS HeureDepart,
                                arret_arrivee.heure AS HeureArrivee,
                                departure.latitude AS LatitudeDepart,
                                departure.longitude AS LongitudeDepart,
                                arrival.latitude AS LatitudeArr,
                                arrival.longitude AS LongitudeArr,
                                bus.capacite AS capacite,
                                departure.end As endSDepp,
                                arrival.end As endSArr
                LIMIT 100;
            """;

                Result result = session.run(query, Values.parameters("departureStation", departureStation, "arrivalStation", arrivalStation));
                while (result.hasNext()) {
                    var record = result.next();
                    String matricule = record.get("Matricule").asString();
                    int voyageID = record.get("VoyageID").asInt();
                    LocalTime heureDepart = LocalTime.parse(record.get("HeureDepart").asString());
                    LocalTime heureArrivee = LocalTime.parse(record.get("HeureArrivee").asString());
                    double latitudeDepart = record.get("LatitudeDepart").asDouble();
                    double longitudeDepart = record.get("LongitudeDepart").asDouble();
                    double latitudeArr = record.get("LatitudeArr").asDouble();
                    double longitudeArr = record.get("LongitudeArr").asDouble();
                    int capacite = record.get("capacite").asInt();
                    int endDep=record.get("endSDepp").asInt();
                    int endArr=record.get("endSArr").asInt();

                    Bus bus = new Bus(matricule, capacite);
                    Station stationDepart = new Station(departureStation, latitudeDepart, longitudeDepart,endDep);
                    Station stationArrivee = new Station(arrivalStation, latitudeArr, longitudeArr,endArr);

                    Voyage voyage = new Voyage(voyageID,bus, LocalDate.now(), null); // Assurez-vous que le constructeur et les paramètres sont corrects
                    voyage.setStationDepart(stationDepart);
                    voyage.setStationArr(stationArrivee);
                    System.out.println(voyage);
                    voyages.add(voyage);
                }
            }

            return voyages;
        }
    }
*/
}