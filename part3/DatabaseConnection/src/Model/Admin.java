package Model;

/**
 * Represents an administrator.
 */
public class Admin {
    private int id;
    private String name;
    private String email;

    /**
     * Constructs a new Admin.
     *
     * @param id     the unique identifier of the admin
     * @param name   the name of the admin
     * @param email  the email of the admin
     */
    public Admin(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
