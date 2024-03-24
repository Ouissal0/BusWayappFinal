
package Vue;

import Modele.BO.Voyage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VoyageDispo extends JFrame {
    private JLabel Heure;
    private JComboBox VoyagesDispoCombobox;
    private JButton buttonChoixVoyage;
    private JPanel MainP;
    private JLabel Date;
    private Voyage selectedVoyage;

    public VoyageDispo(List<Voyage> voyagesdispo,String sdep,String sarr) {
        setContentPane(MainP);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        updateTimeAndDate();
        new Timer(1000, e -> updateTimeAndDate()).start();

        VoyagesDispoCombobox.removeAllItems();
            for (Voyage voyage : voyagesdispo) {
                VoyagesDispoCombobox.addItem(voyage.getB().getNom());
            }



        buttonChoixVoyage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
/*********************************a refaire pour selectionner le voyage ***********************************************/
                    String voyage  = (String) VoyagesDispoCombobox.getSelectedItem();
                if (voyage != null) {
                    dispose();
                    new ConfirmerRes(voyage, sdep, sarr).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VoyageDispo.this, "Aucun voyage sélectionné.");
                }
            }
        });



    }

    private void updateTimeAndDate() {
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date maintenant = new Date();
        Heure.setText(heureFormat.format(maintenant));
        Date.setText(dateFormat.format(maintenant));
    }


}
