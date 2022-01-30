package gestion_sport.Controller;


public class Formateur extends Personnel {
	private static int id_f=0;
	String adresse_f;
	private float salaire;


	/*public Formateur(String nom_p, String prenom_p, String email_p, String password_p, String image_p,float salaire) {
		super(nom_p, prenom_p, email_p, password_p, image_p);
		this.salaire=salaire;
	}
	*/
	public Formateur(int id_f,String nom_f){
		super(nom_f);
		Formateur.id_f =id_f;
	}
	public Formateur(String nom_f,String prenom_f,String email_f,String adresse_f) {
		super(nom_f,prenom_f,email_f);
		this.adresse_f=adresse_f;
	}
	
	
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}


	public static int getId_f() {
		return id_f;
	}


	public static void setId_f(int id_f) {
		Formateur.id_f = id_f;
	}


	public String getAdresse_f() {
		return adresse_f;
	}


	public void setAdresse_f(String adresse_f) {
		this.adresse_f = adresse_f;
	}

	@Override
	public String toString() {
		return this.getNom_p();
	}
	
}
