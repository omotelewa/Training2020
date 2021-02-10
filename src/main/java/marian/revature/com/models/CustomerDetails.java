package marian.revature.com.models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerDetails {

	private int id;
	private int accid;
	private String fname;
	private String lname;
	private String userName;
	private Scanner dep;
	private Scanner dep2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return fname;
	}

	public void setName(String name) {
		this.fname = name;
	}

	@Override
	public String toString() {
		return "Employee emp_id=" + id + ", name=" + fname + " " + lname + ", acc#=" + accid;
	}

	@Override
	public int hashCode() {
		final double prime = 31;
		double result = 1;
		result = prime * result + id;

		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetails other = (CustomerDetails) obj;
		if (id != other.id)
			return false;

		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		return true;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAccid() {
		return accid;
	}

	public void setAccid(int accid) {
		this.accid = accid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void CheckBalance() {

		AccountBalance.getBalance(accid);
	}

	public void Deposit(int ACCOUNT_ID) throws SQLException, IOException {

		dep2 = new Scanner(System.in);
		float deposit = 0;
		boolean isfloat = false;

		while (isfloat == false) {
			try {
				System.out.println("How much would you like to deposit?");
				String tempdep = dep2.nextLine();
				deposit = Float.parseFloat(tempdep);
				isfloat = true;
			} catch (InputMismatchException d) {

			} catch (NumberFormatException e) {
			}
		}

		AccountBalance.DepositBalance(deposit, ACCOUNT_ID);		AccountBalance.DepositBalance(deposit, ACCOUNT_ID);

	}
	public CustomerDetails(int USER_ID, String USER_FIRSTNAME, String USER_LASTNAME, int ACCOUNT_ID, String USER_NAME) {
		super();
		this.id = USER_ID;
		this.setAccid(ACCOUNT_ID);
		this.setUserName(USER_NAME);

		this.fname = USER_FIRSTNAME;
		this.lname = USER_LASTNAME;

	}

	public void Withdraw(int ACCOUNT_ID) throws SQLException, IOException {

		dep = new Scanner(System.in);

		float withdrawal = 0;
		boolean isfloat = false;
		while (isfloat == false) {
			try {
				System.out.println("How much would you like to Withdraw?");
				String tempwith = dep.nextLine();
				withdrawal = Float.parseFloat(tempwith);
				isfloat = true;
			} catch (InputMismatchException d) {

			} catch (NumberFormatException e) {
			}
		}
		AccountBalance.withdrawBalance(withdrawal, ACCOUNT_ID);

	}

}
