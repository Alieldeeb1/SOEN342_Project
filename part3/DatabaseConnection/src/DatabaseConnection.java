import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // Path to your SQLite database file
    private static final String URL = "jdbc:sqlite:/Users/alieldeeb/Downloads/sqlite-tools-osx-x64-3470000/mydatabase.db";

    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish connection
            Connection conn = DriverManager.getConnection(URL);
            if (conn != null) {
                System.out.println("Connected to the database successfully!");

                // Example statement to create a table (optional, remove if not needed)
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS test_table ("
                           + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                           + "name TEXT NOT NULL, "
                           + "age INTEGER"
                           + ");";
                stmt.executeUpdate(sql);
                System.out.println("Table created or already exists.");
                stmt.close();

                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. Please ensure the SQLite JDBC driver is added to the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error:");
            e.printStackTrace();
        }
    }
}

