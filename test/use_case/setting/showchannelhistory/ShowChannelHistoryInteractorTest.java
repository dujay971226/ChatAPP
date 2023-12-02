package use_case.setting.showchannelhistory;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Channel;
import entity.Message;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowChannelHistoryInteractorTest {
    User user;
    Channel channel;
    PubNub pubnub;
    ArrayList<Message> messages = new ArrayList<>();

    CountDownLatch latch = new CountDownLatch(2);

    @Test
    public void successTest() throws PubNubException, InterruptedException {

        this.user = new User("user1", "password1");
        this.channel = new Channel("channel"+LocalDateTime.now().toString(), user);
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(channel);
        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        this.pubnub = new PubNub(pnConfiguration);

        JsonObject messageJsonObject = new JsonObject();
        String messageToBeSent = "testing";
        messageJsonObject.addProperty("msg", messageToBeSent);

        pubnub.publish()
                .channel(channel.getName())
                .message(messageJsonObject)
                .async((result, publishStatus) -> {
                });

        ShowChannelHistoryInputData inputData = new ShowChannelHistoryInputData(LocalDateTime.now(), channel.getName(), pubnub);
        ShowChannelHistoryOutputBoundary successPresenter = new ShowChannelHistoryOutputBoundary() {
           @Override
           public void prepareSuccessView(ShowChannelHistoryOutputData showChannelHistoryOutputData) {
               Message messageToBeCheck = showChannelHistoryOutputData.getChannelMessages().get(0);
               assertEquals(messageToBeSent, messageToBeCheck.getContent());
               assertEquals(user, messageToBeCheck.getUser());
           }

           @Override
           public void prepareFailView(String error) {
                assert false;
           }
        };

        ShowChannelHistoryInputBoundary interactor = new ShowChannelHistoryInteractor(successPresenter);
        interactor.execute(inputData);
    }

}