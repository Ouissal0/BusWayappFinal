<!DOCTYPE html>
<html>
<head>
    <title>Busway Ticket Reservation</title>
    <%@include file="Includes/head.jsp"%>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 80%; margin: 20px auto; }
        label { display: block; margin: 15px 0 5px; }
        select, button { width: 100%; padding: 10px; }
        button { background-color: #91c491; color: white; border: none; margin-top: 20px; }
        /* New CSS for the navbar */
        .Clock {
            overflow: hidden;
            padding: 10px 0;
            text-align: center;
            color: black;
        }
        .current-time { margin: 0; }
        .current-date { text-align: center; margin-top: 20px; }
    </style>
    <script>
        function startTime() {
            var today = new Date();
            var h = today.getHours();
            var m = today.getMinutes();
            var s = today.getSeconds();
            m = checkTime(m);
            s = checkTime(s);
            document.getElementById('clock').innerHTML = h + ":" + m + ":" + s;
            var t = setTimeout(startTime, 500);
        }
        function checkTime(i) {
            if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
            return i;
        }

        // Function to set the current date
        function setDate() {
            var today = new Date();
            var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
            document.getElementById('date').innerHTML = "Date: " + date;
        }

        // Call the time and date functions when the window loads
        window.onload = function() {
            startTime();
            setDate();
        };
        // Check if Geolocation is supported
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function(position) {
                    // On success, retrieve the coordinates
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;

                    console.log("Latitude: " + latitude + ", Longitude: " + longitude);
                    document.getElementById("latitude").value=latitude;
                    document.getElementById("longitude").value=longitude;

                    // You can now use these coordinates as needed
                },
                function(error) {
                    // On error, display an appropriate message
                    console.error("Error getting location: " + error.message);
                },
                {
                    enableHighAccuracy: true, // Request the most accurate location data
                    timeout: 10000,          // Set the maximum time allowed to try to get the location
                    maximumAge: 0            // Indicates that the device cannot use a cached position
                }
            );
        } else {
            console.error("Geolocation is not supported by this browser.");
        }
    </script>
</head>
<body>
<%@include file="Includes/navbar.jsp"%>

<div class="container">
    <div class="Clock">
        <h2 id="clock" class="current-time"></h2>
    </div>

    <h1>Welcome to Busway</h1>
    <form id="reservationForm" method="post" action="SavePosition">
        <label for="nearestStation">Nearest Stations </label>
        <select name="nearestStation" id="nearestStation">
            <c:forEach var="station" items="${nearestStations}">
                <option value="${station.id}">${station.nom}</option>
            </c:forEach>
        </select>

        <label for="destinationStation">Select your Destination</label>
        <select name="destinationStation" id="destinationStation">
            <!-- Options will be generated dynamically -->
        </select>

        <input type="hidden" id="latitude" name="latitude">
        <input type="hidden" id="longitude" name="longitude">

        <button type="submit">Reserver Ticket</button>
    </form>
</div>
<div id="date" class="current-date"></div>
</body>
</html>
