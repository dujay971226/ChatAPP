package data_access.subscribe_room;

import com.google.gson.*;
import entity.Channel;
import entity.Message;
import entity.User;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

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

    @Override
    public ArrayList<String> getChannelNames() {
        Set<String> channelSet = jsonObject.keySet();
        ArrayList<String> channelArrayList = new ArrayList<>();
        for (String channel: channelSet) {
            channelArrayList.add(channel);
        }
        return channelArrayList;
    }

    public static void main(String[] args) {
        SubscribeRoomDataAccessObject da = new SubscribeRoomDataAccessObject("src/data_access/sampleData.json");
        for (String x: da.getChannelNames().toArray(new String[0])) {
            System.out.println(x);
        }
    }
}
