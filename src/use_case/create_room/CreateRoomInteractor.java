package use_case.create_room;

/**
 * Interactor of create room.
 */
public class CreateRoomInteractor implements CreateRoomInputBoundary {

    final CreateRoomDataAccessInterface createRoomDataAccessObject;
    final CreateRoomOutputBoundary createRoomPresenter;

    /**
     * Initializes a createRoomInteractor instance given a data access object and presenter.
     * @param createRoomDataAccessInterface data access object of create room.
     * @param createRoomOutputBoundary presenter of create room.
     */
    public CreateRoomInteractor(CreateRoomDataAccessInterface createRoomDataAccessInterface,
                                CreateRoomOutputBoundary createRoomOutputBoundary) {
        this.createRoomDataAccessObject = createRoomDataAccessInterface;
        this.createRoomPresenter = createRoomOutputBoundary;

    }

    /**
     * Executes based on input data. Data access object stores data and presenter prepares view.
     * @param createRoomInputData input data of create room
     */
    @Override
    public void execute(CreateRoomInputData createRoomInputData) {
        if (createRoomDataAccessObject.existsByName(createRoomInputData.getChannelName())) {
            createRoomPresenter.prepareFailView("Channel name already exists, try again.");
        } else {
            //createRoomDataAccessObject.save("channelName n", "User n"); // for testing only
            createRoomDataAccessObject.save(createRoomInputData.getChannelName(), createRoomInputData.getUser().getName());
            CreateRoomOutputData createRoomOutputData = new CreateRoomOutputData(createRoomInputData.getChannelName(),
                    createRoomInputData.getConfig(), createRoomInputData.getUser());
            createRoomPresenter.prepareSuccessView(createRoomOutputData);
        }
    }
}
