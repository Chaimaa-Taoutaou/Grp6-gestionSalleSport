package gestion_sport.Controller;

public class type_Abonnement {
	private final int id_talon;
	private String libelle;
	


	public type_Abonnement(int id_talon, String libelle) {
		this.id_talon = id_talon;
		this.libelle = libelle;
	}

	public  int getId_talon() {
		return id_talon;
	}



	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return libelle ;
	}

	String req="insert into ";
}
