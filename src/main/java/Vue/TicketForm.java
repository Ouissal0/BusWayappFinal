package Vue;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketForm extends JFrame{
    private JLabel NumVoyage;
    private JLabel PassengerName;
    private JLabel Depart;
    private JLabel Arrive;
    private JPanel QRCode= new JPanel();
    private JLabel Date;
    private JLabel hour;
    private JPanel MainPanel;

    public void updateTicketInformation(String numVoyage, String passengerName, String depart, String arrive) {
        // Mettre à jour les informations du ticket

        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        NumVoyage.setText(numVoyage);
        PassengerName.setText(passengerName);
        Depart.setText(depart);
        Arrive.setText(arrive);


        // Mettre à jour la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();
        Date.setText(now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        hour.setText(now.format(DateTimeFormatter.ofPattern("HH:mm")));

        // Générer et afficher le code QR
        try {
            String qrCodeData = "Passenger: " + passengerName + ", Voyage: " + numVoyage + ", Date: " + Date.getText() + ", Hour: " + hour.getText();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            JLabel qrLabel = new JLabel(new ImageIcon(qrImage));
            QRCode.removeAll(); // Supprimer l'ancien code QR s'il existe
            QRCode.add(qrLabel);
            QRCode.revalidate(); // Revalider le JPanel après ajout du nouveau contenu
            QRCode.repaint(); // Redessiner le JPanel pour afficher le nouveau code QR
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Créer et configurer la fenêtre
            JFrame frame = new JFrame("Ticket Info");
            TicketForm ticketForm = new TicketForm();
            frame.setContentPane(ticketForm.MainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Mettre à jour les informations du ticket après un court délai
            // pour s'assurer que la fenêtre est visible
            SwingUtilities.invokeLater(() -> {
                String numVoyage = "V1234";
                String passengerName = "John Doe";
                String depart = "   Paris";
                String arrive = "Londres";
                ticketForm.updateTicketInformation(numVoyage, passengerName, depart, arrive);
            });
        });
    }


}
