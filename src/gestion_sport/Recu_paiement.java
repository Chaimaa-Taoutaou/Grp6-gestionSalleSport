package gestion_sport;

import java.sql.Date;

public class Recu_paiement {
	private static int id_r=0;
	private Date date;
	private float montant;
	
	public Recu_paiement(Date date,float montant) {
		id_r++;
		this.date=date;
		this.montant=montant;
	}

	public static int getId_r() {
		return id_r;
	}

	public static void setId_r(int id_r) {
		Recu_paiement.id_r = id_r;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	

}
