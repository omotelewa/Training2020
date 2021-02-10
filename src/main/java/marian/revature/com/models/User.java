package marian.revature.com.models;

public class User {
	
	
	int id;
	String inputUsername; 
	String inputPassword;
	String inputFirstName;
	String inputLastName;
	String inputEmail;
	Role r;
	
	public User (String inputusername, String inputPassword, 
			String inputFirstName, String inputLastName, String inputEmail, Role r ) {
		
	
		this.inputUsername = inputusername;
		this.inputPassword = inputPassword;
		this.inputFirstName = inputFirstName;
		this.inputLastName = inputLastName;
		this.inputEmail = inputEmail;
		
		this.r = r;
		
		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInputUsername() {
		return inputUsername;
	}
	public void setInputUsername(String inputUsername) {
		this.inputUsername = inputUsername;
	}
	public String getInputPassword() {
		return inputPassword;
	}
	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}
	public String getInputFirstName() {
		return inputFirstName;
	}
	public void setInputFirstName(String inputFirstName) {
		this.inputFirstName = inputFirstName;
	}
	public String getInputLastName() {
		return inputLastName;
	}
	public void setInputLastName(String inputLastName) {
		this.inputLastName = inputLastName;
	}
	public String getInputEmail() {
		return inputEmail;
	}
	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}
	public Role getR() {
		return r;
	}
	public void setR(Role r) {
		this.r = r;
	}

}
