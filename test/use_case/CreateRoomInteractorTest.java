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
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for create room interactor.
 */
class CreateRoomInteractorTest {

    /**
     * Success Test with empty channel log.
     */
    @org.junit.jupiter.api.Test
    void successTestWithNullChannelLog() {
        try {

            PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubNub = new PubNub(pnConfiguration);
            String channelName = LocalDateTime.now() + "testing channel name";
            User user = new User("testing user", "testing password");
            ArrayList<Channel> channelLogs = new ArrayList<>(); //empty channel log
            Channel channel = new Channel(channelName, user);
            ChannelDataAccessObject channelDataAccessObject = new ChannelDataAccessObject("./test/channel_test.json");
            CreateRoomInputData inputData = new CreateRoomInputData(channelName, pubNub, user, channelLogs);
            CreateRoomOutputBoundary outputBoundary = new CreateRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(CreateRoomOutputData outputData) {
                    assertEquals(user, outputData.getUser());
                    assertEquals(pubNub, outputData.getConfig());
                    assertEquals(channelName, outputData.getChannelName());
                    assertFalse(channelLogs.contains(channel));
                }

                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected:" + error);
                }
            };
            CreateRoomInteractor interactor = new CreateRoomInteractor(outputBoundary, channelDataAccessObject);
            interactor.execute(inputData);
        } catch (PubNubException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Success test with nonempty channel log.
     */
    @org.junit.jupiter.api.Test
    void successTestWithNonNullChannelLog() {
        try {

            PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubNub = new PubNub(pnConfiguration);
            String channelName = LocalDateTime.now() + "testing channel name";
            User user = new User("testing user", "testing password");
            ArrayList<Channel> channelLogs = new ArrayList<>(); //empty channel log
            channelLogs.add(new Channel("c1", user));
            channelLogs.add(new Channel("c2", user));
            ChannelDataAccessObject channelDataAccessObject = new ChannelDataAccessObject("./test/channel_test.json");
            CreateRoomInputData inputData = new CreateRoomInputData(channelName, pubNub, user, channelLogs);
            CreateRoomOutputBoundary outputBoundary = new CreateRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(CreateRoomOutputData outputData) {
                    assertEquals(user, outputData.getUser());
                    assertEquals(pubNub, outputData.getConfig());
                    assertEquals(channelName, outputData.getChannelName());
                    assertNotNull(channelLogs);
                }

                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected:" + error);
                }
            };
            CreateRoomInteractor interactor = new CreateRoomInteractor(outputBoundary, channelDataAccessObject);
            interactor.execute(inputData);
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
            channelLogs.add(channel); // channel log with the test channel present
            ChannelDataAccessObject channelDataAccessObject = new ChannelDataAccessObject("./test/channel_test.json");
            CreateRoomInputData inputData = new CreateRoomInputData(channelName, pubNub, user, channelLogs);
            CreateRoomOutputBoundary outputBoundary = new CreateRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(CreateRoomOutputData outputData) {
                    fail("Use case success is unexpected");
                }

                @Override
                public void prepareFailView(String error) {
                    assertTrue(channelLogs.contains(channel));
                }
            };
            CreateRoomInteractor interactor = new CreateRoomInteractor(outputBoundary, channelDataAccessObject);
            interactor.execute(inputData);
        } catch (PubNubException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}