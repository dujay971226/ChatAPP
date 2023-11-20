package use_case.create_room;


/**
 * The input boundary of create room.
 * @author huangzhihao
 */
public interface CreateRoomInputBoundary {

    /**
     * Executes given an input data.
     * @param createRoomInputData input data of create room
     */
    void execute(CreateRoomInputData createRoomInputData);

}
