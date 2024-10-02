package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Connection connection;
	private String url;
	private String username;
	private String password;

	private DatabaseConnection() {
		loadConfig();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.err.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erreur de connexion à la base de données. " + e.getMessage());
		}
	}


	private void loadConfig() {
		Properties prop = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}
			prop.load(input);
			url = prop.getProperty("db.url");
			username = prop.getProperty("db.username");
			password = prop.getProperty("db.password");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DatabaseConnection getInstance()  {
		if (instance == null ) {
			instance = new DatabaseConnection();
		}
		return instance;
	}


	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Connection closed.");
			} catch (SQLException e) {
				System.err.println("Error in closing connection. " + e.getMessage());
			}
		}
	}
}