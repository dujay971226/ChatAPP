package data_access;

import entity.User;

/**
 * The iUserDataAccessObject interface defines methods for accessing user data, providing
 * a contract for classes that handle the storage and retrieval of user-related information.
 */

public interface iUserDataAccessObject {

    /**
     * Saves the provided user object, storing it in the data repository.
     *
     * @param user The User object to be saved.
     */
    void save(User user);

    /**
     * Retrieves a User object associated with the given username.
     *
     * @param username The unique identifier of the user to be retrieved.
     * @return The User object if found; otherwise, null.
     */
    User get(String username);

    /**
     * Checks if a user with the specified username exists in the data repository.
     *
     * @param identifier The username or identifier to check for existence.
     * @return True if a user with the given username exists; otherwise, false.
     */
    boolean existsByName(String identifier);
}
