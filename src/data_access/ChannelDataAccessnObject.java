package data_access;

import entity.Channel;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class ChannelDataAccessnObject implements iChannelDataAccessObject{
    private final JSONObject file;
    private final Map<Channel, ArrayList<User>> accounts = new HashMap<>();

    public ChannelDataAccessnObject(String jsonPath) throws IOException {

        String data = new String(Files.readAllBytes(Paths.get(jsonPath)));
        file = new JSONObject(data);

        if (!file.isEmpty()){
            for (String channelName : file.keySet()){
                Channel channel = new Channel(channelName, null);
                ArrayList arrayList = new ArrayList<>();
                accounts.put(channel,arrayList);
                JSONArray users = file.getJSONArray(channelName);
                for (int i=0; i<users.length();i++){
                    JSONObject user = users.getJSONObject(i);
                    User theUser = new User(user.keys().next(), user.getString(user.keys().next()));
                    arrayList.add(theUser);

                }
            }
        }
    }

    public ArrayList<Channel> getChannels(User user){
        ArrayList result = new ArrayList<>();
        for (Channel channel : accounts.keySet()){
            if(accounts.get(channel).contains(user)){
                channel.setCurrUser(user);
                result.add(channel);
            }
        }
        return result;
    }
    public void save(Channel channel, User curr, String jsonPath) {
        JSONObject jsonObject;

        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonPath)));
            jsonObject = new JSONObject(content);
        } catch (IOException e) {
            jsonObject = new JSONObject();
        }

        if (!jsonObject.has(channel.getName())) {
            jsonObject.put(channel.getName(), new JSONArray());
        }

        JSONArray users = jsonObject.getJSONArray(channel.getName());
        Boolean exist = false;
        for (int j=0; j<users.length();j++){
            JSONObject user = users.getJSONObject(j);
            if(user.keys().next() == curr.getName()) {
                exist = true;
            }
        }
        if (!exist){
            JSONObject addUser = new JSONObject(curr.getName(),curr.getPassword());
            users.put(addUser);
        }

        try {
            Files.write(Paths.get(jsonPath), jsonObject.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("fail to save");
        }
    }
}
