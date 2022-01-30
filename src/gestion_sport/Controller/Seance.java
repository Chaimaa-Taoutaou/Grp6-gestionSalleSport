package gestion_sport.Controller;

import java.sql.Date;

public class Seance {
	private static int id_s=0;
	private String date_s;
	private String heure_debut;
	private String heure_fin;

	public Seance(String heure_debut,String heure_fin) {
		id_s++;
		this.heure_debut=heure_debut;
		this.heure_fin=heure_fin;
	}
	
	public Seance(String date_s,String heure_debut,String heure_fin) {
		id_s++;
		this.date_s=date_s;
		this.heure_debut=heure_debut;
		this.heure_fin=heure_fin;
	}

	public static int getId_s() {
		return id_s;
	}

	public static void setId_s(int id_s) {
		Seance.id_s = id_s;
	}

	public String getDate_s() {
		return date_s;
	}

	public void setDate_s(String date_s) {
		this.date_s = date_s;
	}

	public String getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(String heure_debut) {
		this.heure_debut = heure_debut;
	}

	public String getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(String heure_fin) {
		this.heure_fin = heure_fin;
	}
}
