package marian.revature.com.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import marian.revature.com.utility.ConnectionUtil;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBalance {
	private static Connection con;

	public static double getBalance(int id) {
		double balance = 0;
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT BALANCE FROM project0.ACCOUNTs A INNER JOIN project0.user1 B ON A.acc_id = B.acc_id WHERE A.acc_id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				balance = rs.getFloat("BALANCE");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

		return balance;
	}

	public static void withdrawBalance(float withdrawal, int ACCOUNT_ID) throws SQLException, IOException {
		double balance = 0;

		balance = AccountBalance.getBalance(ACCOUNT_ID);

		System.out.println(balance);
		if ((balance > withdrawal) && (withdrawal > 0)) {
			try (Connection con = ConnectionUtil.getConnectionfromPostgres()) {

				balance = balance - withdrawal;
				CallableStatement cstmt = con.prepareCall("UPDATE project0.accounts SET BALANCE = ? WHERE acc_id = ?");
				cstmt.setDouble(1, balance);
				cstmt.setInt(2, ACCOUNT_ID);
				cstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

		else
			System.out.println("Insufficient funds to complete transaction");

		try (Connection con = ConnectionUtil.getConnectionfromPostgres()) {

			CallableStatement cstmt = con.prepareCall("INSERT INTO project0.TRANSACTIONS\r\n" + "(AMOUNT, acc_id,TIME )\r\n"
					+ "VALUES\r\n" + "(?, ?, to_date(?,'DD/MM/YYYY'))");
			cstmt.setDouble(1, withdrawal * -1);
			cstmt.setInt(2, ACCOUNT_ID);
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(date);
			cstmt.setString(3, strDate);
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("Cash Withdrawn" + "");

	}

	public static void DepositBalance(float deposit, int ACCOUNT_ID) throws SQLException, IOException {
		double balance = 0;

		balance = AccountBalance.getBalance(ACCOUNT_ID);

		try (Connection con = ConnectionUtil.getConnectionfromPostgres()) {

			balance = balance + deposit;
			CallableStatement cstmt = con.prepareCall("UPDATE project0.accounts SET BALANCE = ? WHERE acc_id = ?");
			cstmt.setDouble(1, balance);
			cstmt.setInt(2, ACCOUNT_ID);
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		balance = AccountBalance.getBalance(ACCOUNT_ID);

		System.out.println(balance);

		try (Connection con = ConnectionUtil.getConnectionfromPostgres()) {

			balance = balance + deposit;
			CallableStatement cstmt = con.prepareCall("INSERT INTO project0.TRANSACTIONS\r\n" + "(AMOUNT, acc_id,TIME )\r\n"
					+ "VALUES\r\n" + "(?, ?, to_date(?,'DD/MM/YYYY'))");
			cstmt.setDouble(1, deposit);
			cstmt.setInt(2, ACCOUNT_ID);
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(date);
			cstmt.setString(3, strDate);
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("Cash Deposited");

	}
}