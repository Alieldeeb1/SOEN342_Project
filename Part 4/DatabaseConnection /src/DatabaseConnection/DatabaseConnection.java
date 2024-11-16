package DatabaseConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Path to your SQLite database file
   // private static final String URL = "jdbc:sqlite:/Users/alieldeeb/Downloads/sqlite-tools-osx-x64-3470000/mydatabase.db";
    private static final String URL = "jdbc:sqlite:/Users/alieldeeb/Downloads/sqlite-tools-osx-x64-3470000/mydatabase.db";

    // Method to establish a connection to the database
    public static Connection getConnection() {
        System.out.println("Database path: " + URL); // Add this line to verify the path
        Connection conn = null;
         
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection established successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. Please ensure the SQLite JDBC driver is added to the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error:");
            e.printStackTrace();
        }
        return conn;
    }


    // Method to close a given connection
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing the database connection:");
                e.printStackTrace();
            }
        }
    }
}
