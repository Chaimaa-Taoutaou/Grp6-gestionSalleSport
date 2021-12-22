package gestion_sport.Controller;

public class Adherent{
	private static int id_A=0;
	private String nom_A,prenom_A,adresse_A,img_A,tel_A,sexe_A;
	
	public Adherent(String nom_A,String prenom_A,String adresse_A,String imd_A,String tel_A,String sexe_A) {
		id_A++;
		this.nom_A=nom_A;
		this.prenom_A=prenom_A;
		this.adresse_A=adresse_A;
		this.tel_A=tel_A;
		this.sexe_A=sexe_A;
	}

	

	public String getNom_A() {
		return nom_A;
	}

	public void setNom_A(String nom_A) {
		this.nom_A = nom_A;
	}

	public String getPrenom_A() {
		return prenom_A;
	}

	public void setPrenom_A(String prenom_A) {
		this.prenom_A = prenom_A;
	}

	public String getAdresse_A() {
		return adresse_A;
	}

	public void setAdresse_A(String adresse_A) {
		this.adresse_A = adresse_A;
	}

	public String getImg_A() {
		return img_A;
	}

	public void setImg_A(String img_A) {
		this.img_A = img_A;
	}

	public String getTel_A() {
		return tel_A;
	}

	public void setTel_A(String tel_A) {
		this.tel_A = tel_A;
	}
	
	public String getSexe_A() {
		return sexe_A;
	}
	
	public void setSexe_A(String sexe_A) {
		this.sexe_A=sexe_A;
	}
	
}
