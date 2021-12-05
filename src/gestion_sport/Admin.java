package gestion_sport;

public class Admin {
	private static int id=0;
	private String username,password;
	
	public Admin(String username,String password) {
		this.username=username;
		this.password=password;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Admin.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
