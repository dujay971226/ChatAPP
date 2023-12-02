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
import use_case.setting.showchannelhistory.ShowChannelHistoryOutputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteMessageInteractorTest {
    @Test
    public void successTest() throws PubNubException {
        User user = new User("user1", "password1");
        Channel channel = new Channel("channel1", user);
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(channel);
        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        PubNub pubnub = new PubNub(pnConfiguration);

        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", "testing");

        pubnub.publish()
                .channel(channel.getName())
                .message(messageJsonObject);

        ArrayList<Message> messages = new ArrayList<>();

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

                            if (channelMessages != null) {
                                for (PNFetchMessageItem messageItem : channelMessages) {
                                    String username = messageItem.getUuid();
                                    String content = messageItem.getMessage().getAsJsonObject().get("msg").toString();
                                    // remove quotation marks
                                    content = content.substring(1, content.length() - 1);
                                    messages.add(new Message(new User(username), content, messageItem.getTimetoken()));
                                }
                                ShowChannelHistoryOutputData showSettingOutputData = new ShowChannelHistoryOutputData(messages, pubnub, channel.getName());
                            }
                        }
                    }
                }
                );
        long timeStamp = messages.get(0).getTimeStamp();

        DeleteMessageInputData inputData = new DeleteMessageInputData(timeStamp - 1, timeStamp, channel.getName(), pubnub);
        DeleteMessageOutputBoundary successPresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteMessageOutputData showSettingOutputData) {

            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        DeleteMessageInputBoundary interactor = new DeleteMessageInteractor(successPresenter);
        interactor.execute(inputData);
    }

}