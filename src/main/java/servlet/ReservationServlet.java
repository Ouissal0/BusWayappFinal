package servlet;

import java.io.IOException;
import Modele.BO.Passager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SavePosition", urlPatterns = "/SavePosition")
public class ReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String latitudeStr = request.getParameter("latitude");
            String longitudeStr = request.getParameter("longitude");
            System.out.println("Received latitude: " + latitudeStr + ", longitude: " + longitudeStr);

            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);
                response.sendRedirect("index.jsp");


            // Rest of the servlet code...
        } catch (NumberFormatException e) {
            System.err.println("Error parsing latitude or longitude: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid latitude or longitude");
        }
    }

}
