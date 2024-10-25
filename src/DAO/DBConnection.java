package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Campeonato";
    private static final String username = "postgres";
    private static final String password = "1234"; //niidea2004
    private static final String driver = "org.postgresql.Driver";

    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return DriverManager.getConnection(jdbcUrl, username, password);
    }

}
