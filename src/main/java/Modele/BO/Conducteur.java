package Modele.BO;

public class Conducteur {

    
	private String Nom;
    private String CIN;
	public Conducteur(String nom, String cIN) {
		super();
		Nom = nom;
		CIN = cIN;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
    
    
}
