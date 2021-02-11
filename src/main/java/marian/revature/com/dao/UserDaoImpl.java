package marian.revature.com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import marian.revature.com.models.AccountDetails;
import marian.revature.com.models.Login;
import marian.revature.com.models.User;
import marian.revature.com.utility.ConnectionUtil;
import marian.revature.com.utility.InputValidationUtil;

public class UserDaoImpl implements UserDao {

	public static Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());


	@Override
	public List<User> getAllUsers() {
		LOG.info("GETTING ALL USERS");
		List<User> cl = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project01.user";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User u = new User();
				User.resultSetMapper(u, rs);
				cl.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return cl;
	}

	@Override
	public List<User> getAllUsersByRole(String role) throws IOException {
		LOG.info("GETTING ALL USERS BY ROLE");
		List<User> cl = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project01.user where ROLE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, role);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				User.resultSetMapper(u, rs);
				cl.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return cl;
	}

	@Override
	public User getUserbyId(int id) {

		User c = null;
		// try-with-resources... resources included in the try args will be
		// closed at
		// the end of the block
		// works with all AutoCloseable resources
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project01.user WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new User();
				User.resultSetMapper(c, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		boolean success = false;
		LOG.info("UPDATING USER BY ID info will be = " + user.toString());
		User dbUser = getUserbyId(user.getId());

		if (dbUser == null) {
			LOG.error("Make sure the existing user id is found in db before updating!! WAS NOT FOUND IN DB");
			return success; 
		}
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "UPDATE project0.user SET user_name = ? email = ? role = ? first_name = ? WHERE user_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getRole());
			pstmt.setString(4, user.getFirstName());
			pstmt.setInt(5, user.getId());
			pstmt.execute();
			success = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		
		return success; 

	}

	@Override
	public boolean deleteUser(User user) {
		boolean success = false;
		LOG.info("DELETING USER BY ID " + user.toString());
		User dbUser = getUserbyId(user.getId());
		
		if (dbUser == null) {
			LOG.error("Make sure the existing user id is found in db before deleting!! WAS NOT FOUND IN DB");
			return success; 
		}
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "DELETE FROM project0.user WHERE user_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.execute();
			success = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		
		return success; 

	}

	@Override
	public AccountDetails createBankAccount() {
		User c = null;
		// TODO Auto-generated method stub
		int type = 1;
		int accid = 0;
		double balance = 0;

		String choice;
		boolean checking = true;
		boolean yn = true;
		while (yn) {
			float amount = InputValidationUtil
					.getUserAmount("Please enter the amount of money you would like to deposit");

			String userResponse = InputValidationUtil.promptUser("Will this be a checking account?", "Yes", "No");
			if (userResponse.contentEquals("yes")) { // checking account
				System.out.printf("Great. Creating a checkings account in the amonunt of $.2f\n", amount);
			} else if (userResponse.contentEquals("no")) { // savings account
				System.out.printf("Great. Creating a savings account in the amonunt of $.2f\n", amount);

			} else {
				System.out.println("Exiting the application");
				System.exit(0);
			}

			type = 0;
		}
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "INSERT INTO project0.accounts (BALANCE, ACCOUNT_TYPE,acc_id) VALUES (-965, ?,?); END;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			Random rand = new Random();
			accid = rand.nextInt();
			pstmt.setInt(2, accid);
			ResultSet rs;
			pstmt.execute();

			sql = "SELECT * FROM  project0.accounts WHERE BALANCE = -965";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				accid = rs.getInt("acc_id");

			}
			System.out.println("please enter a username");
			Scanner accin = new Scanner(System.in);
			String username = accin.nextLine();

			System.out.println("please enter your first name");
			String fname = accin.nextLine();

			System.out.println("please enter your last name");
			String lname = accin.nextLine();
			System.out.println("please enter your password");
			String password = accin.nextLine();

			// sql = "UPDATE User SET ACCOUNT_ID= ? WHERE ACCOUNT_ID
			// IS NULL";
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, accid);
			// rs = pstmt.executeQuery();

			sql = "INSERT INTO project0.user1 (first_name,last_name,username,password,acc_id) VALUES (?,?,?,?,?); END;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, username);
			pstmt.setString(4, password);
			pstmt.setInt(5, accid);

			pstmt.execute();

			sql = "UPDATE project0.ACCOUNTs SET BALANCE = ? WHERE acc_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, balance);
			pstmt.setInt(2, accid);

			pstmt.execute();

			sql = "SELECT * FROM project0.user1 WHERE username = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//
//				int ACCOUNT_ID = rs.getInt("acc_id");
//
//				int USER_ID = rs.getInt("ID");
//				String USER_FIRSTNAME = rs.getString("first_name");
//				String USER_LASTNAME = rs.getString("last_name");
//				String USER_NAME = rs.getString("username");
//
//				c = new User(USER_ID, USER_FIRSTNAME, USER_LASTNAME, ACCOUNT_ID, USER_NAME);
//				System.out.println("USER CREATED");
//
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;

	}

	

	@Override
	public User getUserbyUsername(String username) throws IOException {
		LOG.info("GETTING USER BY USERNAME");

		User currentUser = null;
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project0.user WHERE user_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				currentUser = new User();
				User.resultSetMapper(currentUser, rs); 

			}

		} catch (ClassNotFoundException | SQLException e1) {
			LOG.error(e1.getMessage());
		}

		return currentUser;

	}

	@Override
	public User getUserbyUsernameAndPassword(String username, String password) throws IOException {
		LOG.info("GETTING USER BY USERNAME and Password");

		User currentUser = null;
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project0.user WHERE user_name = ? AND password = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				currentUser = new User();
				User.resultSetMapper(currentUser, rs); 

			}

		} catch (ClassNotFoundException | SQLException e1) {
			LOG.error(e1.getMessage());
		}

		return currentUser;
	}

	@Override
	public User createUser(User user) {
		LOG.info("USER IS BEING ADDED TO THE DB");

		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "INSERT INTO project01.user (first_name, email, role, user_name, password) VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getRole());
			pstmt.setString(4, user.getUserName());
			pstmt.setString(5, user.getPassword());
			pstmt.execute();
		}catch (Exception e){
			LOG.error(e.getMessage());
		}
		return null;
	}

}
