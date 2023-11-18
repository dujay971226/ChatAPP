package data_access.create_room;


import com.google.gson.*;

import use_case.create_room.CreateRoomDataAccessInterface;

import java.io.*;



/**
 * Data access object of create room.
 */
public class CreateRoomDataAccessObject implements CreateRoomDataAccessInterface {

    private final String jsPath;
    private final JsonObject jsonObject;

    /**
     * Initializes a CreateRoomDataAccessObject instance with a given json file path.
     * @param path json file path
     */
    public CreateRoomDataAccessObject(String path) {
        this.jsPath = path;
        try {
            jsonObject = JsonParser.parseReader(new FileReader(path))
                    .getAsJsonObject();
        } catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Determines whether a channel name exists in the json object.
     * @param possibleName a possible channel name
     * @return true if name exists, false otherwise
     */
    @Override
    public boolean existsByName(String possibleName) {
        return jsonObject.keySet().contains(possibleName);
    }

    /**
     * Saves the channel name locally to a json object. Note this method assumes that
     * the channel name does not already exist therefore would replace any existing
     * channel name and its users. It does not add user to it existing channel, use
     * SubscribeRoomDataAccessObject's save() instead.
     * @see data_access.subscribe_room.SubscribeRoomDataAccessObject
     * @param channelName channel name
     */
    @Override
    public void save(String channelName, String userName) {
        JsonArray userArray = new JsonArray();
        userArray.add(userName);
        jsonObject.add(channelName, userArray);
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


    /// for testing !!!

    //// delete after
    public static void main(String[] args) throws IOException {
        CreateRoomDataAccessObject da = new CreateRoomDataAccessObject("src/data_access/sampleData.json");
        boolean b = da.existsByName("channel1");
        System.out.println(b);
        da.save("newChannel", "newUser1");
    } //// warning: DELETE


}
