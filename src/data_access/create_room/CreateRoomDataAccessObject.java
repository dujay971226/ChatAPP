package data_access.create_room;


import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import entity.User;
import org.json.JSONObject;
import use_case.create_room.CreateRoomDataAccessInterface;

// TODO finish upload channel to server

/**
 * Data access object of create room.
 */
public class CreateRoomDataAccessObject implements CreateRoomDataAccessInterface {

    private final JSONObject jSObject;

    /**
     * Initializes a CreateRoomDataAccessObject instance.
     */
    public CreateRoomDataAccessObject(JSONObject jSObject) {this.jSObject = jSObject; }

    /**
     * Determines whether a channel name exists.
     * @param possibleName a possible channel name
     * @return true if name exists, false otherwise
     */
    @Override
    public boolean existsByName(String possibleName) {
        JSONObject channelsJSObject = jSObject.getJSONObject("channels");
        for (String channelName : channelsJSObject.keySet()) {
            if (channelsJSObject.getString(channelName).equals(possibleName)) {
                return true; // exists a same name
            }
        }
        return false; // no same name found
    }

    /**
     * Saves the channel name locally.
     * @param channelName channel name
     */
    @Override
    public void save(String channelName, User user) {

    }

    public static void main(String[] args) {
        JSONObject js = new JSONObject("sampleData.json");
        CreateRoomDataAccessObject obj = new CreateRoomDataAccessObject(js);
        boolean b = obj.existsByName("channel1");
        System.out.println(b);
    }


}
