package gestion_sport;

public class Abonnement {
	private static int id_abon;
	private String type_abon;
	private int dur�e;
	
	public Abonnement(String type_abon,int dur�e) {
		id_abon++;
		this.type_abon=type_abon;
		this.dur�e=dur�e;
	}

	public static int getId_abon() {
		return id_abon;
	}

	public static void setId_abon(int id_abon) {
		Abonnement.id_abon = id_abon;
	}

	public String getType_abon() {
		return type_abon;
	}

	public void setType_abon(String type_abon) {
		this.type_abon = type_abon;
	}

	public int getDur�e() {
		return dur�e;
	}

	public void setDur�e(int dur�e) {
		this.dur�e = dur�e;
	}
	
	
}
