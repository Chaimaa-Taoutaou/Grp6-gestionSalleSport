package gestion_sport;

public class type_Abonnement {
	private static int id_talon=0;
	private String libelle;
	
	public type_Abonnement(String libelle) {
		id_talon++;
		this.libelle=libelle;
	}

	public static int getId_talon() {
		return id_talon;
	}

	public static void setId_talon(int id_talon) {
		type_Abonnement.id_talon = id_talon;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

}
