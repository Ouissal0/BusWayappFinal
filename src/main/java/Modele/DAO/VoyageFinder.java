package Modele.DAO;

import ConnectionDb.ConnectionDb;
import Modele.BO.Bus;
import Modele.BO.Station;
import org.neo4j.driver.*;
import Modele.BO.Voyage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;
import static org.neo4j.driver.Values.parameters;
import java.time.format.DateTimeFormatter;
public class VoyageFinder {
    private static Driver driver;

    public VoyageFinder() {
        ConnectionDb neo4j = new ConnectionDb();
        driver = neo4j.getDriver();
    }

    public  static List<Voyage> getBusMatricules(String stationDepart, String stationArrivee) {
        List<Voyage> Voyages = new ArrayList<>();

        String stationDep= stationDepart;/*stationDepart.getStation();*/
        String stationArr= stationArrivee;/*stationArrivee.getStation();*/
        try (Session session = driver.session()) {
            // Requête Cypher pour récupérer les bus entre les stations de départ et d'arrivée
            String query = "MATCH (depart:STATION {nom: $stationDepart})<-[relDepart:DEPART|ARRET]-(bus:BUS)-[relArrive:ARRET]->(arrivee:STATION {nom: $stationArrivee}) " +
                    "RETURN bus.nom AS matricule,bus.matricule As busmat,bus.Capacité AS Capacite , depart.nom as nomsdep ,"+
                    " depart.latitude as deplatitude, depart.longitude as deplongitude , depart.end as enddep ,"+"arrivee.nom as nomsarr ," +
                   "arrivee.latitude as arrlatitude, arrivee.longitude as arrlongitude , arrivee.end as endarr , relDepart.idVoyage as idV ";
            Result result = session.run(query, Values.parameters("stationDepart", stationDep, "stationArrivee", stationArr));
            // Parcours des résultats et ajout des matricules dans la liste
            while (result.hasNext()) {
                Record record = result.next();
                Value matriculeValue = record.get("matricule");
                String matricule = matriculeValue.asString();
                String busmat = record.get("busmat").asString();
                int capacite = record.get("Capacite").asInt();
                String nomsdep = record.get("nomsdep").asString();
                double deplatitude = record.get("deplatitude").asDouble();
                double deplongitude = record.get("deplongitude").asDouble();
                int enddep = record.get("enddep").asInt();
                String nomsarr = record.get("nomsarr").asString();
                double arrlatitude = record.get("arrlatitude").asDouble();
                double arrlongitude = record.get("arrlongitude").asDouble();
                int voyageID=record.get("idV").asInt();
                int endarr = record.get("endarr").asInt();


                // Requête Cypher pour vérifier si le nombre de places vides est égal à zéro
                String queryCount = "MATCH (bus:BUS {nom: $matricule})-[rel:DEPART|ARRET]->(station:STATION {nom: $stationDep}) " +
                        "WITH id(station) AS stationId " +
                        "MATCH (bus)-[rel:ARRET|DEPART]->(station:STATION) " +
                        "WHERE id(station) <= stationId " +
                        "WITH bus,SUM(rel.nbreDesc) AS totalNbrDesc, SUM(rel.nbreMont) AS totalNbrMont " +
                        "WHERE bus.Capacité + totalNbrDesc - totalNbrMont > 0 " +
                        "RETURN count(*) AS count ;";

                Result resultCount = session.run(queryCount, Values.parameters("matricule",matricule, "stationDep", stationDep));

                // Vérification du résultat de la requête et ajout du matricule à la liste si le résultat est zéro
                if (resultCount.hasNext()) {
                    Record recordCount = resultCount.next();
                    int count = recordCount.get("count").asInt();
                    if (count > 0) {
                        Bus bus = new Bus(busmat, capacite,matricule);
                        Station stationD = new Station(nomsdep, deplatitude, deplongitude,enddep);
                        Station stationA = new Station(nomsarr, arrlatitude, arrlongitude,endarr);

                        Voyage voyage = new Voyage(voyageID,bus, LocalDate.now(), null); // Assurez-vous que le constructeur et les paramètres sont corrects
                        voyage.setStationDepart(stationD);
                        voyage.setStationArr(stationA);

                        Voyages.add(voyage);


                    }
                }
            }
        }

        return Voyages;
    }
    public static List<Voyage> VoyagesTime( String stationDepart, String stationArrivee) {
        List<Voyage> Voyages=getBusMatricules(stationDepart, stationArrivee);
        List<Voyage> VoyagesDispo=new ArrayList<>();
        try (Session session = driver.session()) {

            for ( Voyage v: Voyages ) {
                System.out.println(v);
                String matricule=v.getB().getNom();
                System.out.println("Test 5:"+ matricule);
                // Requête Cypher pour le départ
                String departQuery = "MATCH (depart:STATION {nom: $stationDepart})<-[relDepart:DEPART|ARRET]-(bus:BUS {nom: $matricule}) " +
                        "RETURN relDepart.heure AS heureDepart";
                Result departResult = session.run(departQuery, Values.parameters("stationDepart", stationDepart, "matricule", matricule));
                if (departResult.hasNext()) {
                    Record record = departResult.next();
                    String heureDepart = record.get("heureDepart").asString();
                    System.out.println("Heure de dep  "+matricule+"   "+heureDepart);
                    // Requête Cypher pour l'arrivée
                    String arriveeQuery = "MATCH (arrivee:STATION {nom: $stationArrivee})<-[relArrive:ARRIVE|ARRET]-(bus:BUS {nom: $matricule}) " +
                            "RETURN relArrive.heure AS heureArrivee";
                    Result arriveeResult = session.run(arriveeQuery, Values.parameters("stationArrivee", stationArrivee, "matricule", matricule));
                    if (arriveeResult.hasNext()) {
                        Record arriveeRecord = arriveeResult.next();
                        String heureArrivee = arriveeRecord.get("heureArrivee").asString();
                        System.out.println("Heure de arr  "+matricule+"   "+heureArrivee);
                        // Calcul de la durée du voyage
                        String duree = calculateDuration(heureDepart, heureArrivee);
                        System.out.println("Test 4  "+duree);
                        /*********verification de duree si elle est positif ou negatif ********/
                        // Concaténation des détails du voyage
                        if (!duree.contains("-")) { // Si la durée n'est pas négative
                            System.out.println("Voyage dispo : "+ v);
                       VoyagesDispo.add(v);
                        } else {
                            System.out.println("La durée du voyage est négative pour le bus avec le matricule " );

                        }
                    }
                }
            }
        }

        return VoyagesDispo;
    }


    public static String calculateDuration(String heureDepart, String heureArrivee) {
        // Extraction des heures et des minutes de l'heure de départ
        String[] departParts = heureDepart.split(":");
        int departHour = Integer.parseInt(departParts[0]);
        int departMinute = Integer.parseInt(departParts[1]);

        // Extraction des heures et des minutes de l'heure d'arrivée
        String[] arriveeParts = heureArrivee.split(":");
        int arriveeHour = Integer.parseInt(arriveeParts[0]);
        int arriveeMinute = Integer.parseInt(arriveeParts[1]);

        // Calcul de la durée en minutes
        int dureeEnMinutes = (arriveeHour * 60 + arriveeMinute) - (departHour * 60 + departMinute);

        // Formatage de la durée en heures et minutes
        int heures = dureeEnMinutes / 60;
        int minutes = dureeEnMinutes % 60;
        return String.format("%02d:%02d", heures, minutes);
    }



    }
