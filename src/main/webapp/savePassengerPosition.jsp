<%@ page import="Modele.BO.Passager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Récupérer les paramètres de la requête
  float latitude = Float.parseFloat(request.getParameter("latitude"));
  float longitude = Float.parseFloat(request.getParameter("longitude"));

  // Créer un objet Passager avec les coordonnées
  Passager passager = new Passager("", longitude, latitude);
  System.out.println(longitude+" "+latitude);
%>

<!DOCTYPE html>
<html>
<head>
  <title>Confirmation</title>
</head>
<body>
<h2>Confirmation de la position du passager</h2>
<p>Latitude: <%= latitude %></p>
<p>Longitude: <%= longitude %></p>
<!-- Vous pouvez ajouter d'autres informations de confirmation ou rediriger l'utilisateur ici -->
</body>
</html>
