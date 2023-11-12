package use_case.create_room;

public interface CreateRoomOutputBoundary {
    void prepareSuccessView(CreateRoomOutputData outputData);
    void prepareFailView(String error);
}
