
package gestion_sport.Controller;


public class salle {
    private static int id_s=0;
    private String nom,ville,adrs,email,tels;

    public salle(String nom, String ville, String adrs, String email,String tels) {
        id_s++;
        this.nom = nom;
        this.ville = ville;
        this.adrs = adrs;
        this.tels = tels;
        this.email=email;
    }

    public static int getId_s() {
        return id_s;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public String getAdrs() {
        return adrs;
    }

    public String getEmail() {
        return email;
    }

    public static void setId_s(int id_s) {
        salle.id_s = id_s;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTels() {
        return tels;
    }

    public void setTels(String tels) {
        this.tels = tels;
    }
    
    
}
