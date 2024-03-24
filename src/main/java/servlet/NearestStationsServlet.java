package servlet;

import Modele.BO.Station;
import Modele.DAO.Neo4jStationFinder;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/nearestStations")  // Utilisez un URL pattern ici
public class NearestStationsServlet extends HttpServlet {

    private Neo4jStationFinder stationFinder;

    public void init() {
       // stationFinder = new Neo4jStationFinder("bolt://localhost:7687", "neo4j", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            double latitude = 10.5678;
        double longitude = 47.3456;

        Map<Station, List<Station>> closestStationsMap = stationFinder.findClosestStationsWithDestinations(latitude, longitude);

        request.setAttribute("nearestStations", closestStationsMap.keySet());
        System.out.println("Stations les plus proches récupérées :");
        for (Station station : closestStationsMap.keySet()) {
            System.out.println("Station: " + station.getStation() + " - Latitude: " + station.getLatitude() + ", Longitude: " + station.getLongitude());
            System.out.println("Destinations accessibles depuis cette station:");
            List<Station> destinations = closestStationsMap.get(station);
            for (Station destination : destinations) {
                System.out.println(" - " + destination.getStation());
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); // Assurez-vous que c'est le bon chemin
        dispatcher.forward(request, response);
    }

    public void destroy() {
        stationFinder.close();
    }


}

