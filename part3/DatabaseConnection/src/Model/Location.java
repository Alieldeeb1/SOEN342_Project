package Model;

/**
 * Represents a physical location for lessons or events.
 */
public class Location {
    private int id;
    private String name;
    private String city;
    private String type;
    private String address;

    /**
     * Constructs a new Location.
     *
     * @param id      the unique identifier of the location
     * @param name    the name of the location, must not be null or empty
     * @param city    the city where the location is, must not be null or empty
     * @param type    the type of the location
     * @param address the address of the location
     */
    public Location(int id, String name, String city, String type, String address) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City must not be null or empty");
        }
        this.id = id;
        this.name = name;
        this.city = city;
        this.type = type;
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        this.name = name;
    }

    public String getCity() { return city; }
    public void setCity(String city) {
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City must not be null or empty");
        }
        this.city = city;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
