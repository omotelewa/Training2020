package marian.revature.com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {
	
	public static final String DATABASE_PROPERTIES = "src\\main\\resources\\connection.properties";

	public static Connection getConnectionfromPostgres() throws SQLException, IOException, ClassNotFoundException {
		Properties prop = new Properties();
		Class.forName("org.postgresql.Driver");
		InputStream in = new FileInputStream(DATABASE_PROPERTIES);
		prop.load(in);
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
				prop.getProperty("password"));
	}
	
	
	
	public static void createTable() {
		 Connection c = null;
	      Statement stmt = null;
	      try {
	      
	        c = ConnectionUtil.getConnectionfromPostgres();
	        System.out.println("Opened database successfully");

	        stmt = c.createStatement();
	        String sql = "CREATE TABLE project01.COMPANY " +
	            "(ID INT PRIMARY KEY    NOT NULL," +
	            " NAME          TEXT    NOT NULL, " +
	            " AGE            INT    NOT NULL, " +
	            " ADDRESS        CHAR(50), " +
	            " SALARY        REAL)";
	        stmt.executeUpdate(sql);
	        System.out.println("Table created successfully");
	        stmt.close();
	        c.close();
	      } catch ( Exception e ) {
	        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	    
	      }
	}

}
