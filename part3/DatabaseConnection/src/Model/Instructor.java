package Model;

/**
 * Represents an instructor who teaches lessons.
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
}
