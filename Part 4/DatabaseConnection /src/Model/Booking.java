package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a booking for a lesson or event with CRUD operations.
 */
public class Booking {
    private int id;
    private int clientId;
    private int offeringId;
    private String bookingDate;
    private String status;

    /**
     * Constructs a new Booking.
     *
     * @param id           the unique identifier of the booking
     * @param clientId     the ID of the client who made the booking
     * @param offeringId   the ID of the offering being booked
     * @param bookingDate  the date of the booking
     * @param status       the status of the booking
     */
    public Booking(int id, int clientId, int offeringId, String bookingDate, String status) {
        this.id = id;
        this.clientId = clientId;
        this.offeringId = offeringId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public int getOfferingId() { return offeringId; }
    public void setOfferingId(int offeringId) { this.offeringId = offeringId; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // CRUD Operations
    public boolean createBooking(Connection connection) {
        String query = "INSERT INTO Bookings (id, client_id, offering_id, booking_date, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.id);
            statement.setInt(2, this.clientId);
            statement.setInt(3, this.offeringId);
            statement.setString(4, this.bookingDate);
            statement.setString(5, this.status);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Booking readBooking(Connection connection, int bookingId) {
        String query = "SELECT * FROM Bookings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Booking(
                    resultSet.getInt("id"),
                    resultSet.getInt("client_id"),
                    resultSet.getInt("offering_id"),
                    resultSet.getString("booking_date"),
                    resultSet.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBooking(Connection connection) {
        String query = "UPDATE Bookings SET client_id = ?, offering_id = ?, booking_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.clientId);
            statement.setInt(2, this.offeringId);
            statement.setString(3, this.bookingDate);
            statement.setString(4, this.status);
            statement.setInt(5, this.id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBooking(Connection connection, int bookingId) {
        String query = "DELETE FROM Bookings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookingId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

