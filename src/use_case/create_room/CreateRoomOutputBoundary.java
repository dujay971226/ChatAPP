package use_case.create_room;

public interface CreateRoomOutputBoundary {
    void prepareSuccessView(CreateRoomOutputData user);
    void prepareFailView(String error);
}
