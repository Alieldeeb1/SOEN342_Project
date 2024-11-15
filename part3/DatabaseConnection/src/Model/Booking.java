package Model;

/**
 * Represents a booking for a lesson or event.
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
}
