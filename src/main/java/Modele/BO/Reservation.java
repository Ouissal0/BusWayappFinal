package Modele.BO;
import java.time.*;

public class Reservation
{
    private Passager P;
    private Voyage V;
    private LocalTime heureRes;

    private Arret dep;

    public Passager getP() {
        return P;
    }

    private Arret arr;


    public Reservation(Passager p, Voyage v, LocalTime heureRes, Station SDep, Station SArr, int nbticket) {
        P = p;
        V = v;
        heureRes = heureRes;

        boolean debutDecrement = false;

        for (Arret a : v.getArrets()) {
            if (a.getS().equals(SDep)) {
                dep=a;
                debutDecrement = true;
            }

            if (debutDecrement)
            {
                if (a.getS().equals(SArr))
                {
                    arr=a;
                    break;
                }
                a.setNbrPlacesVides(a.getNbrPlacesVides() - nbticket);
            }
        }
    }

    public void afficherInformationsTicket()
    {
        System.out.println("Nom du passager : " + P.getNom());
        System.out.println("Heure de réservation : " + heureRes);
        System.out.println("Station de départ : " + dep.getS().getStation() + " à " +dep.getHeureArret());
        System.out.println("Station d'arrivée : " + arr.getS().getStation() + " à " + arr.getHeureArret());
        System.out.println("Numéro de voyage : " + V.getNumV());
    }


}
