package data_access.subscribe_room;

import com.google.gson.*;
import entity.Channel;
import entity.User;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;

import java.io.*;

/**
 * Data access object of subscribe room.
 */
public class SubscribeRoomDataAccessObject implements SubscribeRoomDataAccessInterface {

    private final String jsPath;
    private final JsonObject jsonObject;

    /**
     * Initializes a SubscribeRoomDataAccessObject instance with a given json file path.
     * @param path json file path
     */
    public SubscribeRoomDataAccessObject(String path) {
        this.jsPath = path;
        try {
            jsonObject = JsonParser.parseReader(new FileReader(path))
                    .getAsJsonObject();
        } catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the username to an existing channel. Use CreateRoomDataAccessObject's
     * save() instead if channel name does not exist.
     * @see data_access.create_room.CreateRoomDataAccessObject
     * @param channelName channel name
     * @param userName username
     */
    @Override
    public void save(String channelName, String userName) {
        JsonArray userArray = jsonObject.getAsJsonArray(channelName);
        System.out.println(userArray);
        userArray.add(userName);
        System.out.println(jsonObject.getAsJsonArray(channelName));
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = g.toJson(jsonObject);
        try {
            FileWriter file = new FileWriter(jsPath);
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(jsonString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Message> getMessageLog(String channelName, String userName) {
        return null;
    }

    //TODO
    @Override
    public String[] getChannelNames() {
        return new String[0];
    }

    public static void main(String[] args) {
        SubscribeRoomDataAccessObject da = new SubscribeRoomDataAccessObject("src/data_access/sampleData.json");
        da.save("channel1", "new -- ");
    }
}
