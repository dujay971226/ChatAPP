package data_access;

import entity.Channel;
import entity.User;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChannelDataAccessObjectTest {

    ChannelDataAccessObject channelDataAccessObject;
    @Before
    public void setUp() throws Exception {
        channelDataAccessObject = new ChannelDataAccessObject("test/testchannel.json");

    }
    @After
    public void Teardown() throws IOException {
        String jsonString = "{\"channel1\":[{\"user1\":\"password1\"},{\"user2\":\"password2\"}],"
                + "\"channel2\":[{\"user2\":\"password2\"}],"
                + "\"channel3\":[{\"user1\":\"password1\"},{\"user3\":\"password3\"}]}";

        JSONObject file = new JSONObject(jsonString);
        Files.write(Paths.get("test/testchannel.json"), file.toString().getBytes(), StandardOpenOption.CREATE);

    }

    @Test
    public void getChannels() {
        User user3 = new User("user3","password3");
        ArrayList<Channel> channels = new ArrayList<>();
        Channel channel3 = new Channel("channel3",null);
        channels.add(channel3);
        assertEquals("channel3",channelDataAccessObject.getChannels(user3).get(0).getName());
    }

    @Test
    public void save() throws IOException {
        User user4 = new User("user4", "password4");
        Channel channel2 = new Channel("channel2", null);
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(channel2);
        channelDataAccessObject.save("channel2", user4);
        channelDataAccessObject = new ChannelDataAccessObject("test/testchannel.json");
        assertEquals("channel2", channelDataAccessObject.getChannels(user4).get(0).getName());

    }
    public void reload(){

    }

    }
