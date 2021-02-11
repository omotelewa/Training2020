package marian.revature.com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConnectionUtil {

	public static final String DATABASE_PROPERTIES_WIN = "src\\main\\resources\\connection.properties";
	public static final String DATABASE_PROPERTIES_UNIX = "src/main/resources/connection.properties";
	public static Logger LOG = Logger.getLogger(ConnectionUtil.class.getName());

	public static Connection getConnectionfromPostgres() throws SQLException, IOException, ClassNotFoundException {
		Properties prop = new Properties();
		Class.forName("org.postgresql.Driver");
		// InputStream in = new FileInputStream(DATABASE_PROPERTIES);
		// prop.load(in);
		try (final InputStream inputStream = new FileInputStream(DATABASE_PROPERTIES_WIN)) {
			prop.load(inputStream);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			LOG.error("Could not load this properties file for WINDOWS... trying FOR UNIX ...");
			try (final InputStream inputStream = new FileInputStream(DATABASE_PROPERTIES_UNIX)) {
				prop.load(inputStream);
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
				System.err.println("Could not load this properties file for both OS.");
			}
		}

		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
				prop.getProperty("password"));
	}

	public static void createUserTable() {
		Connection c = null;
		Statement stmt = null;
		try {

			c = ConnectionUtil.getConnectionfromPostgres();
			LOG.info("Opened database successfully -- creating user table");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS project01.user " + "(user_id SERIAL PRIMARY KEY    NOT NULL,"
					+ " first_name          TEXT    NOT NULL, " + " username          TEXT    NOT NULL, "
					+ " password          TEXT    NOT NULL, " + " role          TEXT    NOT NULL )";
			stmt.executeUpdate(sql);
			LOG.info("User Table created successfully");
			stmt.close();
			c.close();
			LOG.info("Closed database successfully");
		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());

		}
	}

	public static void createBankAccountTable() {
		Connection c = null;
		Statement stmt = null;
		try {

			c = ConnectionUtil.getConnectionfromPostgres();
			LOG.info("Opened database successfully -- Creating Bank account table");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS project01.accounts "
					+ "(account_id 					SERIAL PRIMARY KEY    NOT NULL,"
					+ " user_id          				INT 	NOT NULL, "
					+ " account_balance            	REAL    NOT NULL, "
					+ " account_type        			TEXT NOT NULL, "
					+ " account_creation_date        	TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			LOG.info("Bank Account Table created successfully");
			stmt.close();
			c.close();
			LOG.info("Closed database successfully");

		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());

		}
	}

	public static void createAccountApplicationTable() {
		Connection c = null;
		Statement stmt = null;
		try {

			c = ConnectionUtil.getConnectionfromPostgres();
			LOG.info("Opened database successfully -- Creating Account application table");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS project01.account_application "
					+ "(application_id 					SERIAL PRIMARY KEY    NOT NULL,"
					+ " user_id          				INT 	NOT NULL, " + " status            	TEXT    NOT NULL, "
					+ " account_type        	TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			LOG.info("Account application Table created successfully");
			stmt.close();
			c.close();
			LOG.info("Closed database successfully");

		} catch (Exception e) {
			LOG.error(e.getClass().getName() + ": " + e.getMessage());

		}
	}

	public static String getCurrentDateString() {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		return formatter.format(d);
	}

	public static void createSchemas() {
		ConnectionUtil.createAccountApplicationTable();
		ConnectionUtil.createBankAccountTable();
		ConnectionUtil.createUserTable();

	}

}
