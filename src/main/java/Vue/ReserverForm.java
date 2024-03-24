package Vue;

import Modele.BO.Station;
import Modele.BO.Voyage;
import Modele.DAO.Neo4jStationFinder;
import Modele.DAO.VoyageFinder;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ReserverForm extends JFrame {
    private JComboBox<String> StationsDestcomboBox;
    private JComboBox<String> StationProcheComboBox;
    private JButton Chercher;
    private JPanel panel1;
    private JLabel Date;
    private JLabel Heure;
    private JPanel HeurePanel;
    private Neo4jStationFinder stationFinder;

    private String StationDepp;
    private String  StationDes;

    public ReserverForm() {
        initializeUI();

        // Example coordinates, replace with actual data retrieval mechanism
        double latitude = 10.5678;
        double longitude = 47.3456;
        updateNearestStations(latitude,longitude);
        updateDestinationStations();

        StationsDestcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                 StationDes = (String) e.getItem();

            }
        });

        StationProcheComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                 StationDepp = (String) e.getItem();

            }
        });

        Chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                VoyageFinder VF=new VoyageFinder();
                List<Voyage> voyages = VF.VoyagesTime(StationDepp, StationDes);

                if (!voyages.isEmpty()) {
                    new VoyageDispo(voyages,StationDepp,StationDes); // Ouvre la nouvelle fenêtre avec les voyages
                    dispose(); // Ferme la fenêtre actuelle
                } else {
                    JOptionPane.showMessageDialog(ReserverForm.this, "Aucun voyage disponible");
                }


            }
        });
    }
    private void initializeUI() {
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        updateTimeAndDate();
        new Timer(1000, e -> updateTimeAndDate()).start();
    }
    private void updateNearestStations(double latitude, double longitude) {
        stationFinder = new Neo4jStationFinder();

        Map<Station, List<Station>> closestStationsMap = stationFinder.findClosestStationsWithDestinations(latitude, longitude);
        StationProcheComboBox.removeAllItems();
        for (Station station : closestStationsMap.keySet()) {
            StationProcheComboBox.addItem(station.getStation());
        }
    }
    private void updateDestinationStations() {
        stationFinder = new Neo4jStationFinder();

        StationsDestcomboBox.removeAllItems();
        List<Station> destinations = stationFinder.getAllStations();
        if (destinations != null) {
            for (Station destination : destinations) {
                StationsDestcomboBox.addItem(destination.getStation());
            }
        }
    }

    private void updateTimeAndDate() {
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date maintenant = new Date();
        Heure.setText(heureFormat.format(maintenant));
        Date.setText(dateFormat.format(maintenant));
    }

    public static void main(String[] args) {
        new ReserverForm();
    }}
