package Modele.DAO;

import Modele.BO.Station;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

public class Neo4jStationFinder {

    private final Driver driver;
    private Map<Station, List<Station>> stationMap;

    public Neo4jStationFinder(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void close() {
        driver.close();
    }

    public Map<Station, List<Station>> findClosestStationsWithDestinations(double latitude, double longitude) {
        stationMap = new HashMap<>();
        try (Session session = driver.session()) {
            // Trouver les 3 stations les plus proches
            String closestStationsQuery = "MATCH (s:STATION) " +
                    "RETURN s, point.distance(point({latitude: s.latitude, longitude: s.longitude}), point({latitude: $lat, longitude: $lon})) AS dist " +
                    "ORDER BY dist LIMIT 3";

            Result closestStationsResult = session.run(closestStationsQuery, parameters("lat", latitude, "lon", longitude));

            // Pour chaque station proche, trouver les destinations accessibles
            while (closestStationsResult.hasNext()) {
                Record record = closestStationsResult.next();
                Node stationNode = record.get("s").asNode();
                Station station = new Station(
                        stationNode.get("nom").asString(),
                        stationNode.get("latitude").asDouble(),
                        stationNode.get("longitude").asDouble()
                );

                String destinationsQuery = "MATCH path=(start:STATION {nom: $stationId})-[:NEXT*]->(d:STATION) " +
                        "WHERE ALL(r IN relationships(path) WHERE r.nbreDispo > 0) " +
                        "RETURN d";

                List<Station> destinations = new ArrayList<>();
                Result destinationsResult = session.run(destinationsQuery, parameters("stationId", station.getStation()));

                while (destinationsResult.hasNext()) {
                    Record destRecord = destinationsResult.next();
                    Node destNode = destRecord.get("d").asNode();
                    Station destStation = new Station(
                            destNode.get("nom").asString(),
                            destNode.get("latitude").asDouble(),
                            destNode.get("longitude").asDouble()
                    );
                    destinations.add(destStation);
                }

                stationMap.put(station, destinations);
            }

        }
        return stationMap;
    }

    public static void main(String... args) {
        Neo4jStationFinder neo4j = new Neo4jStationFinder("bolt://localhost:7687", "neo4j", "12398765");

        double passengerLat = 10.5678;
        double passengerLon = 47.3456;

        Map<Station, List<Station>> closestStations = neo4j.findClosestStationsWithDestinations(passengerLat, passengerLon);

        for (Map.Entry<Station, List<Station>> entry : closestStations.entrySet()) {
            System.out.println("Closest Station: " + entry.getKey().getStation());
            System.out.println("Destinations:");
            for (Station station : entry.getValue()) {
                System.out.println(" - " + station.getStation());
            }
        }



        neo4j.close();
    }}