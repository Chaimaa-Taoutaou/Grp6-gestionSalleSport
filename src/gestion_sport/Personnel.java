package gestion_sport;

import java.sql.Date;

public class Personnel {
	private static int id_p=0;
	private String nom_p,prenom_p,email_p,password_p,image_p;
	private Date date_naiss;
	
	public Personnel(String nom_p,String prenom_p,String email_p,String password_p,String image_p) {
		id_p++;
		this.nom_p=nom_p;
		this.prenom_p=prenom_p;
		this.email_p=email_p;
		this.password_p=password_p;
		this.image_p=image_p;
	}
	public Personnel(String nom_p,String prenom_p,String email_p) {
		this.nom_p=nom_p;
		this.password_p=prenom_p;
		this.email_p=email_p;
	}

	
	public static int getId_p() {
		return id_p;
	}

	public static void setId_p(int id_p) {
		Personnel.id_p = id_p;
	}

	public String getNom_p() {
		return nom_p;
	}

	public void setNom_p(String nom_p) {
		this.nom_p = nom_p;
	}

	public String getPrenom_p() {
		return prenom_p;
	}

	public void setPrenom_p(String prenom_p) {
		this.prenom_p = prenom_p;
	}

	public String getEmail_p() {
		return email_p;
	}

	public void setEmail_p(String email_p) {
		this.email_p = email_p;
	}

	public String getPassword_p() {
		return password_p;
	}

	public void setPassword_p(String password_p) {
		this.password_p = password_p;
	}

	public String getImage_p() {
		return image_p;
	}

	public void setImage_p(String image_p) {
		this.image_p = image_p;
	}

	public Date getDate_naiss() {
		return date_naiss;
	}

	public void setDate_naiss(Date date_naiss) {
		this.date_naiss = date_naiss;
	}
	
}
