package Modele.DAO;

import Modele.BO.Station;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/busway")
public class BuswayController extends HttpServlet {
    private Neo4jStationFinder stationFinder=new Neo4jStationFinder();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Station> Stations = stationFinder.getAllStations();
        for (Station s:Stations)
            System.out.println(s.getStation());
        RequestDispatcher req= request.getRequestDispatcher("/index.jsp");
        request.setAttribute("nearestStations",Stations);
        req.forward(request,response);


    }
}