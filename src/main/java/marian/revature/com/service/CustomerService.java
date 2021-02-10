package marian.revature.com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerService {
	
	public void createAllTables(Connection c) {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE project01.CUSTOMER " +
		            "(ID INT PRIMARY KEY    NOT NULL," +
		            " NAME          TEXT    NOT NULL, " +
		            " DOB          TEXT    NOT NULL, "+
		            " LUSERNAME          TEXT    NOT NULL, "+
		            " LPASSWORD          TEXT    NOT NULL, "+
		            " AGE            INT    NOT NULL, " +
		            " ADDRESS        CHAR(50))";
		        stmt.executeUpdate(sql);
		        stmt.close();
		        c.close();
		} catch (SQLException e) {
			System.out.println("Table creation operation failed");
		}
        
	}
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;
	public static void newCustomer(String name, String DOB,String ADDRESS, String AGE, String LUSERNAME,String LPASSWORD)
	{
		try {
			con = PostgreSQLConnection.getConnectionInstance();
			stmt = con.createStatement();
			String query = String.format("INSERT INTO project01.CUSTOMER (NAME, DOB, AGE, ADDRESS,LUSERNAME,LPASSWORD) VALUES ('%s','%s', %s,%s,%s,%s)", name, DOB,AGE,ADDRESS,LUSERNAME,LPASSWORD);
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				String id = rs.getString(1);
				String output = String.format(":: CREATE NEW CUSTOMER #%s - SUCCESS\n",id);
				System.out.println(output);
			}
			con.close();
		} catch (Exception err) {
			System.out.println(":: CREATE NEW CUSTOMER - FAILURE");
			System.out.println(err);
			System.out.println();
		}
	}
	public static boolean authCustomer(String id, String password,String loginuser)
	{
		try {
			con = PostgreSQLConnection.getConnectionInstance();
			stmt = con.createStatement();
			String query = String.format("SELECT * FROM project01.CUSTOMER WHERE (ID=%s AND LUSERNAME=%s AND LPASSWORD=%s)", id, loginuser,password);
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				String output = String.format(":: AUTHENTICATED CUSTOMER #%s - SUCCESS\n",id);
				System.out.println(output);
				con.close();
				return true;
			}
			con.close();
		} catch (Exception err) {
			System.out.println(":: AUTHENTICATED CUSTOMER - FAILURE");
			System.out.println(err);
			System.out.println();
		}
		return false;
	}

}
