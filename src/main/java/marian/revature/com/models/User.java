package marian.revature.com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Predicate;

import marian.revature.com.utility.InputValidationUtil;

public class User {

	private int id;
	private String firstName;
	private String email;
	private String userName;
	private String password;
	private String role;
	public User() {


	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;

		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void resultSetMapper(User u, ResultSet rs) throws SQLException {
		int id = rs.getInt("user_id");
		String firstName = rs.getString("first_name");
		String password = rs.getString("password");
		String email = rs.getString("email");
		String role = rs.getString("role");
		String userName = rs.getString("user_name");
		u.setEmail(email);
		u.setId(id);
		u.setRole(role);
		u.setRole(password);
		u.setUserName(userName);
		u.setFirstName(firstName);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", email=" + email + ", userName=" + userName + ", role="
				+ role + "]";
	}

	public static User createUserByInput(Scanner scan) {
		User user = new User();
		String prompt = InputValidationUtil.pr("Enter a first name. ");
		String firstName;

		firstName = scan.nextLine();

		firstName = InputValidationUtil.validateString(firstName, prompt, scan);
		prompt = InputValidationUtil.pr("Enter a user name. ");
		String userName = scan.nextLine();
		userName = InputValidationUtil.validateString(userName, prompt, scan);
		prompt = InputValidationUtil.pr("Enter a password. ");
		String password = scan.nextLine();
		password = InputValidationUtil.validateString(password, prompt, scan);
		prompt = InputValidationUtil.pr("Enter an email. ");
		String email = scan.nextLine();
		email = InputValidationUtil.validateString(email, prompt, scan);
		prompt = InputValidationUtil.pr("Enter a ROLE: CUSTOMER or ADMIN or EMPLOYEE ");
		String role = scan.nextLine();
		role = role.toLowerCase();
		Predicate<String> validRole = (r) -> r.contentEquals("customer") || r.contentEquals("admin")
				|| r.contentEquals("employee");
		role = validRole.test(role) ? role : "";
		role = InputValidationUtil.validateString(role, prompt, scan);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setPassword(password);
		user.setRole(role);
		user.setUserName(userName);
		return user;

	}

}
