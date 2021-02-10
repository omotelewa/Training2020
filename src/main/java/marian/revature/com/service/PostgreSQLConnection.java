package marian.revature.com.service;

import java.sql.Connection;
import java.sql.DriverManager;

public final class PostgreSQLConnection {

	private PostgreSQLConnection() {

	}

	private static Connection conn = null;

	public static Connection getConnectionInstance() {
		try {
			Class.forName("org.postgresql.Driver");
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
			}
			System.out.println("Connected to database successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return conn;
	}
}
