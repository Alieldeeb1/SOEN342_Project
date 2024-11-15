package Model;

/**
 * Represents an offering for a lesson or event.
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
}
