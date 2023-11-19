package use_case.create_room;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import entity.Channel;
import entity.Message;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Interactor of create room.
 */
public class CreateRoomInteractor implements CreateRoomInputBoundary {

    final CreateRoomDataAccessInterface createRoomDataAccessObject;
    final CreateRoomOutputBoundary createRoomPresenter;

    /**
     * Initializes a createRoomInteractor instance given a data access object and presenter.
     * @param createRoomDataAccessInterface data access object of create room.
     * @param createRoomOutputBoundary presenter of create room.
     */
    public CreateRoomInteractor(CreateRoomDataAccessInterface createRoomDataAccessInterface,
                                CreateRoomOutputBoundary createRoomOutputBoundary) {
        this.createRoomDataAccessObject = createRoomDataAccessInterface;
        this.createRoomPresenter = createRoomOutputBoundary;

    }

    /**
     * Executes based on input data. Data access object stores data and presenter prepares view.
     * @param createRoomInputData input data of create room
     */
    @Override
    public void execute(CreateRoomInputData createRoomInputData) {

        if (exists(createRoomInputData.getChannelName(), createRoomInputData.getChannelLog())) {
            createRoomPresenter.prepareFailView("Channel name already exists, try again.");
        } else {
            ArrayList<Message> messageLog = getMessageLog(createRoomInputData.getChannelName(),
                    createRoomInputData.getConfig(), createRoomInputData.getUser());

            CreateRoomOutputData createRoomOutputData = new CreateRoomOutputData(createRoomInputData.getChannelName(),
                    createRoomInputData.getConfig(), createRoomInputData.getUser(), messageLog);
            createRoomPresenter.prepareSuccessView(createRoomOutputData);
        }
    }

    private boolean exists(String channel, ArrayList<Channel> channels) {
        for (Channel c : channels) {
            if (channel.equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Message> getMessageLog(String channelName, PubNub pubNub, User user) {
        ArrayList<Message> messageLog = new ArrayList<>();
        pubNub.fetchMessages()
                .channels(Arrays.asList(channelName))
                .maximumPerChannel(25)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(@Nullable PNFetchMessagesResult pnFetchMessagesResult, @NotNull PNStatus pnStatus) {
                        if (!pnStatus.isError()) {
                            Map<String, List<PNFetchMessageItem>> channels = pnFetchMessagesResult.getChannels();
                            for (PNFetchMessageItem messageItem : channels.get(channelName)) {
                                long time = messageItem.getTimetoken() / 10000000L;
                                LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                                        TimeZone.getDefault().toZoneId());
                                Message mes = new Message(user, messageItem.getMessage().getAsString(),
                                        localDateTime);
                                messageLog.add(mes);
                            }
                        }
                    }
                });


        return null;
    }
}
