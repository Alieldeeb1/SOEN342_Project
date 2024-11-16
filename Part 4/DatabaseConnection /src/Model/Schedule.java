package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a schedule for a specific location with CRUD operations.
 */
public class Schedule {
    private int id;
    private int locationId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private boolean availability;

    /**
     * Constructs a new Schedule.
     *
     * @param id          the unique identifier of the schedule
     * @param locationId  the ID of the location the schedule is associated with
     * @param dayOfWeek   the day of the week
     * @param startTime   the start time of the schedule
     * @param endTime     the end time of the schedule
     * @param availability the availability status of the schedule
     */
    public Schedule(int id, int locationId, String dayOfWeek, String startTime, String endTime, boolean availability) {
        this.id = id;
        this.locationId = locationId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availability = availability;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }

    // CRUD Operations
    public boolean createSchedule(Connection connection) {
        String query = "INSERT INTO Schedules (id, location_id, day_of_week, start_time, end_time, availability) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setInt(2, this.locationId);
            statement.setString(3, this.dayOfWeek);
            statement.setString(4, this.startTime);
            statement.setString(5, this.endTime);
            statement.setBoolean(6, this.availability);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Schedule readSchedule(Connection connection, int scheduleId) {
        String query = "SELECT * FROM Schedules WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, scheduleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Schedule(
                    resultSet.getInt("id"),
                    resultSet.getInt("location_id"),
                    resultSet.getString("day_of_week"),
                    resultSet.getString("start_time"),
                    resultSet.getString("end_time"),
                    resultSet.getBoolean("availability")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSchedule(Connection connection) {
        String query = "UPDATE Schedules SET location_id = ?, day_of_week = ?, start_time = ?, end_time = ?, availability = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.locationId);
            statement.setString(2, this.dayOfWeek);
            statement.setString(3, this.startTime);
            statement.setString(4, this.endTime);
            statement.setBoolean(5, this.availability);
            statement.setInt(6, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSchedule(Connection connection, int scheduleId) {
        String query = "DELETE FROM Schedules WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, scheduleId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

