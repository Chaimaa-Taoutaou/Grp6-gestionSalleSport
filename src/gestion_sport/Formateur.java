package gestion_sport;

public class Formateur extends Personnel implements Adherent{
	private float salaire;
	public Formateur(String nom_p, String prenom_p, String email_p, String password_p, String image_p,float salaire) {
		super(nom_p, prenom_p, email_p, password_p, image_p);
		this.salaire=salaire;
	}
	
	
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}
	
}
