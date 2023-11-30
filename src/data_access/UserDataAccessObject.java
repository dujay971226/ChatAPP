package data_access;

import entity.User;
import entity.UserFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The UserDataAccessObject class implements the iUserDataAccessObject interface
 * and provides functionality for saving, retrieving, and checking the existence of
 * user data. It uses a CSV file as the data repository.
 */
public class UserDataAccessObject implements iUserDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private final UserFactory userFactory;

    /**
     * Constructor for UserDataAccessObject.
     *
     * @param csvPath     The path to the CSV file.
     * @param userFactory The factory for creating User objects.
     * @throws IOException If an I/O error occurs.
     */
    public UserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);

        if (csvFile.length() == 0) {
            save(); // Create an empty CSV file with headers if it doesn't exist
        } else {
            // Load existing data from the CSV file
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("username,password");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    User user = userFactory.create(username, password);
                    accounts.put(username, user);
                }
            }
        }
    }

    /**
     * Saves the provided user data by updating the accounts map and writing to the CSV file.
     *
     * @param user The User object to be saved.
     */
    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    /**
     * Retrieves a User object associated with the given username.
     *
     * @param username The unique identifier of the user to be retrieved.
     * @return The User object if found; otherwise, null.
     */
    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    /**
     * * Checks if a user with the specified username exists in the data repository.
     *
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s", user.getName(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
