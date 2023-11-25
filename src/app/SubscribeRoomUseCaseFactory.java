package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletocreate.ProfileToCreateController;
import interface_adapter.profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.room.RoomViewModel;
import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomPresenter;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateInteractor;
import use_case.profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.subscribe_room.SubscribeRoomInputBoundary;
import use_case.subscribe_room.SubscribeRoomInteractor;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import view.SubscribeRoomView;

public class SubscribeRoomUseCaseFactory {

    private SubscribeRoomUseCaseFactory() {}; // No access
    public static SubscribeRoomView create(ViewManagerModel viewManagerModel,
                                           SubscribeRoomViewModel subscribeRoomViewModel,
                                           RoomViewModel roomViewModel, CreateRoomViewModel createRoomViewModel,
                                           ProfileViewModel profileViewModel) {
        SubscribeRoomController subscribeRoomController = SubscribeRoomUseCaseFactory.createSubscribeRoomController(
                viewManagerModel, subscribeRoomViewModel, roomViewModel);
        ProfileToCreateController profileToCreateController = SubscribeRoomUseCaseFactory.createProfileToCreateController(
                viewManagerModel, createRoomViewModel, profileViewModel);
        SubscribeRoomView subscribeRoomView = new SubscribeRoomView(subscribeRoomController, subscribeRoomViewModel,
                profileToCreateController);
        return subscribeRoomView;
    }

    private static SubscribeRoomController createSubscribeRoomController(ViewManagerModel viewManagerModel,
                                                                         SubscribeRoomViewModel subscribeRoomViewModel,
                                                                         RoomViewModel roomViewModel) {
        SubscribeRoomOutputBoundary subscribeRoomOutputBoundary = new SubscribeRoomPresenter(viewManagerModel,
                subscribeRoomViewModel, roomViewModel);
        SubscribeRoomInputBoundary subscribeRoomInputBoundary = new SubscribeRoomInteractor(subscribeRoomOutputBoundary);
        SubscribeRoomController subscribeRoomController = new SubscribeRoomController(subscribeRoomInputBoundary);
        return subscribeRoomController;
    }

    private static ProfileToCreateController createProfileToCreateController(ViewManagerModel viewManagerModel,
                                                                             CreateRoomViewModel createRoomViewModel,
                                                                             ProfileViewModel profileViewModel) {
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel,
                createRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInputBoundary = new ProfiletocreateInteractor(
                profiletocreateOutputBoundary);
        ProfileToCreateController profileToCreateController = new ProfileToCreateController(profiletocreateInputBoundary);
        return profileToCreateController;
    }
}