package Modele.BO;

public class Bus {
    private String Matricule;
    private int NbrPlacesLimite;

	public Bus(String matricule, int nbrPlacesLimite) {
		super();
		Matricule = matricule;
		NbrPlacesLimite = nbrPlacesLimite;
	}

	public String getMatricule() {
		return Matricule;
	}

	public void setMatricule(String matricule) {
		Matricule = matricule;
	}

	public int getNbrPlacesLimite() {
		return NbrPlacesLimite;
	}

	public void setNbrPlacesLimite(int nbrPlacesLimite) {
		NbrPlacesLimite = nbrPlacesLimite;
	}
//nbr de place vides a calculer

}
