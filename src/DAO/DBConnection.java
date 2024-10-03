package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Campeonato";
    private static final String username = "postgres";
    private static final String password = "niidea2004"; //niidea2004

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

}
