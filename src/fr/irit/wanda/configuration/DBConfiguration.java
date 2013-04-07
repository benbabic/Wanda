/**
 * 
 * @author Benjamin Babic 
 * 
 */

package fr.irit.wanda.configuration;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configuration Singleton used to set up a connection to the database specified
 * in the configuration file.<br>
 * The default configuration file path is described by CONFIGURATION_FILE_PATH<br>
 * <br>
 * Note: : The configuration file will be automatically generated with the
 * default values if it does not exist on the server.
 */
public final class DBConfiguration { /* Singleton pattern for configuration file. */

	private static Logger logger = Logger.getLogger("DBConfiguration");
	private static final String CONFIGURATION_FILE_PATH = "db.connect";
	private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/Wanda";
	private static final String DEFAULT_DRIVER = "org.postgresql.Driver";
	private static final String DEFAULT_LOGIN = "postgres";
	private static final String DEFAULT_PASSWORD = "12556633";

	private static volatile DBConfiguration instance = null;
	private String url = "";
	private String login = "";
	private String password = "";
	private String driver = "";
	private Connection connection = null;

	/*
	 * Creates the singleton Configuration.<br> Should be launched only by the
	 * getInstance method.<br> Follows the singleton pattern. Configuration
	 * creation never fails, if it isn't loaded and if it will be logged and the
	 * default options will be forced.
	 */
	private DBConfiguration() {
		loadCfg();
		try {
			Class.forName(getDriver());
			connection = (DriverManager.getConnection(getUrl(), getLogin(),
					getPassword()));
		} catch (SQLException e) {
			logger.severe("Unable to set the connection to the database.");
			logger.fine("Error " + e.getErrorCode() + ": " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.severe("Unable to load the following driver class:"
					+ getDriver());
		}
	}

	/*
	 * Logger initialization
	 */
	private static void initiateLogger() {
		if (logger.getLevel() == null) {
			logger.setLevel(Level.ALL);
		}
	}

	/*
	 * Loads the configuration file in the single Configuration instance. If the
	 * file was not found it generates the file using a private method.
	 */
	private void loadCfg() {
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(CONFIGURATION_FILE_PATH);
			prop.load(in);
			this.url = prop.getProperty("Url");
			this.login = prop.getProperty("Login");
			this.driver = prop.getProperty("Driver");
			this.password = prop.getProperty("Password");
			in.close();
		} catch (Exception e) { // if the file was not found
			logger.warning("File not found: " + CONFIGURATION_FILE_PATH);
			logger.info("Generating default configuration");
			generateDefaultCfg(); // we try to generate it
		}
	}

	/*
	 * Generates and Loads the default configuration
	 */
	private void generateDefaultCfg() {
		Properties prop = new Properties();
		File config = new File(CONFIGURATION_FILE_PATH);

		try {
			FileOutputStream out = new FileOutputStream(config);
			prop.setProperty("Url", DEFAULT_URL);
			prop.setProperty("Driver", DEFAULT_DRIVER);
			prop.setProperty("Login", DEFAULT_LOGIN);
			prop.setProperty("Password", DEFAULT_PASSWORD);
			prop.store(out, "[AUTO-GENERATED CONFIGURATION FILE]");
			out.flush();
			out.close();
			logger.info("Default configuration file was sucessfully generated if you face problems connecting to database try to edit it.");
		} catch (IOException e1) {
			logger.warning("Unable to generate default configuration file, you may not have sufficiant permissions.");
			logger.info("Hard forcing configuration.");
		}
		forceDefaultCfg(); // We load the generated configuration
	}

	/*
	 * Loads the default configuration
	 */
	private void forceDefaultCfg() {
		this.url = DEFAULT_URL;
		this.login = DEFAULT_LOGIN;
		this.driver = DEFAULT_DRIVER;
		this.password = DEFAULT_PASSWORD;
	}

	/**
	 * Creates the unique instance of Configuration if it does not already
	 * exist.
	 * 
	 * @return Configuration instance using Singleton Pattern
	 */
	public final static DBConfiguration getInstance() {
		initiateLogger();
		if (instance == null) // we try to avoid using synchronized
		{
			synchronized (DBConfiguration.class) {
				if (instance == null)
					instance = new DBConfiguration();
			}
		}
		return instance;
	}

	/**
	 * Returns the URL within the configuration
	 * 
	 * @return the URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the login within the configuration
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Returns the password within the configuration
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the driver name within the configuration
	 * 
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
}