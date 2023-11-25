package app;

import com.pubnub.api.PubNubException;
import entity.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.*;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.room.RoomInputBoundary;
import use_case.room.RoomInteractor;
import use_case.room.RoomOutputBoundary;
import use_case.room.RoomReceiveInputData;
import view.RoomView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class RoomUseCaseFactory {

    private RoomUseCaseFactory() {}
    public static RoomView create(
            ViewManagerModel viewManagerModel, RoomViewModel roomViewModel, ProfileViewModel profileViewModel, JournalViewModel journalViewModel, SettingViewModel settingViewModel) {

        try {
            RoomMessageController roomMessageController = createRoomMessageUseCase(viewManagerModel,
                    profileViewModel, journalViewModel, settingViewModel, roomViewModel);
            RoomReceiveController roomReceiveController = createRoomReceiveUseCase(viewManagerModel,
                    profileViewModel, journalViewModel, settingViewModel, roomViewModel);
            RoomExitController roomExitController = createRoomExitUseCase(viewManagerModel,
                    profileViewModel, journalViewModel, settingViewModel, roomViewModel);
            RoomToSettingController roomToSettingController = createRoomToSettingUseCase(viewManagerModel,
                    profileViewModel, journalViewModel, settingViewModel, roomViewModel);
            RoomToJournalController roomToJournalController = createRoomToJournalUseCase(viewManagerModel,
                    profileViewModel, journalViewModel, settingViewModel, roomViewModel);
            return new RoomView(roomMessageController, roomReceiveController,
                    roomExitController, roomViewModel, roomToSettingController, roomToJournalController);
        } catch (IOException | PubNubException e) {
        }

        return null;
    }

    private static RoomMessageController createRoomMessageUseCase(ViewManagerModel viewManagerModel,
                                                                  ProfileViewModel profileViewModel,
                                                                  JournalViewModel journalViewModel,
                                                                  SettingViewModel settingViewModel,
                                                                  RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, journalViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomMessageController(roomInteractor);
    }

    private static RoomReceiveController createRoomReceiveUseCase(ViewManagerModel viewManagerModel,
                                                                  ProfileViewModel profileViewModel,
                                                                  JournalViewModel journalViewModel,
                                                                  SettingViewModel settingViewModel,
                                                                  RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, journalViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomReceiveController(roomInteractor);
    }

    private static RoomExitController createRoomExitUseCase(ViewManagerModel viewManagerModel,
                                                            ProfileViewModel profileViewModel,
                                                            JournalViewModel journalViewModel,
                                                            SettingViewModel settingViewModel,
                                                            RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, journalViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomExitController(roomInteractor);
    }

    private static RoomToSettingController createRoomToSettingUseCase(ViewManagerModel viewManagerModel,
                                                                      ProfileViewModel profileViewModel,
                                                                      JournalViewModel journalViewModel,
                                                                      SettingViewModel settingViewModel,
                                                                      RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, journalViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomToSettingController(roomInteractor);
    }

    private static RoomToJournalController createRoomToJournalUseCase(ViewManagerModel viewManagerModel,
                                                                      ProfileViewModel profileViewModel,
                                                                      JournalViewModel journalViewModel,
                                                                      SettingViewModel settingViewModel,
                                                                      RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel,
                journalViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomToJournalController(roomInteractor);
    }
}
