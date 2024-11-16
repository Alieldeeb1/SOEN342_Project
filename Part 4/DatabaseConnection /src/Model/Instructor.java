package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents an instructor who teaches lessons with CRUD operations.
 */
public class Instructor {
    private int id;
    private String name;
    private String phoneNumber;
    private String specialization;
    private String citiesAvailable;

    /**
     * Constructs a new Instructor.
     *
     * @param id              the unique identifier of the instructor
     * @param name            the name of the instructor
     * @param phoneNumber     the phone number of the instructor
     * @param specialization  the area of specialization of the instructor
     * @param citiesAvailable the cities where the instructor is available to teach
     */
    public Instructor(int id, String name, String phoneNumber, String specialization, String citiesAvailable) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.citiesAvailable = citiesAvailable;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getCitiesAvailable() { return citiesAvailable; }
    public void setCitiesAvailable(String citiesAvailable) { this.citiesAvailable = citiesAvailable; }

    // CRUD Operations
    public boolean createInstructor(Connection connection) {
        String query = "INSERT INTO Instructors (id, name, phone_number, specialization, cities_available) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setString(2, this.name);
            statement.setString(3, this.phoneNumber);
            statement.setString(4, this.specialization);
            statement.setString(5, this.citiesAvailable);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Instructor readInstructor(Connection connection, int instructorId) {
        String query = "SELECT * FROM Instructors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, instructorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Instructor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("specialization"),
                    resultSet.getString("cities_available")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInstructor(Connection connection) {
        String query = "UPDATE Instructors SET name = ?, phone_number = ?, specialization = ?, cities_available = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.name);
            statement.setString(2, this.phoneNumber);
            statement.setString(3, this.specialization);
            statement.setString(4, this.citiesAvailable);
            statement.setInt(5, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteInstructor(Connection connection, int instructorId) {
        String query = "DELETE FROM Instructors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, instructorId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
