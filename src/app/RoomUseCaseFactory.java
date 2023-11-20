package app;

import entity.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.room.*;
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
            ViewManagerModel viewManagerModel, RoomViewModel roomViewModel, ProfileViewModel profileViewModel, SettingViewModel settingViewModel) {

        try {
            RoomMessageController roomMessageController = createRoomMessageUseCase(viewManagerModel,
                    profileViewModel, settingViewModel, roomViewModel);
            RoomReceiveController roomReceiveController = createRoomReceiveUseCase(viewManagerModel,
                    profileViewModel, settingViewModel, roomViewModel);
            RoomExitController roomExitController = createRoomExitUseCase(viewManagerModel,
                    profileViewModel, settingViewModel, roomViewModel);
            RoomToSettingController roomToSettingController = createRoomToSettingUseCase(viewManagerModel,
                    profileViewModel, settingViewModel, roomViewModel);
            return new RoomView(roomMessageController, roomReceiveController,
                    roomExitController, roomViewModel, roomToSettingController, journalController);
        } catch (IOException e) {
        }

        return null;
    }

    private static RoomMessageController createRoomMessageUseCase(ViewManagerModel viewManagerModel,
                                                            ProfileViewModel profileViewModel,
                                                            SettingViewModel settingViewModel,
                                                            RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomMessageController(roomInteractor);
    }

    private static RoomReceiveController createRoomReceiveUseCase(ViewManagerModel viewManagerModel,
                                                           ProfileViewModel profileViewModel,
                                                           SettingViewModel settingViewModel,
                                                           RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomReceiveController(roomInteractor);
    }

    private static RoomExitController createRoomExitUseCase(ViewManagerModel viewManagerModel,
                                                           ProfileViewModel profileViewModel,
                                                           SettingViewModel settingViewModel,
                                                           RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomExitController(roomInteractor);
    }

    private static RoomToSettingController createRoomToSettingUseCase(ViewManagerModel viewManagerModel,
                                                                  ProfileViewModel profileViewModel,
                                                                  SettingViewModel settingViewModel,
                                                                  RoomViewModel roomViewModel) throws IOException {

        RoomOutputBoundary roomOutputBoundary = new RoomPresenter(viewManagerModel, profileViewModel, settingViewModel, roomViewModel);

        RoomInputBoundary roomInteractor = new RoomInteractor(
                roomOutputBoundary);

        return new RoomToSettingController(roomInteractor);
    }
}
