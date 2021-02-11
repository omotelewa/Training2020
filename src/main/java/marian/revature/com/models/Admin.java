package marian.revature.com.models;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import marian.revature.com.utility.ConnectionUtil;

public class Admin {

	
	public static void ViewAllAccounts() {
		
		
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project0.accounts A INNER JOIN project0.user1 B ON A.acc_id = B.acc_id ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				float balance = rs.getFloat("BALANCE");
				
				int id = rs.getInt("id");
				int Accid = rs.getInt("acc_id");
				String Name= rs.getString("username");

				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				
				System.out.println("USER ID:"+id +" | USER ACCOUNT:"+ Accid +" | USER NAME:"+ Name+" | NAME:" + fname +" "+ lname + " | BALANCE:" + balance);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	}
public static void DeleteAccount(int accid) {
		
	float balance = 0;
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project0.accounts A INNER JOIN project0.user1 B ON A.acc_id = B.acc_id WHERE A.acc_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

			     balance = rs.getFloat("BALANCE");
				
				int id = rs.getInt("id");
				int Accid = rs.getInt("acc_id");
				String Name= rs.getString("username");

				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				
				System.out.println("USER ID:"+id +" | USER ACCOUNT:"+ Accid +" | USER NAME:"+ Name+" | NAME:" + fname +" "+ lname + " | BALANCE:" + balance);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
	if (balance == 0) {
		
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "DELETE * FROM project0.accounts WHERE acc_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accid);
			pstmt.execute();
			sql = "DELETE * FROM project0.accounts WHERE acc_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accid);
			pstmt.execute();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	else {System.out.println("");
		System.out.println("cannot delete user. The user has funds in their account");}
		
	}

static void viewAccount(int accid) {
	

	try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
		String sql = "SELECT * FROM project0.accounts A INNER JOIN project0.user1 B ON A.acc_id = B.acc_id WHERE A.acc_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, accid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {

			float balance = rs.getFloat("BALANCE");
			
			int id = rs.getInt("id");
			int Accid = rs.getInt("acc_id");
			String Name= rs.getString("username");

			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			
			System.out.println("USER ID:"+id +" | USER ACCOUNT:"+ Accid +" | USER NAME:"+ Name+" | NAME:" + fname +" "+ lname + " | BALANCE:" + balance);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	}
	
		
}

static void viewTransactions(int accid) {
	

	try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
		String sql = "SELECT * FROM project0.TRANSACTIONS WHERE acc_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, accid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {

			float balance = rs.getFloat("AMOUNT");
			
			
			int Accid = rs.getInt("acc_id");
			String TIME= rs.getString("TIME");

			int TRANSACTIONID = rs.getInt("TRANSID");
			
			
			System.out.println("USER ACCOUNT:"+ Accid +" | TIME:" + TIME +" "+  " | AMOUNT:" + balance+  " | TRANSACTION ID:" + TRANSACTIONID);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	}
	
	
	
	
	
	
}
	
}
