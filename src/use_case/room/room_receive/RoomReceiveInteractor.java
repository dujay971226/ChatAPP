package use_case.room.room_receive;

import entity.Message;

public class RoomReceiveInteractor implements RoomReceiveInputBoundary{

    final RoomReceiveOutputBoundary roomReceivePresenter;

    public RoomReceiveInteractor(RoomReceiveOutputBoundary roomReceiveOutputBoundary) {
        this.roomReceivePresenter = roomReceiveOutputBoundary;
    }

    //Set notice to tell the view that it needs to update a new coming message.
    @Override
    public void execute(RoomReceiveInputData roomReceiveInputData) {
        Message newMessage = roomReceiveInputData.getMessage();

        RoomReceiveOutputData roomReceiveOutputData = new RoomReceiveOutputData(newMessage);

        roomReceivePresenter.prepareNewMessageView(roomReceiveOutputData);
    }
}
