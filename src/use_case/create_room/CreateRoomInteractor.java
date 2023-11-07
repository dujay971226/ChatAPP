package use_case.create_room;

public class CreateRoomInteractor implements CreateRoomInputBoundary {

    final CreateRoomDataAcessInterface createRoomDataAcessObject;
    final CreateRoomOutputBoundary createRoomPresenter;

    public CreateRoomInteractor(CreateRoomDataAcessInterface createRoomDataAcessInterface,
                                CreateRoomOutputBoundary createRoomOutputBoundary) {
        this.createRoomDataAcessObject = createRoomDataAcessInterface;
        this.createRoomPresenter = createRoomOutputBoundary;

    }
    @Override
    public void execute(CreateRoomInputData createRoomInputData) {
        if (createRoomDataAcessObject.existsByName(createRoomInputData.getChannelName())) {
            createRoomPresenter.prepareFailView("Channel name already exists");
        } else {

            // TODO save it as channel
            createRoomDataAcessObject.save(createRoomInputData.getChannelName());


            CreateRoomOutputData createRoomOutputData = new CreateRoomOutputData(createRoomInputData.getChannelName());
            createRoomPresenter.prepareSuccessView(createRoomOutputData);
        }
    }
}
