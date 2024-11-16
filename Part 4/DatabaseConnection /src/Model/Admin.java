package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents an administrator with basic CRUD operations.
 */
public class Admin {
    private int id;
    private String name;
    private String email;

    /**
     * Constructs a new Admin.
     *
     * @param id     the unique identifier of the admin
     * @param name   the name of the admin
     * @param email  the email of the admin
     */
    public Admin(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // CRUD Operations
    public boolean createAdmin(Connection connection) {
        String query = "INSERT INTO Admins (id, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setString(2, this.name);
            statement.setString(3, this.email);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Admin readAdmin(Connection connection, int adminId) {
        String query = "SELECT * FROM Admins WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Admin(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAdmin(Connection connection) {
        String query = "UPDATE Admins SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.name);
            statement.setString(2, this.email);
            statement.setInt(3, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAdmin(Connection connection, int adminId) {
        String query = "DELETE FROM Admins WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
