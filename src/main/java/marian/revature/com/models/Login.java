package marian.revature.com.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import marian.revature.com.utility.ConnectionUtil;

public class Login {

	private CustomerDetails c = null;
	private String choice;
	private String username = null;
	private String password = null;
	private boolean choices = true;

	public Login() throws SQLException, IOException, InterruptedException {
		
		while (choices) {
			promptUser();

			try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
				authenticateUser(conn);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project0.user1 A INNER JOIN project0.accounts B ON A.acc_id = B.acc_id WHERE username = ? AND PASSWORD = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int ACCOUNT_ID = rs.getInt("acc_id");

				int USER_ID = rs.getInt("id");
				String USER_FIRSTNAME = rs.getString("first_name");
				String USER_LASTNAME = rs.getString("last_name");
				String USER_NAME = rs.getString("username");
				c = new CustomerDetails(USER_ID, USER_FIRSTNAME, USER_LASTNAME, ACCOUNT_ID, USER_NAME);

			}

		}
		System.out.println(c.getUserName());
		if (c.getUserName().contains("admin")) {
			choices = true;

			while (choices) {
				System.out.println("What would you like to do today?");
				Scanner accin = new Scanner(System.in);

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
				Scanner accin = new Scanner(System.in);

				System.out.println("Please enter your selection (case sensitive)");

				System.out.println("enter :\n'check balance'\n'deposit'\n'withdraw',\n'logout' ");
				choice = accin.nextLine();

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

		}

	}

	public Login(CustomerDetails c) throws SQLException, IOException {

		// TODO Auto-generated method stub

		String choice;

		boolean choices = true;

		while (choices) {
			System.out.println("What would you like to do today?");
			Scanner accin = new Scanner(System.in);

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

	}

	private void promptUser(){
		System.out.println("Please enter your username");
			Scanner accin = new Scanner(System.in);
			this.username = accin.nextLine();
			System.out.println("Please enter your password");
			this.password = accin.nextLine();
	} // end of method

	private boolean authenticateUser(Connection conn){
		boolean result = false;
		try {
			String sql = "SELECT COUNT(username) AS COUNT FROM project0.user1 A INNER JOIN project0.accounts B ON A.acc_id = B.acc_id WHERE username = ? AND PASSWORD = ? GROUP BY A.username";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int checker = rs.getInt("COUNT");

				if (checker == 1) {
					
					System.out.println("Logging You in");
					Thread.sleep(1000);
					System.out.print(".");
					Thread.sleep(1000);
					System.out.print(".");
					Thread.sleep(1000);
					System.out.print(".");
					Thread.sleep(1000);
					System.out.print(".");
					System.out.println(" ");

					// choices = false;
					result = true; // logged in

				} else {
					System.out.println("Username and Password Not Found");
					result = false; // 
				}
			}

			
		}catch (SQLException e){
			e.printStackTrace();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		return result;
		
	}// end of method

}
