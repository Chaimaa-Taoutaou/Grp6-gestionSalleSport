package gestion_sport;

import java.sql.Date;

public class Seance {
	private static int id_s=0;
	private Date date_s;
	private int dur�e_s;
	private int heure_s;
	
	public Seance(Date date_s,int dur�e_s,int heure_s) {
		id_s++;
		this.date_s=date_s;
		this.dur�e_s=dur�e_s;
		this.heure_s=heure_s;
	}

	public static int getId_s() {
		return id_s;
	}

	public static void setId_s(int id_s) {
		Seance.id_s = id_s;
	}

	public Date getDate_s() {
		return date_s;
	}

	public void setDate_s(Date date_s) {
		this.date_s = date_s;
	}

	public int getDur�e_s() {
		return dur�e_s;
	}

	public void setDur�e_s(int dur�e_s) {
		this.dur�e_s = dur�e_s;
	}

	public int getHeure_s() {
		return heure_s;
	}

	public void setHeure_s(int heure_s) {
		this.heure_s = heure_s;
	}
	
	
	
}
