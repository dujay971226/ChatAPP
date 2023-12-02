package use_case.room;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Channel;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.profile.profiletocreate.ProfiletocreateInputData;
import use_case.profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateOutputData;
import use_case.room.room_message.RoomMessageInputData;
import use_case.room.room_message.RoomMessageOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MessageInteractorTest {

        @Test
        public void MessageSendSuccessTest () throws PubNubException {
            User user = new User("user1", "password1");
            Channel channel = new Channel("channel1", user);
            String msg = "Message Test";
            UserId userId = new UserId(user.getName());
            PNConfiguration pnConfiguration = new PNConfiguration(userId);
            pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
            pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
            pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
            PubNub pubnub = new PubNub(pnConfiguration);

            RoomMessageInputData roomMessageInputData = new RoomMessageInputData(user, channel, pubnub, msg);
            RoomMessageOutputBoundary roomMessagePresenter = new RoomMessageOutputBoundary() {

                @Override
                public void prepareSentView() {
                    assertEquals();
                }

                @Override
                public void prepareLostConnectionView() {
                    assertEquals();
                }

            };
        }

    }
