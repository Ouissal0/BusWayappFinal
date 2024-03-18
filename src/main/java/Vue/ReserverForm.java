package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ReserverForm extends JFrame{
    private JComboBox stationsLesPlusProchesComboBox;
    private JComboBox comboBox2;
    private JButton réserverTicketButton;
    private JPanel MainPanel;
    private JLabel Date;
    private JLabel Heure;
    private JPanel HeurePanel;

    public ReserverForm()
    {
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);
        setVisible(true);

        réserverTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        stationsLesPlusProchesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });

        updateTimeAndDate();
        Timer timer = new Timer(1000, e -> updateTimeAndDate());
        timer.start();




    }
    private void updateTimeAndDate() {
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Récupérer l'heure et la date actuelles
        Date maintenant = new Date();
        String heureActuelle = heureFormat.format(maintenant);
        String dateActuelle = dateFormat.format(maintenant);

        // Mettre à jour les labels

        Date.setText("Date :"+dateActuelle);
        Heure.setText(  heureActuelle );

    }
    public static void main(String[] args)
    {
        new ReserverForm();
    }
}
