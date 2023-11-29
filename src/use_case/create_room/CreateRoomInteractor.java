package use_case.create_room;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import data_access.ChannelDataAccessObject;
import data_access.iChannelDataAccessObject;
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
 * @author huangzhihao
 */
public class CreateRoomInteractor implements CreateRoomInputBoundary {

    final CreateRoomOutputBoundary createRoomPresenter;
    final iChannelDataAccessObject channelDataAccessObject;

    /**
     * Initializes a createRoomInteractor instance given a data access object and presenter.
     * @param createRoomOutputBoundary presenter of create room.
     */
    public CreateRoomInteractor(CreateRoomOutputBoundary createRoomOutputBoundary,
                                iChannelDataAccessObject channelDataAccessObject) {
        this.createRoomPresenter = createRoomOutputBoundary;
        this.channelDataAccessObject = channelDataAccessObject;

    }

    /**
     * Executes based on input data. Data access object stores data and presenter prepares view.
     * @param createRoomInputData input data of create room
     */
    @Override
    public void execute(CreateRoomInputData createRoomInputData) {
        PubNub pubNub = createRoomInputData.getConfig();
        String channelName = createRoomInputData.getChannelName();
        pubNub.subscribe().channels(Collections.singletonList(channelName)).execute();


        if (exists(createRoomInputData.getChannelName(), createRoomInputData.getChannelLog())) {
            createRoomPresenter.prepareFailView("Channel name already exists, try again.");
        } else {
            channelDataAccessObject.save(createRoomInputData.getChannelName(), createRoomInputData.getUser());
            CreateRoomOutputData createRoomOutputData = new CreateRoomOutputData(createRoomInputData.getChannelName(),
                    createRoomInputData.getConfig(), createRoomInputData.getUser());
            createRoomPresenter.prepareSuccessView(createRoomOutputData);
        }
    }

    // Checks if channel exists in an arraylist of channels.
    private boolean exists(String channel, ArrayList<Channel> channels) {
        if (channels == null) {
            return false;
        }
        for (Channel c : channels) {
            if (channel.equals(c.getName())) {
                return true;
            }
        }
        return false;
    }
}
