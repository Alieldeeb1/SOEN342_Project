package Model;

/**
 * Represents a client who books lessons.
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
}

