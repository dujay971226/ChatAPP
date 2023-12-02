package app;

import data_access.iChannelDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletocreate.ProfileToCreateController;
import interface_adapter.profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
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

/**
 * Use case factory of subscribe room.
 *
 * @author huangzhihao
 */
public class SubscribeRoomUseCaseFactory {

    private SubscribeRoomUseCaseFactory() {
    }

    // No access

    /**
     * Creates a subscribe room view.
     *
     * @param viewManagerModel       view manager model
     * @param subscribeRoomViewModel subscribe room view model
     * @param roomViewModel          room view model
     * @param createRoomViewModel    create room view model
     * @param profileViewModel       profile view model
     * @return a subscribe room view.
     */
    public static SubscribeRoomView create(ViewManagerModel viewManagerModel,
                                           SubscribeRoomViewModel subscribeRoomViewModel,
                                           RoomViewModel roomViewModel, CreateRoomViewModel createRoomViewModel,
                                           ProfileViewModel profileViewModel, JournalViewModel journalViewModel,
                                           SettingViewModel settingViewModel,
                                           iChannelDataAccessObject channelDataAccessObject) {
        SubscribeRoomController subscribeRoomController = SubscribeRoomUseCaseFactory.createSubscribeRoomController(
                viewManagerModel, subscribeRoomViewModel, roomViewModel, profileViewModel, journalViewModel, settingViewModel,
                channelDataAccessObject);
        ProfileToCreateController profileToCreateController = SubscribeRoomUseCaseFactory.createProfileToCreateController(
                viewManagerModel, createRoomViewModel, profileViewModel, channelDataAccessObject);
        return new SubscribeRoomView(subscribeRoomController, subscribeRoomViewModel,
                profileToCreateController);
    }

    private static SubscribeRoomController createSubscribeRoomController(ViewManagerModel viewManagerModel,
                                                                         SubscribeRoomViewModel subscribeRoomViewModel,
                                                                         RoomViewModel roomViewModel,
                                                                         ProfileViewModel profileViewModel,
                                                                         JournalViewModel journalViewModel,
                                                                         SettingViewModel settingViewModel,
                                                                         iChannelDataAccessObject channelDataAccessObject) {
        SubscribeRoomOutputBoundary subscribeRoomOutputBoundary = new SubscribeRoomPresenter(viewManagerModel,
                subscribeRoomViewModel, roomViewModel, profileViewModel, journalViewModel, settingViewModel);
        SubscribeRoomInputBoundary subscribeRoomInputBoundary = new SubscribeRoomInteractor(subscribeRoomOutputBoundary,
                channelDataAccessObject);
        return new SubscribeRoomController(subscribeRoomInputBoundary);
    }

    private static ProfileToCreateController createProfileToCreateController(ViewManagerModel viewManagerModel,
                                                                             CreateRoomViewModel createRoomViewModel,
                                                                             ProfileViewModel profileViewModel,
                                                                             iChannelDataAccessObject channelDataAccessObject) {
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel,
                createRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInputBoundary = new ProfiletocreateInteractor(
                profiletocreateOutputBoundary, channelDataAccessObject);
        return new ProfileToCreateController(profiletocreateInputBoundary);
    }
}
