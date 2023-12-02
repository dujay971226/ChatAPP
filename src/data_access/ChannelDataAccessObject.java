package data_access;

import entity.Channel;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for managing channel and user data.
 * This class handles the loading and saving of channel and user information from and to a JSON file.
 *
 * @author Xiaofeng Li
 */
public class ChannelDataAccessObject implements iChannelDataAccessObject {
    private final JSONObject file;
    private final String jsonPath;
    private final Map<Channel, ArrayList<User>> accounts = new HashMap<>();

    /**
     * Constructs a new ChannelDataAccessObject and loads data from the specified JSON file.
     *
     * @param jsonPath The path to the JSON file containing channel and user data.
     * @throws IOException If there is an issue reading the file.
     */
    public ChannelDataAccessObject(String jsonPath) throws IOException {
        this.jsonPath = jsonPath;
        String data = new String(Files.readAllBytes(Paths.get(jsonPath)));
        file = new JSONObject(data);

        if (!file.isEmpty()) {
            for (String channelName : file.keySet()) {
                Channel channel = new Channel(channelName, null);
                ArrayList<User> arrayList = new ArrayList<>();
                accounts.put(channel, arrayList);
                JSONArray users = file.getJSONArray(channelName);
                for (int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String username = user.keys().next();
                    User theUser = new User(username, user.getString(username));
                    arrayList.add(theUser);
                }
            }
        }
    }

    /**
     * Retrieves a list of channels that a given user is associated with.
     *
     * @param user The user for whom to retrieve the channel list.
     * @return An ArrayList of Channels associated with the given user.
     */
    public ArrayList<Channel> getChannels(User user) {
        ArrayList<Channel> result = new ArrayList<>();
        for (Channel channel : accounts.keySet()) {
            for (User muser : accounts.get(channel)) {
                if (muser.getName().equals(user.getName())) {
                    channel.setCurrUser(user);
                    result.add(channel);
                }
            }
        }

        return result;
    }

    /**
     * Saves the specified user data to the specified channel in the JSON file.
     *
     * @param channelName The channel name of the channel to be saved.
     * @param curr        The current user whose data is to be saved.
     */
    public void save(String channelName, User curr) {
        JSONObject jsonObject;

        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonPath)));
            jsonObject = new JSONObject(content);
        } catch (IOException e) {
            jsonObject = new JSONObject();
        }

        if (!jsonObject.has(channelName)) {
            jsonObject.put(channelName, new JSONArray());
        }

        JSONArray users = jsonObject.getJSONArray(channelName);
        boolean exist = false;
        for (int j = 0; j < users.length(); j++) {
            JSONObject user = users.getJSONObject(j);
            if (user.keys().next().equals(curr.getName())) {
                exist = true;
            }
        }
        if (!exist) {
            JSONObject addUser = new JSONObject();
            addUser.put(curr.getName(), curr.getPassword());
            users.put(addUser);
        }

        try {
            Files.write(Paths.get(jsonPath), jsonObject.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("fail to save");
        }
    }
}

