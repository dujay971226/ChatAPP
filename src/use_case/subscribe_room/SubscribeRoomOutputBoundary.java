package use_case.subscribe_room;

/**
 * Output boundary of subscribe room.
 *
 * @author huangzhihao
 */
public interface SubscribeRoomOutputBoundary {

    /**
     * Prepares success view.
     *
     * @param outputData output data.
     */
    void prepareSuccessView(SubscribeRoomOutputData outputData);

    /**
     * Prepares fail view.
     *
     * @param error error string
     */
    void prepareFailView(String error);
}
