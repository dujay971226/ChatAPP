package use_case.setting.deletemessage;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import entity.Channel;
import entity.Message;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.setting.showchannelhistory.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteMessageInteractorTest {
    User user;
    Channel channel;
    PubNub pubnub;
    ArrayList<Message> messages = new ArrayList<>();

    CountDownLatch latch = new CountDownLatch(2);

    @Test
    public void successTest() throws PubNubException, InterruptedException {

        final Object[] timeList = new Object[1];

        this.user = new User("user1", "password1");
        this.channel = new Channel("channel1", user);
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(channel);
        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        this.pubnub = new PubNub(pnConfiguration);

        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", "testing");

        pubnub.publish()
            .channel(channel.getName())
            .message(messageJsonObject)
            .async((result, publishStatus) -> {
            });

        ShowChannelHistoryInputData showChannelHistoryInputData = new ShowChannelHistoryInputData(LocalDateTime.now(), channel.getName(), pubnub);
        ShowChannelHistoryOutputBoundary showChannelHistoryPresenter = new ShowChannelHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowChannelHistoryOutputData showChannelHistoryOutputData) {
                long timeStamp = showChannelHistoryOutputData.getChannelMessages().get(0).getTimeStamp();
                timeList[0] = timeStamp;
            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        ShowChannelHistoryInputBoundary showChannelHistoryInteractor = new ShowChannelHistoryInteractor(showChannelHistoryPresenter);
        showChannelHistoryInteractor.execute(showChannelHistoryInputData);


        DeleteMessageInputData inputData = new DeleteMessageInputData(timeList, channel.getName(), pubnub);

        assertEquals(channel.getName(), inputData.getChannelName());
        assertEquals(pubnub, inputData.getConfig());
        assertEquals(timeList, inputData.getStartTimeLists());

        DeleteMessageOutputBoundary successPresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteMessageOutputData deleteMessageOutputData) {
                pubnub.fetchMessages()
                        .channels(Collections.singletonList(channel.getName()))
                        .maximumPerChannel(10)
                        .includeMessageActions(true)
                        .includeMeta(true)
                        .includeMessageType(true)
                        .includeUUID(true)
                        .async(new PNCallback<PNFetchMessagesResult>() {
                            @Override
                            public void onResponse(PNFetchMessagesResult result, PNStatus status) {
                                if (!status.isError()) {
                                    List<PNFetchMessageItem> channelMessages = result.getChannels().get(channel.getName());
                                    assert channelMessages.isEmpty();
                                }
                            }
                        });
            }

            @Override
            public void prepareFailView(String error) {
            }
        };

        DeleteMessageInputBoundary interactor = new DeleteMessageInteractor(successPresenter);

        interactor.execute(inputData);
    }
}