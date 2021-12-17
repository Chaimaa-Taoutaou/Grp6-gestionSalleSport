package Classes;

public class Activite {
	private static int id_s=0;
	private String nom_s;
	private String description;
	
	public Activite(String nom_s,String description) {
		id_s++;
		this.nom_s=nom_s;
		this.description=description;
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
