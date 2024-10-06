package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class for managing database connections.
 * This class provides methods to establish, retrieve, and close database connections.
 */
public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Connection connection;
	private String url;
	private String username;
	private String password;

	/**
	 * Private constructor to prevent instantiation.
	 * Loads configuration and establishes a database connection.
	 */
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

	/**
	 * Loads database configuration from a properties file.
	 */
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

	/**
	 * Returns the database connection.
	 *
	 * @return The Connection object representing the database connection.
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Returns the singleton instance of DatabaseConnection.
	 *
	 * @return The DatabaseConnection instance.
	 */
	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	/**
	 * Closes the database connection.
	 */
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