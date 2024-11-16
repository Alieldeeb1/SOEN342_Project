package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;


/**
 * Represents a client who books lessons with CRUD operations.
 */
public class Client {
    private int id;
    private String name;
    private int age;
    private Integer guardianId;
    private String phoneNumber;

    /**
     * Constructs a new Client.
     *
     * @param id           the unique identifier of the client
     * @param name         the name of the client
     * @param age          the age of the client
     * @param guardianId   the ID of the client's guardian, if applicable
     * @param phoneNumber  the phone number of the client
     */
    public Client(int id, String name, int age, Integer guardianId, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Integer getGuardianId() { return guardianId; }
    public void setGuardianId(Integer guardianId) { this.guardianId = guardianId; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // CRUD Operations
    public boolean createClient(Connection connection) {
        String query = "INSERT INTO Clients (id, name, age, guardian_id, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setString(2, this.name);
            statement.setInt(3, this.age);
            if (this.guardianId != null) {
                statement.setInt(4, this.guardianId);
            } else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }
            statement.setString(5, this.phoneNumber);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Client readClient(Connection connection, int clientId) {
        String query = "SELECT * FROM Clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getObject("guardian_id") != null ? resultSet.getInt("guardian_id") : null,
                    resultSet.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateClient(Connection connection) {
        String query = "UPDATE Clients SET name = ?, age = ?, guardian_id = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.name);
            statement.setInt(2, this.age);
            if (this.guardianId != null) {
                statement.setInt(3, this.guardianId);
            } else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }
            statement.setString(4, this.phoneNumber);
            statement.setInt(5, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteClient(Connection connection, int clientId) {
        String query = "DELETE FROM Clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Client> getAllClients(Connection connection) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Integer guardianId = rs.getObject("guardian_id") != null ? rs.getInt("guardian_id") : null;
                String phoneNumber = rs.getString("phone_number");
                clients.add(new Client(id, name, age, guardianId, phoneNumber));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving clients from the database:");
            e.printStackTrace();
        }
        return clients;
    }

}


