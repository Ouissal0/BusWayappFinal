package Modele.BO;

public class Bus {
    private String Matricule;
    private int NbrPlacesLimite;
	private String nom;

	public Bus(String matricule, int nbrPlacesLimite,String nom) {
		super();
		Matricule = matricule;
		NbrPlacesLimite = nbrPlacesLimite;
		this.nom=nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
