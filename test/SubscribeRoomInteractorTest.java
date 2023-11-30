import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Channel;
import entity.Message;
import entity.User;
import interface_adapter.subscribe_room.SubscribeRoomPresenter;
import use_case.subscribe_room.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubscribeRoomInteractorTest {

    @org.junit.jupiter.api.Test
    void successTest() {

        try {
            PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubNub = new PubNub(pnConfiguration);
            String channelName = "testing channel name";
            User user = new User("testing user", "testing password");
            ArrayList<Channel> channelLogs = new ArrayList<>();
            channelLogs.add(new Channel(channelName, user));
            SubscribeRoomInputData inputData = new SubscribeRoomInputData(channelName, pubNub, user, channelLogs);
            SubscribeRoomOutputBoundary outputBoundary = new SubscribeRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(SubscribeRoomOutputData outputData) {
                    assertEquals(channelName, outputData.getChannelName());
                    assertEquals(user, outputData.getUser());
                    assertEquals(pubNub, outputData.getConfig());
                    ArrayList<Message> messages = new ArrayList<>();
                    messages.add(new Message("testing message"));
                    assertEquals(messages, outputData.getMessageLog());
                }

                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected");
                }
            };
            SubscribeRoomInputBoundary interactor = new SubscribeRoomInteractor(outputBoundary);
            interactor.execute(inputData);

        } catch (PubNubException e) {
            throw new RuntimeException(e);
        }

    }
}