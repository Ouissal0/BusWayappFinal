package Vue;

import Modele.BO.Station;
import Modele.BO.Voyage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmerRes extends JFrame{
    private JTextField PassengerNameTF;
    private JTextField DepartStationTF;
    private JTextField StationArr;
    private JTextField textField1;
    private JButton reserverButton;
    private JPanel panel1;
    private JLabel Heure;
    private JLabel Date;

    public ConfirmerRes(String voyage,String sdep,String sarr)
       {

            add(panel1);
            setTitle("Reservation Form");
            setSize(700, 500);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            // Set the text fields with the station names
            DepartStationTF.setText(sdep);
            StationArr.setText(sarr);
            textField1.setText(voyage);
            updateTimeAndDate();
            new Timer(1000, e -> updateTimeAndDate()).start();
            reserverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Reservation made for: " + PassengerNameTF.getText());
                    System.out.println("Departing from: " + DepartStationTF.getText());
                    System.out.println("Arriving at: " + StationArr.getText());
                    TicketForm ticketForm = new TicketForm();
                    dispose();
                    ticketForm.updateTicketInformation(voyage,PassengerNameTF.getText(),sdep,sarr);
                }
            });

        }

    private void updateTimeAndDate() {
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date maintenant = new Date();
        Heure.setText(heureFormat.format(maintenant));
        Date.setText(dateFormat.format(maintenant));
    }


}

