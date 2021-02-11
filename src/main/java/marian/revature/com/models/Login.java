package marian.revature.com.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import marian.revature.com.dao.UserDaoImpl;
import marian.revature.com.utility.ConnectionUtil;
import marian.revature.com.utility.InputValidationUtil;

public class Login {

	private User currentUser = null;
	private String choice;
	private String username = null;
	private String password = null;
	private boolean choices = true;
	private Scanner accin;
	private int accountAttempt = 3; 
	private boolean loggedin = false;
	public static Logger LOG = Logger.getLogger(Login.class.getName());  
	private UserDaoImpl userDaoImpl = new UserDaoImpl();


	public Login() throws SQLException, IOException, InterruptedException {

		/*
		
		
		System.out.println(c.getUserName());
		if (c.getUserName().contains("admin")) {
			choices = true;

			while (choices) {
				System.out.println("What would you like to do today?");
				this.accin = new Scanner(System.in);

				System.out.println("Please enter your selection (case sensitive)");

				System.out.println(
						"enter :\n'check balance'\n'deposit'\n'withdraw'\n'view all accounts'\n'view account by ID'\n'delete account'\n'view transactions by ID'\n'logout' ");
				choice = accin.nextLine();

				switch (choice) {
				case "check balance":
					float bal = (float) AccountBalance.getBalance(c.getAccid());
					System.out.println("Your balance is : $" + bal);

					break;

				case "deposit":

					c.Deposit(c.getAccid());
					break;
				case "withdraw":
					c.Withdraw(c.getAccid());
				case "logout":
					choices = false;
					break;
				case "view all accounts":
					Admin.ViewAllAccounts();
					break;
				case "delete account":
					Scanner dep2 = new Scanner(System.in);
					int accid = 0;
					boolean isint = false;

					while (isint == false) {
						try {
							System.out.println("Please enter the users account ID");
							String tempdep = dep2.nextLine();
							accid = Integer.parseInt(tempdep);
							isint = true;
							Admin.DeleteAccount(accid);
						} catch (InputMismatchException d) {

						} catch (NumberFormatException e) {
						}
					}

				case "view transactions by ID":
					Scanner dep4 = new Scanner(System.in);
					int accid2 = 0;
					boolean isint2 = false;

					while (isint2 == false) {
						try {
							System.out.println("Please enter the users account ID");
							String tempdep = dep4.nextLine();
							accid2 = Integer.parseInt(tempdep);
							isint2 = true;
							Admin.viewTransactions(accid2);
						} catch (InputMismatchException d) {

						} catch (NumberFormatException e) {
						}
					}

				case "view account by ID":

					Scanner dep3 = new Scanner(System.in);
					int accid1 = 0;
					boolean isint1 = false;

					while (isint1 == false) {
						try {
							System.out.println("Please enter the users account ID");
							String tempdep = dep3.nextLine();
							accid1 = Integer.parseInt(tempdep);
							isint1 = true;
							Admin.DeleteAccount(accid1);
						} catch (InputMismatchException d) {

						} catch (NumberFormatException e) {
						}
					}

				}
			}

		} else {
			choices = true;

			while (choices) {
				System.out.println("What would you like to do today?");

				System.out.println("Please enter your selection (case sensitive)");

				System.out.println("enter :\n'check balance'\n'deposit'\n'withdraw',\n'logout' ");
				choice = this.accin.nextLine();

				switch (choice) {
				case "check balance":
					c.CheckBalance();

					break;

				case "deposit":

					c.Deposit(c.getAccid());
					break;
				case "withdraw":
					c.Withdraw(c.getAccid());
				case "logout":
					choices = false;
					break;

				default:
					System.out.println("please enter again ");
					boolean repeat = true;

					while (repeat) {
						choice = this.accin.nextLine();

						switch (choice) {

						case "check balance":
							repeat = false;
							c.CheckBalance();

							break;

						case "deposit":
							repeat = false;

							c.Deposit(c.getAccid());
							break;
						case "withdraw":
							repeat = false;
							c.Withdraw(c.getAccid());
						case "logout":
							choices = false;
							repeat = false;
							break;
						}
					}
					break;
				}
			}

		}
		*/

	}

	/*
	public Login(CustomerDetails c) throws SQLException, IOException {

		// TODO Auto-generated method stub

		String choice;

		boolean choices = true;

		while (choices) {
			System.out.println("What would you like to do today?");

			System.out.println("Please enter your selection (case sensitive)");

			System.out.println("enter : 'check balance','deposit','withdraw',or 'logout' ");
			choice = accin.nextLine();

			switch (choice) {
			case "check balance":
				float bal = (float) AccountBalance.getBalance(c.getAccid());
				System.out.println("Your balance is : $" + bal);

				break;

			case "deposit":

				c.Deposit(c.getAccid());
				break;
			case "withdraw":
				c.Withdraw(c.getAccid());
			case "logout":
				choices = false;
				break;

			default:
				System.out.println("please enter again ");
				boolean repeat = true;

				while (repeat) {
					System.out.println("enter : 'check balance','deposit','withdraw',or 'logout' ");
					choice = accin.nextLine();

					switch (choice) {

					case "check balance":
						repeat = false;
						c.CheckBalance();

						break;

					case "deposit":
						repeat = false;

						c.Deposit(c.getAccid());
						break;
					case "withdraw":
						repeat = false;
						c.Withdraw(c.getAccid());
					case "logout":
						choices = false;
						repeat = false;
						break;
					}
				}
				break;
			}
		}

	}*/

	// every action is failed, success, or another action... 
	public String loginAttempt(){
		loginAction();
		
		while (true){
			if (accountAttempt == 0){
				System.out.println("Too many failed login attempts");
				String response = InputValidationUtil.promptUser("Please create an Account ... or try to LOGIN AGAIN.. Enter create/login", "create", "login"); 
				if (response == null){
					// user quit
					return "failed"; 
				}else if (response.contentEquals("login")){
					accountAttempt = 3; 
					return loginAttempt();
				}else { // then user wants to create
					return "create"; 
				}
			}else {
				// successful login... 
				return "success";
			}
		}
	}
	
	public User getLoggedInUser() {
		return currentUser; 
		
	}
	private void loginAction(){
		// User Login
		while (choices && accountAttempt != 0) {
			try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
				promptUserNameAndPass();
				boolean validLogin = authenticateUser(conn, this.username, this.password);
				if (validLogin){
					LOG.info("Successful login");
					choices = false;
					loggedin = true;
				}else {
					choices = true; 
					accountAttempt--; 
					LOG.info("Please try to login again... Accounts Attempts LEFT " + accountAttempt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void promptUserNameAndPass() {
		System.out.println("Please enter your username");
		this.accin = new Scanner(System.in);
		this.username = accin.nextLine();
		System.out.println("Please enter your password");
		this.password = accin.nextLine();
		this.accin.close();
	} // end of method


	
	
	
	
	private boolean authenticateUser(Connection conn, String username, String password) {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT COUNT(user_name) AS COUNT FROM project01.user WHERE user_name = ? AND PASSWORD = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username.toLowerCase());
			pstmt.setString(2, password.toLowerCase());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int checker = rs.getInt("COUNT");
			if (checker == 1) {
				LOG.info("Success! Logging You in");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".");
				System.out.println(" ");
				// choices = false;
				result = true; // logged in
				this.currentUser = userDaoImpl.getUserbyUsername(username); 
				
			} else {
				LOG.info("Username and Password Not Found");
				result = false; //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}// end of method

}
