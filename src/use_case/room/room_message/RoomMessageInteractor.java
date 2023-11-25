package use_case.room.room_message;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;

public class RoomMessageInteractor implements RoomMessageInputBoundary {

    final RoomMessageOutputBoundary roomMessagePresenter;

    public RoomMessageInteractor(RoomMessageOutputBoundary roomMessageOutputBoundary) {
        this.roomMessagePresenter = roomMessageOutputBoundary;
    }

    @Override
    public void execute(RoomMessageInputData roomMessageInputData) {

        PubNub pubnub = roomMessageInputData.getConfig();

        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", roomMessageInputData.getMessage());

        pubnub.publish()
                .channel(roomMessageInputData.getChannel().getName())
                .message(messageJsonObject)
                .async((result, publishStatus) -> {
                            if (!publishStatus.isError()) {
                                roomMessagePresenter.prepareSentView();
                            }
                            else {
                                roomMessagePresenter.prepareLostConnectionView();
                            }
                        }
                );
    }
}
