package use_case.room;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @Override
    public void execute(RoomToSettingInputData roomToSettingInputData) {

        RoomToSettingOutputData roomToSettingOutputData = new RoomToSettingOutputData(roomToSettingInputData.getUser(),
                roomToSettingInputData.getChannel(), roomToSettingInputData.getConfig());

        RoomPresenter.prepareSettingView(roomToSettingOutputData);

    }

    @Override
    public void execute() {
        RoomPresenter.prepareJournalView();
    }

    @Override
    public void execute(RoomReceiveInputData roomReceiveInputData) {
        Message newMessage = roomReceiveInputData.getMessage();

        RoomOutputData roomOutputData = new RoomOutputData(newMessage);

        RoomPresenter.prepareNewMessageView(roomOutputData);
    }


    //Send Message Use Case
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
                        RoomPresenter.prepareSentView();
                    }
                    else {
                        RoomPresenter.prepareLostConnectionView();
                    }
                }
                );
    }
}
