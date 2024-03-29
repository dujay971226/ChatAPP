package app;

import data_access.iChannelDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribePresenter;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.CreateRoomView;

/**
 * Use case factory of create room.
 *
 * @author huangzhihao
 */
public class CreateRoomUseCaseFactory {

    private CreateRoomUseCaseFactory() {
    }

    // No access

    /**
     * Creates a view of create room.
     *
     * @param viewManagerModel         view manager model
     * @param createRoomViewModel      create room manager model
     * @param roomViewModel            room view model
     * @param subscribeRoomViewModel   subscribe room view model
     * @param profileViewModel         profile view model
     * @param iChannelDataAccessObject channel data access object interface
     * @return create room view
     */
    public static CreateRoomView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel,
                                        RoomViewModel roomViewModel, SubscribeRoomViewModel subscribeRoomViewModel,
                                        ProfileViewModel profileViewModel, JournalViewModel journalViewModel,
                                        SettingViewModel settingViewModel,
                                        iChannelDataAccessObject iChannelDataAccessObject) {
        CreateRoomController createRoomController = CreateRoomUseCaseFactory.createCreateRoomUseCase(viewManagerModel,
                createRoomViewModel, roomViewModel, profileViewModel, journalViewModel, settingViewModel,
                iChannelDataAccessObject);
        ProfileToSubscribeController profileToSubscribeController = CreateRoomUseCaseFactory.createProfileToSubscribeController(
                viewManagerModel, subscribeRoomViewModel, profileViewModel, iChannelDataAccessObject);
        CreateRoomView createRoomView = new CreateRoomView(createRoomController, createRoomViewModel,
                profileToSubscribeController);
        return createRoomView;
    }

    private static CreateRoomController createCreateRoomUseCase(ViewManagerModel viewManagerModel,
                                                                CreateRoomViewModel createRoomViewModel,
                                                                RoomViewModel roomViewModel,
                                                                ProfileViewModel profileViewModel,
                                                                JournalViewModel journalViewModel,
                                                                SettingViewModel settingViewModel,
                                                                iChannelDataAccessObject channelDataAccessObject) {
        CreateRoomOutputBoundary createRoomOutputBoundary = new CreateRoomPresenter(viewManagerModel,
                createRoomViewModel, roomViewModel, profileViewModel, journalViewModel, settingViewModel);
        CreateRoomInputBoundary createRoomInputBoundary = new CreateRoomInteractor(createRoomOutputBoundary,
                channelDataAccessObject);
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
