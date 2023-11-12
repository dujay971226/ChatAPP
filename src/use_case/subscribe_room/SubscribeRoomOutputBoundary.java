package use_case.subscribe_room;

public interface SubscribeRoomOutputBoundary {

    void prepareSuccessView(SubscribeRoomOutputData outputData);

    void prepareFailView(String error);
}
