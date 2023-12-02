package data_access;
import entity.User;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class UserDataAccessObjectTest {

    private static final String TEST_CSV_PATH = "test_users.csv";

    private UserDataAccessObject userDataAccessObject;

    @Before
    public void setUp() {
        try {
            // Using a temporary CSV file for testing
            userDataAccessObject = new UserDataAccessObject(TEST_CSV_PATH, new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Error setting up the test", e);
        }
    }

    @Test
    public void saveAndRetrieveUser() {
        // Create a new user
        User newUser = new User("testUser", "testPassword");

        // Save the user
        userDataAccessObject.save(newUser);

        // Retrieve the user by username
        User retrievedUser = userDataAccessObject.get("testUser");

        // Check if the retrieved user is not null
        assertNotNull(retrievedUser);

        // Check if the retrieved user has the expected username and password
        assertEquals("testUser", retrievedUser.getName());
        assertEquals("testPassword", retrievedUser.getPassword());
    }

    @Test
    public void userExistsByName() {
        // Create a new user
        User newUser = new User("existingUser", "password123");

        // Save the user
        userDataAccessObject.save(newUser);

        // Check if the user exists by name
        assertTrue(userDataAccessObject.existsByName("existingUser"));
        assertFalse(userDataAccessObject.existsByName("nonExistingUser"));
    }

    @Test
    public void saveAndReloadData() {
        // Create a new user
        User newUser = new User("reloadUser", "reloadPassword");

        // Save the user
        userDataAccessObject.save(newUser);

        // Create a new UserDataAccessObject to simulate reloading from the CSV file
        UserDataAccessObject reloadedDataAccessObject;
        try {
            reloadedDataAccessObject = new UserDataAccessObject(TEST_CSV_PATH, new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Error reloading data for the test", e);
        }

        // Retrieve the user from the reloaded data
        User retrievedUser = reloadedDataAccessObject.get("reloadUser");

        // Check if the retrieved user is not null
        assertNotNull(retrievedUser);

        // Check if the retrieved user has the expected username and password
        assertEquals("reloadUser", retrievedUser.getName());
        assertEquals("reloadPassword", retrievedUser.getPassword());
    }
}