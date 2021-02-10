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

import daoOperations.CustomerDao;
import marian.revature.com.models.CustomerDetails;
import marian.revature.com.utility.ConnectionUtil;

public class CustomerDAOimpl implements CustomerDao {

	private static final String DATABASE_FIEDLS = "src\\main\\java\\connection.properties";
	private Scanner accin;

	@Override
	public List<CustomerDetails> getUser() {
		List<CustomerDetails> cl = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres(DATABASE_FIEDLS)) {
			String sql = "SELECT * FROM CustomerDetails";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				int USER_ID = rs.getInt("USER_ID");
				String USER_FIRSTNAME = rs.getString("USER_FIRSTNAME");
				String USER_LASTNAME = rs.getString("USER_LASTNAME");
				String USER_NAME = rs.getString("USER_NAME");

				int ACCOUNT_ID = rs.getInt("ACCOUNT_ID");
				cl.add(new CustomerDetails(USER_ID, USER_FIRSTNAME, USER_LASTNAME, ACCOUNT_ID, USER_NAME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return cl;
	}

	@Override
	public CustomerDetails getUserbyId(int id) {
		CustomerDetails c = null;
		// try-with-resources... resources included in the try args will be closed at
		// the end of the block
		// works with all AutoCloseable resources
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres(DATABASE_FIEDLS)) {
			String sql = "SELECT * FROM CustomerDetails WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int USER_ID = rs.getInt("USER_ID");
				String USER_FIRSTNAME = rs.getString("USER_FIRSTNAME");
				String USER_LASTNAME = rs.getString("USER_LASTNAME");
				String USER_NAME = rs.getString("USER_NAME");

				int ACCOUNT_ID = rs.getInt("ACCOUNT_ID");

				c = new CustomerDetails(USER_ID, USER_FIRSTNAME, USER_LASTNAME, ACCOUNT_ID, USER_NAME);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return c;
	}

	@Override
	public void updateUser(CustomerDetails employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(CustomerDetails employee) {
		// TODO Auto-generated method stub

	}

	public CustomerDetails createUser() {
		CustomerDetails c = null;
		// TODO Auto-generated method stub
		int type = 1;
		int accid = 0;
		double balance = 0;

		String choice;
		boolean checking = true;
		boolean yn = true;
		while (yn) {
			System.out.println("please enter the amount of money you would like to deposit");
			accin = new Scanner(System.in);
			String tempbal;
			boolean isdouble = false;
			while (isdouble == false) {
				try {
					tempbal = accin.nextLine();
					balance = Double.parseDouble(tempbal);
					isdouble = true;
				} catch (InputMismatchException e) {

					System.out.println("Please enter a number");
				} catch (NumberFormatException d) {

					System.out.println("Please enter a number");
				}
			}

			System.out.println("Will this be a checking account?");

			System.out.println("enter : yes or no");
			choice = accin.nextLine();

			switch (choice) {
			case "yes":
				yn = false;

				break;

			case "no":
				yn = false;
				checking = false;
				break;

			default:
				System.out.println("please enter again ");
				boolean repeat = true;

				while (repeat) {
					System.out.println("Will this be a checking account? : yes or no");
					choice = accin.nextLine();

					switch (choice) {
					case "yes":
						yn = repeat = false;
						break;

					case "no":
						yn = repeat = false;
						checking = false;
						break;
					}
				}
				break;
			}
		}
		if (checking == false) {

			type = 0;
		}
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres(DATABASE_FIEDLS)) {
			String sql = "INSERT INTO project0.accounts (BALANCE, ACCOUNT_TYPE,acc_id) VALUES (-965, ?,?); END;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			Random rand= new Random();
			accid=rand.nextInt();
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

//			 sql = "UPDATE CustomerDetails SET ACCOUNT_ID= ? WHERE ACCOUNT_ID IS NULL";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, accid);
//			 rs = pstmt.executeQuery();

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

			rs = pstmt.executeQuery();
			while (rs.next()) {

				int ACCOUNT_ID = rs.getInt("acc_id");

				int USER_ID = rs.getInt("ID");
				String USER_FIRSTNAME = rs.getString("first_name");
				String USER_LASTNAME = rs.getString("last_name");
				String USER_NAME = rs.getString("username");

				c = new CustomerDetails(USER_ID, USER_FIRSTNAME, USER_LASTNAME, ACCOUNT_ID, USER_NAME);
				System.out.println("USER CREATED");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return c;

	}

	@Override
	public CustomerDetails getUserbyUsername(String username) throws IOException {

		CustomerDetails c = null;
		// try-with-resources... resources included in the try args will be closed at
		// the end of the block
		// works with all AutoCloseable resources
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres(DATABASE_FIEDLS)) {
			String sql = "SELECT * FROM project0.user1 WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int USER_ID = rs.getInt("id");
				String USER_FIRSTNAME = rs.getString("first_name");
				String USER_LASTNAME = rs.getString("last_name");
				String USER_NAME = rs.getString("username");

				int ACCOUNT_ID = rs.getInt("acc_id");

				c = new CustomerDetails(USER_ID, USER_FIRSTNAME, USER_LASTNAME, ACCOUNT_ID, USER_NAME);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return c;
	
	}
}
