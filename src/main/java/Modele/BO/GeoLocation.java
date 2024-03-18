package Modele.BO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoLocation {

    public static void main(String[] args) {
        try {
            // Envoyer une requête HTTP à l'API IP Geolocation
            URL url = new URL("https://api.ipgeolocation.io/ipgeo?apiKey=YOUR_API_KEY");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Lire la réponse de l'API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extraire et afficher les coordonnées géographiques
            String jsonResponse = response.toString();
            String latitude = jsonResponse.split("\"latitude\":")[1].split(",")[0];
            String longitude = jsonResponse.split("\"longitude\":")[1].split(",")[0];
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);

            // Fermer la connexion
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
