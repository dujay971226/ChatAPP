package app;

import data_access.iChannelDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribePresenter;
import interface_adapter.room.RoomViewModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.CreateRoomView;

public class CreateRoomUseCaseFactory {

    private CreateRoomUseCaseFactory() {}; // No access

    public static CreateRoomView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel,
                                        RoomViewModel roomViewModel, SubscribeRoomViewModel subscribeRoomViewModel,
                                        ProfileViewModel profileViewModel,
                                        iChannelDataAccessObject iChannelDataAccessObject) {
        CreateRoomController createRoomController = CreateRoomUseCaseFactory.createCreateRoomUseCase(viewManagerModel,
                createRoomViewModel, roomViewModel);
        ProfileToSubscribeController profileToSubscribeController = CreateRoomUseCaseFactory.createProfileToSubscribeController(
                viewManagerModel, subscribeRoomViewModel, profileViewModel, iChannelDataAccessObject);
        CreateRoomView createRoomView = new CreateRoomView(createRoomController, createRoomViewModel,
                profileToSubscribeController);
        return createRoomView;
    }

    private static CreateRoomController createCreateRoomUseCase(ViewManagerModel viewManagerModel,
                                                                CreateRoomViewModel createRoomViewModel,
                                                                RoomViewModel roomViewModel) {
        CreateRoomOutputBoundary createRoomOutputBoundary = new CreateRoomPresenter(viewManagerModel,
                createRoomViewModel, roomViewModel);
        CreateRoomInputBoundary createRoomInputBoundary = new CreateRoomInteractor(createRoomOutputBoundary);
        CreateRoomController createRoomController = new CreateRoomController(createRoomInputBoundary);
        return createRoomController;
    }

    private static ProfileToSubscribeController createProfileToSubscribeController(ViewManagerModel viewManagerModel,
                                                                                   SubscribeRoomViewModel subscribeRoomViewModel,
                                                                                   ProfileViewModel profileViewModel,
                                                                                   iChannelDataAccessObject iChannelDataAccessObject) {
        ProfiletosubscribeOutputBoundary profiletosubscribeOutputBoundary = new ProfileToSubscribePresenter(viewManagerModel,
                subscribeRoomViewModel, profileViewModel);
        ProfiletosubscribeInputBoundary profiletosubscribeInputBoundary = new ProfiletosubscribeInteractor(
                iChannelDataAccessObject, profiletosubscribeOutputBoundary);
        ProfileToSubscribeController profileToSubscribeController = new ProfileToSubscribeController(
                profiletosubscribeInputBoundary);
        return profileToSubscribeController;
    }

}

/*
CreateRoomViewModel createRoomViewModel, ProfileViewModel profileViewModel) {
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel,
                createRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInputBoundary = new ProfiletocreateInteractor(
                profiletocreateOutputBoundary);
 */
