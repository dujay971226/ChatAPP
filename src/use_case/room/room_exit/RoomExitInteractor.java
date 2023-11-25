package use_case.room.room_exit;

import com.pubnub.api.PubNub;

public class RoomExitInteractor implements RoomExitInputBoundary{

    final RoomExitOutputBoundary roomExitPresenter;

    public RoomExitInteractor(RoomExitOutputBoundary roomExitOutputBoundary) {
        this.roomExitPresenter = roomExitOutputBoundary;
    }

    //unsubscribe the user from the channel before exit and set to profile view
    @Override
    public void execute(RoomExitInputData roomExitInputData) {
        PubNub pubnub = roomExitInputData.getConfig();

        pubnub.unsubscribeAll();

        roomExitPresenter.prepareProfileView();
    }
}
