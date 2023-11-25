package app;

import com.pubnub.api.PubNubException;
import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.room.room_exit.RoomExitController;
import interface_adapter.room.room_exit.RoomExitPresenter;
import interface_adapter.room.room_message.RoomMessageController;
import interface_adapter.room.room_message.RoomMessagePresenter;
import interface_adapter.room.room_receive.RoomReceiveController;
import interface_adapter.room.room_receive.RoomReceivePresenter;
import interface_adapter.room.room_to_journal.RoomToJournalController;
import interface_adapter.room.room_to_journal.RoomToJournalPresenter;
import interface_adapter.room.room_to_setting.RoomToSettingController;
import interface_adapter.room.room_to_setting.RoomToSettingPresenter;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.room.room_exit.RoomExitInputBoundary;
import use_case.room.room_exit.RoomExitInteractor;
import use_case.room.room_exit.RoomExitOutputBoundary;
import use_case.room.room_message.RoomMessageInputBoundary;
import use_case.room.room_message.RoomMessageInteractor;
import use_case.room.room_message.RoomMessageOutputBoundary;
import use_case.room.room_receive.RoomReceiveInputBoundary;
import use_case.room.room_receive.RoomReceiveInteractor;
import use_case.room.room_receive.RoomReceiveOutputBoundary;
import use_case.room.room_to_journal.RoomToJournalInputBoundary;
import use_case.room.room_to_journal.RoomToJournalInteractor;
import use_case.room.room_to_journal.RoomToJournalOutputBoundary;
import use_case.room.room_to_setting.RoomToSettingInputBoundary;
import use_case.room.room_to_setting.RoomToSettingInteractor;
import use_case.room.room_to_setting.RoomToSettingOutputBoundary;
import view.RoomView;

import java.io.IOException;

public class RoomUseCaseFactory {

    //Create RoomView for main to use
    private RoomUseCaseFactory() {}
    public static RoomView create(
            ViewManagerModel viewManagerModel, RoomViewModel roomViewModel, ProfileViewModel profileViewModel, JournalViewModel journalViewModel, SettingViewModel settingViewModel) {

        try {
            RoomMessageController roomMessageController = createRoomMessageUseCase(viewManagerModel, roomViewModel);
            RoomReceiveController roomReceiveController = createRoomReceiveUseCase(viewManagerModel, roomViewModel);
            RoomExitController roomExitController = createRoomExitUseCase(viewManagerModel, profileViewModel);
            RoomToSettingController roomToSettingController = createRoomToSettingUseCase(viewManagerModel, settingViewModel);
            RoomToJournalController roomToJournalController = createRoomToJournalUseCase(viewManagerModel, journalViewModel);
            return new RoomView(roomMessageController, roomReceiveController,
                    roomExitController, roomViewModel, roomToSettingController, roomToJournalController);
        } catch (IOException | PubNubException e) {
        }

        return null;
    }

    //Create necessary Controllers for RoomView
    private static RoomMessageController createRoomMessageUseCase(ViewManagerModel viewManagerModel,
                                                                  RoomViewModel roomViewModel) throws IOException {

        RoomMessageOutputBoundary roomOutputBoundary = new RoomMessagePresenter(viewManagerModel, roomViewModel);

        RoomMessageInputBoundary roomMessageInteractor = new RoomMessageInteractor(
                roomOutputBoundary);

        return new RoomMessageController(roomMessageInteractor);
    }

    private static RoomReceiveController createRoomReceiveUseCase(ViewManagerModel viewManagerModel,
                                                                  RoomViewModel roomViewModel) throws IOException {

        RoomReceiveOutputBoundary roomReceiveOutputBoundary = new RoomReceivePresenter(viewManagerModel, roomViewModel);

        RoomReceiveInputBoundary roomReceiveInteractor = new RoomReceiveInteractor(
                roomReceiveOutputBoundary);

        return new RoomReceiveController(roomReceiveInteractor);
    }

    private static RoomExitController createRoomExitUseCase(ViewManagerModel viewManagerModel,
                                                            ProfileViewModel profileViewModel) throws IOException {

        RoomExitOutputBoundary roomExitOutputBoundary = new RoomExitPresenter(viewManagerModel, profileViewModel);

        RoomExitInputBoundary roomExitInteractor = new RoomExitInteractor(
                roomExitOutputBoundary);

        return new RoomExitController(roomExitInteractor);
    }

    private static RoomToSettingController createRoomToSettingUseCase(ViewManagerModel viewManagerModel,
                                                                      SettingViewModel settingViewModel) throws IOException {

        RoomToSettingOutputBoundary roomToSettingOutputBoundary = new RoomToSettingPresenter(viewManagerModel, settingViewModel);

        RoomToSettingInputBoundary roomToSettingInteractor = new RoomToSettingInteractor(
                roomToSettingOutputBoundary);

        return new RoomToSettingController(roomToSettingInteractor);
    }

    private static RoomToJournalController createRoomToJournalUseCase(ViewManagerModel viewManagerModel,
                                                                      JournalViewModel journalViewModel) throws IOException {

        RoomToJournalOutputBoundary roomToJournalOutputBoundary = new RoomToJournalPresenter(viewManagerModel, journalViewModel);

        RoomToJournalInputBoundary roomToJournalInteractor = new RoomToJournalInteractor(
                roomToJournalOutputBoundary);

        return new RoomToJournalController(roomToJournalInteractor);
    }
}
