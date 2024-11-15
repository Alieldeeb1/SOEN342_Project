package Model;

/**
 * Represents a schedule for a specific location.
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
}
