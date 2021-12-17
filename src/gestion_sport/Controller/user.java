package gestion_sport.Controller;


public class user {
  private static int idu=0;
 private String nom;
  private String prenom;
   private String email;

    public user(String nom, String prenom, String email) {
        idu++;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public static int getIdu() {
        return idu;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

   

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 

   
   
}
