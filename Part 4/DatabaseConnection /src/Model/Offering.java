package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;


/**
 * Represents an offering for a lesson or event with CRUD operations.
 */
public class Offering {
    private int id;
    private int locationId;
    private String lessonType;
    private int instructorId;
    private int scheduleId;
    private boolean isGroup;

    /**
     * Constructs a new Offering.
     *
     * @param id           the unique identifier of the offering
     * @param locationId   the ID of the location where the offering takes place
     * @param lessonType   the type of lesson being offered
     * @param instructorId the ID of the instructor teaching the lesson
     * @param scheduleId   the ID of the schedule associated with the offering
     * @param isGroup      whether the offering is a group lesson or not
     */
    public Offering(int id, int locationId, String lessonType, int instructorId, int scheduleId, boolean isGroup) {
        this.id = id;
        this.locationId = locationId;
        this.lessonType = lessonType;
        this.instructorId = instructorId;
        this.scheduleId = scheduleId;
        this.isGroup = isGroup;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }

    public String getLessonType() { return lessonType; }
    public void setLessonType(String lessonType) { this.lessonType = lessonType; }

    public int getInstructorId() { return instructorId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }

    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public boolean isGroup() { return isGroup; }
    public void setGroup(boolean group) { isGroup = group; }

    // CRUD Operations
    public boolean createOffering(Connection connection) {
        String query = "INSERT INTO Offerings (id, location_id, lesson_type, instructor_id, schedule_id, is_group) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setInt(2, this.locationId);
            statement.setString(3, this.lessonType);
            statement.setInt(4, this.instructorId);
            statement.setInt(5, this.scheduleId);
            statement.setBoolean(6, this.isGroup);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Offering readOffering(Connection connection, int offeringId) {
        String query = "SELECT * FROM Offerings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, offeringId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Offering(
                    resultSet.getInt("id"),
                    resultSet.getInt("location_id"),
                    resultSet.getString("lesson_type"),
                    resultSet.getInt("instructor_id"),
                    resultSet.getInt("schedule_id"),
                    resultSet.getBoolean("is_group")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateOffering(Connection connection) {
        String query = "UPDATE Offerings SET location_id = ?, lesson_type = ?, instructor_id = ?, schedule_id = ?, is_group = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.locationId);
            statement.setString(2, this.lessonType);
            statement.setInt(3, this.instructorId);
            statement.setInt(4, this.scheduleId);
            statement.setBoolean(5, this.isGroup);
            statement.setInt(6, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteOffering(Connection connection, int offeringId) {
        String query = "DELETE FROM Offerings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, offeringId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    public static List<Offering> getAllOfferings(Connection connection) {
        List<Offering> offerings = new ArrayList<>();
        String query = "SELECT * FROM Offerings";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                int locationId = rs.getInt("location_id");
                String lessonType = rs.getString("lesson_type");
                int instructorId = rs.getInt("instructor_id");
                int scheduleId = rs.getInt("schedule_id");
                boolean isGroup = rs.getBoolean("is_group");
                offerings.add(new Offering(id, locationId, lessonType, instructorId, scheduleId, isGroup));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving offerings from the database:");
            e.printStackTrace();
        }
        return offerings;
    }


}
