package Modele.BO;

import java.time.LocalTime;

public class Arret
{
	
     private  Voyage V;
     private  Station S;
     private  LocalTime HeureArret;
	 private int NbrPlacesVides;
     
     
	public Arret(Voyage v, Station s, LocalTime heureArret) {
		super();
		V = v;
		S = s;
		HeureArret = heureArret;
		NbrPlacesVides=v.getB().getNbrPlacesLimite();
	}


	public Voyage getV() {
		return V;
	}


	public void setV(Voyage v) {
		V = v;
	}


	public Station getS() {
		return S;
	}


	public void setS(Station s) {
		S = s;
	}


	public LocalTime getHeureArret() {
		return HeureArret;
	}


	public void setHeureArret(LocalTime heureArret) {
		HeureArret = heureArret;
	}



	public void setNbrPlacesVides(int NbrPlaces)
	{

		NbrPlacesVides=NbrPlaces;

	}

  public int getNbrPlacesVides()
  {
	  return  NbrPlacesVides;
  }
     
     
     
     
     

}
