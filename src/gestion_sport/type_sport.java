package gestion_sport;

public class type_sport {
	private static int id_s=0;
	private String nom_s;
	private String description;
	
	public type_sport(String nom_s,String description) {
		id_s++;
		this.nom_s=nom_s;
		this.description=description;
	}

	public static int getId_s() {
		return id_s;
	}

	public static void setId_s(int id_s) {
		type_sport.id_s = id_s;
	}

	public String getNom_s() {
		return nom_s;
	}

	public void setNom_s(String nom_s) {
		this.nom_s = nom_s;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
