package use_case.subscribe_room;

/**
 * Input boundary of subscribe room.
 *
 * @author huangzhihao
 */
public interface SubscribeRoomInputBoundary {

    /**
     * Executes using input data.
     *
     * @param subscribeRoomInputData input data.
     */
    void execute(SubscribeRoomInputData subscribeRoomInputData);

}
