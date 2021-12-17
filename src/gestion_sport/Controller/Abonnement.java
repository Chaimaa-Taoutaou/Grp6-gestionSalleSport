package gestion_sport.Controller;

public class Abonnement {
	private static int id_abon;
	private String type_abon;
	private int duree;
	
	public Abonnement(String type_abon,int duree) {
		id_abon++;
		this.type_abon=type_abon;
		this.duree=duree;
	}

	public static int getId_abon() {
		return id_abon;
	}

	public static void setId_abon(int id_abon) {
		Abonnement.id_abon = id_abon;
	}

	public String getType_abon() {
		return type_abon;
	}

	public void setType_abon(String type_abon) {
		this.type_abon = type_abon;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	
}
