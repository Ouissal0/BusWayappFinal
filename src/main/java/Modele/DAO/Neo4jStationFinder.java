package Modele.DAO;

import ConnectionDb.ConnectionDb;
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

    public Neo4jStationFinder()
    {
        ConnectionDb neo4j=new ConnectionDb();
        driver= neo4j.getDriver();

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
                        stationNode.get("longitude").asDouble(),
                        stationNode.get("end").asInt()
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
                            destNode.get("longitude").asDouble(),
                            destNode.get("end").asInt()
                    );
                    destinations.add(destStation);
                }

                stationMap.put(station, destinations);
            }

        }

        return stationMap;
    }
    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (s:STATION) RETURN s.nom AS nom, s.latitude AS latitude, s.longitude AS longitude,s.end as End");
            while (result.hasNext()) {
                Record record = result.next();
                String nom = record.get("nom").asString();
                double latitude = record.get("latitude").asDouble();
                double longitude = record.get("longitude").asDouble();
                int end=record.get("End").asInt();
                stations.add(new Station(nom, latitude, longitude,end));
            }
        }
        return stations;
    }



}