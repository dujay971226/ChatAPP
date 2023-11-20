package use_case.create_room;

/**
 * Output boundary of create room.
 * @author huangzhihao
 */
public interface CreateRoomOutputBoundary {

    /**
     * Prepares success view.
     * @param outputData output data
     */
    void prepareSuccessView(CreateRoomOutputData outputData);

    /**
     * Prepares fail view.
     * @param error error message
     */
    void prepareFailView(String error);
}
