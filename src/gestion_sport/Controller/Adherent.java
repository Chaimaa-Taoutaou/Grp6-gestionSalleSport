package gestion_sport.Controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Adherent {

	private static int id_a=0;
	private String cin;
	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	private String s;

	private Date dateinscrip;
	private String strDate;

	private String typeabon;
	private String nomacti;
	private String formateur;
	private Float prix;

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public  Adherent(){

	}

	public Adherent(String cin,String nom, String prenom,String tel){
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.tel=tel;
	}


	public Adherent(String cin, String nom, String prenom, String tel, String adresse, String s, Date dateinscrip) {
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.s = s;
		this.dateinscrip = dateinscrip;
	}

	public Adherent(String cin, String nom, String prenom, String tel, String adresse, String s,String strDate,String typeabon,Float prix) {
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.s = s;
		this.strDate=strDate;
		this.typeabon=typeabon;
		this.prix=prix;
	}

	public Adherent(String cin, String nom, String prenom, String tel, String adresse, String s) {
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.s = s;
	}


	public Adherent(String typeabon, String nomacti, String formateur) {
		this.typeabon = typeabon;
		this.nomacti = nomacti;
		this.formateur = formateur;
	}

	public Adherent( String nomacti,String typeabon) {
		this.typeabon = typeabon;

		this.formateur = formateur;
	}

	public String getTypeabon() {
		return typeabon;
	}

	public String getNomacti() {
		return nomacti;
	}

	public String getFormateur() {
		return formateur;
	}

	public Date getDateinscrip() {
		return dateinscrip;
	}


	public void setDateString(String dateStr){
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		dateStr = dateFormat.format(dateinscrip);*/
		this.strDate=dateStr;
	}
	public String getDateString(){

		return strDate;
	}

	public static int getId_a() {
		return id_a;
	}

	public String getCin() {
		return cin;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTel() {
		return tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getS() {
		return s;
	}

	public static void setId_a(int id_a) {
		Adherent.id_a = id_a;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setS(String s) {
		this.s = s;
	}






}



