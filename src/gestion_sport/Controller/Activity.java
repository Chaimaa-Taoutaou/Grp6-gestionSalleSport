package gestion_sport.Controller;

public class Activity {
    private static int id_activity=0;
    private String nom,formateur,prix;

    public Activity(String nom, String formateur, String prix) {
        id_activity++;
        this.nom = nom;
        this.formateur = formateur;
        this.prix = prix;
    }

    public static int getId_activity() {
        return id_activity;
    }

    public static void setId_activity(int id_activity) {
        Activity.id_activity = id_activity;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
