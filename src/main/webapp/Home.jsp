<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Busway Ticket Reservation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script>
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
    <style>
        /* Custom styles go here */
        .top-bar {
            background-color: #f8f9fa; /* Example background color */
            padding: 10px 0;
        }

        .navbar {
            background-color: #fff; /* White background for the navbar */
        }

        .main-banner {
            margin-top: 20px;
            background-image: url('images/main_image.png');
            background-size: cover;
            height: 300px; /* Adjust as needed */
        }

        /* Additional custom styling */
        /* ... */
    </style>
</head>
<body>

<!-- Top Bar for Social Media and Language Selection -->
<div class="top-bar d-flex justify-content-end">
    <div class="social-media-links">
        <!-- Icons for Facebook, YouTube, Instagram -->
    </div>
    <div class="language-selector mx-3">
        <!-- Language dropdown -->
    </div>
    <div class="account-info">
        <!-- Login or account info links -->
    </div>
</div>

<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <!-- Your Logo Here -->
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <!-- Navigation Links Here -->
        </ul>
    </div>
</nav>

<!-- Main Banner Section -->
<div class="main-banner">
    <!-- Banner Content Here -->
</div>

<!-- Search and Call-to-Action Section -->
<div class="container mt-4">
    <div class="row">
        <div class="col">
            <!-- Search Bar -->
        </div>
        <div class="col">
            <!-- Call-to-Action Buttons -->
        </div>
    </div>
</div>

<!-- Main Content Section -->
<div class="container mt-4">
    <div class="col-md-4">
        <div class="card">
            <img class="card-img-top" src="bus_image.jpg" alt="Bus image">
            <div class="card-body">
                <h5 class="card-title">Reserver votre ticket</h5>
                <p class="card-text">Description</p>
                <a href="SavePosition" method="post" class="btn btn-primary" onclick="getLocationAndSubmitForm()">Reserve Ticket</a>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="footer bg-light mt-5">
    <div class="container text-center">
        <span class="text-muted">&copy; 2023 Busway Reservation System</span>
    </div>
</footer>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
