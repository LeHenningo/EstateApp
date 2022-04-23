package de.dis.data;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Einfaches Singleton zur Verwaltung von Datenbank-Verbindungen.
 * 
 * @author Michael von Riegen
 * @version April 2009
 */
public class DbConnectionManager {

	// instance of Driver Manager
	private static DbConnectionManager _instance = null;

	// DB connection
	private Connection _con;

	/**
	 * Erzeugt eine Datenbank-Verbindung
	 */
	private DbConnectionManager() {
		try {
			// Holen der Einstellungen aus der db.properties Datei
			Properties properties = new Properties();
			FileInputStream stream = new FileInputStream(new File("db.properties"));
			properties.load(stream);
			stream.close();

			String jdbcUser = properties.getProperty("jdbc_user");
			String jdbcPass = properties.getProperty("jdbc_pass");
			String jdbcUrl = properties.getProperty("jdbc_url");
			// Verbindung zur Datenbank herstellen
			_con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Liefert Instanz des Managers
	 * 
	 * @return DBConnectionManager
	 */
	public static DbConnectionManager getInstance() {
		if (_instance == null) {
			_instance = new DbConnectionManager();
		}
		return _instance;
	}

	/**
	 * Liefert eine Verbindung zur Datenbank zurC<ck
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		return _con;
	}

}
