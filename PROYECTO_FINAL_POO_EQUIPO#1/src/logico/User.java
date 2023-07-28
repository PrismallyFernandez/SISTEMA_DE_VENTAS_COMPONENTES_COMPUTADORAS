package logico;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3609313352946430885L;
	
	private String userId;
	private String tipo;
	private String name;
	private String userName;
	private String pass;
	public static int cod = 1;
	
	public User(String userId, String tipo, String name, String userName, String pass) {
		super();
		this.userId = userId;
		this.name = name;
		this.tipo = tipo;
		this.userName = userName;
		this.pass = pass;
		User.cod++;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
