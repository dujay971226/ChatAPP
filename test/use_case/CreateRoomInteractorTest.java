package use_case;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import data_access.ChannelDataAccessObject;
import entity.Channel;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.create_room.CreateRoomInputData;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateRoomInteractorTest {

    @org.junit.jupiter.api.Test
    void successTest() {
        try {

            PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubNub = new PubNub(pnConfiguration);
            String channelName = LocalDateTime.now() + "testing channel'";
            User user = new User("testing user", "testing password");
            ArrayList<Channel> channelLogs = new ArrayList<>();
            Channel channel = new Channel(channelName, user);
            channelLogs.add(channel);
            ChannelDataAccessObject channelDataAccessObject = new ChannelDataAccessObject("./data/channels.json");
            for (Channel ch : channelDataAccessObject.getChannels(user)) {
                assertNotEquals(channelName, ch.getName()); // make sure it doesn't exist
            }
            CreateRoomInputData inputData = new CreateRoomInputData(channelName, pubNub, user, channelLogs);
            CreateRoomOutputBoundary outputBoundary = new CreateRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(CreateRoomOutputData outputData) {
                    assertEquals(user, outputData.getUser());
                    assertEquals(pubNub, outputData.getConfig());
                    assertEquals(channelName, outputData.getChannelName());
                    assertFalse(channelDataAccessObject.getChannels(user).contains(channel));
                }

                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected");
                }
            };
        } catch (PubNubException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void failTest() {
        try {
            PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubNub = new PubNub(pnConfiguration);
            String channelName = "testing channel name";
            User user = new User("testing user", "testing password");
            ArrayList<Channel> channelLogs = new ArrayList<>();
            Channel channel = new Channel(channelName, user);
            channelLogs.add(channel);
            ChannelDataAccessObject channelDataAccessObject = new ChannelDataAccessObject("./data/channels.json");
            channelDataAccessObject.save("testing channel name", user);
            CreateRoomInputData inputData = new CreateRoomInputData(channelName, pubNub, user, channelLogs);
            CreateRoomOutputBoundary outputBoundary = new CreateRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(CreateRoomOutputData outputData) {
                    fail("Use case success is unexpected");
                }

                @Override
                public void prepareFailView(String error) {
                    assertTrue(channelDataAccessObject.getChannels(user).contains(channel));
                }
            };
        } catch (PubNubException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}