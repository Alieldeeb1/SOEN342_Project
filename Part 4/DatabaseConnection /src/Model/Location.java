package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a physical location for lessons or events with CRUD operations.
 */
public class Location {
    private int id;
    private String name;
    private String city;
    private String type;
    private String address;

    /**
     * Constructs a new Location.
     *
     * @param id      the unique identifier of the location
     * @param name    the name of the location, must not be null or empty
     * @param city    the city where the location is, must not be null or empty
     * @param type    the type of the location
     * @param address the address of the location
     */
    public Location(int id, String name, String city, String type, String address) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City must not be null or empty");
        }
        this.id = id;
        this.name = name;
        this.city = city;
        this.type = type;
        this.address = address;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        this.name = name;
    }

    public String getCity() { return city; }
    public void setCity(String city) {
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City must not be null or empty");
        }
        this.city = city;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // CRUD Operations
    public boolean createLocation(Connection connection) {
        String query = "INSERT INTO Locations (id, name, city, type, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setString(2, this.name);
            statement.setString(3, this.city);
            statement.setString(4, this.type);
            statement.setString(5, this.address);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Location readLocation(Connection connection, int locationId) {
        String query = "SELECT * FROM Locations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Location(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getString("type"),
                    resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateLocation(Connection connection) {
        String query = "UPDATE Locations SET name = ?, city = ?, type = ?, address = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.name);
            statement.setString(2, this.city);
            statement.setString(3, this.type);
            statement.setString(4, this.address);
            statement.setInt(5, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteLocation(Connection connection, int locationId) {
        String query = "DELETE FROM Locations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, locationId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

