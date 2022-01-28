package gestion_sport.Controller;

import java.sql.Date;

public class Adherent {

    private static int id_a=0;
    private String cin;
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private String s;
    private Date dateinscrip;



    public Adherent(String cin, String nom, String prenom, String tel, String adresse, String s, Date dateinscrip) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.s = s;
        this.dateinscrip = dateinscrip;
    }

    public Date getDateinscrip() {
        return dateinscrip;
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



