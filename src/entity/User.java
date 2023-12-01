package entity;

public class User {
    private final String name;

    private String password;

    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     */

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return name;
    }
}