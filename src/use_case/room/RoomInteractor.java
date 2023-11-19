package use_case.room;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;

import java.time.LocalDateTime;

public class RoomInteractor implements RoomInputBoundary{

    final RoomOutputBoundary RoomPresenter;

    public RoomInteractor(RoomOutputBoundary roomOutputBoundary) {
        this.RoomPresenter = roomOutputBoundary;
    }


    @Override
    public void execute(RoomExitInputData roomExitInputData) {
        PubNub pubnub = roomExitInputData.getConfig();

        pubnub.unsubscribeAll();

        RoomPresenter.prepareProfileView();
    }


    //Send Message Use Case
    @Override
    public void execute(RoomMessageInputData roomMessageInputData) {

        PubNub pubnub = roomMessageInputData.getConfig();

        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", roomMessageInputData.getMessage());

        pubnub.publish()
                .channel(roomMessageInputData.getChannel().getChannelName())
                .message(messageJsonObject)
                .async((result, publishStatus) -> {
                    if (!publishStatus.isError()) {
                    }
                    else {
                        RoomPresenter.prepareLostConnectionView();
                    }
                });
    }
}
