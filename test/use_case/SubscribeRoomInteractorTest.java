package use_case;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import data_access.ChannelDataAccessObject;
import entity.Channel;
import entity.Message;
import entity.User;
import interface_adapter.subscribe_room.SubscribeRoomPresenter;
import use_case.subscribe_room.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for subscribe room interactor.
 */
class SubscribeRoomInteractorTest {

    /**
     * Success test.
     */
    @org.junit.jupiter.api.Test
    void successTestChannelExists() {

        try {
            String channelName = "testing channel name";
            User user = new User("testing user", "testing password");
            ChannelDataAccessObject channelDataAccessObject = new ChannelDataAccessObject("./test/channel_test.json");
            PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubNub = new PubNub(pnConfiguration);
            pubNub.subscribe().channels(Collections.singletonList(channelName)).execute();
            JsonObject messageJsonObject = new JsonObject();
            messageJsonObject.addProperty("msg", "testing message");
            pubNub.publish()
                    .channel(channelName)
                    .message(messageJsonObject)
                    .async((result, publishStatus) -> {
                                if (publishStatus.isError()) {throw new RuntimeException("Publish status error."); }
                            }
                    );
            SubscribeRoomInputData inputData = new SubscribeRoomInputData(channelName, pubNub, user);
            SubscribeRoomOutputBoundary outputBoundary = new SubscribeRoomOutputBoundary() {
                @Override
                public void prepareSuccessView(SubscribeRoomOutputData outputData) {
                    assertEquals(channelName, outputData.getChannelName());
                    assertEquals(user, outputData.getUser());
                    assertEquals(pubNub, outputData.getConfig());
                    for (Message m : outputData.getMessageLog()) {
                        assertNotNull(m.getContent());
                        assertNotNull(m.getTime());
                        assertNotNull(m.getUser());
                    }
                }

                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected:" + error);
                }
            };
            SubscribeRoomInputBoundary interactor = new SubscribeRoomInteractor(outputBoundary, channelDataAccessObject);
            interactor.execute(inputData);
            Thread.sleep(1000);
        } catch (PubNubException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}