package interface_adapter.room;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.showsetting.SettingState;
import interface_adapter.showsetting.SettingViewModel;
import use_case.room.RoomOutputBoundary;
import use_case.room.RoomOutputData;
import use_case.room.RoomToSettingOutputData;

import javax.swing.*;

public class RoomPresenter implements RoomOutputBoundary {

    private final RoomViewModel roomViewModel;
    private final ProfileViewModel profileViewModel;
    private final JournalViewModel journalViewModel;
    private final SettingViewModel settingViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoomPresenter (ViewManagerModel viewManagerModel,
                          ProfileViewModel profileViewModel,
                          JournalViewModel journalViewModel,
                          SettingViewModel settingViewModel,
                          RoomViewModel roomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.journalViewModel = journalViewModel;
        this.settingViewModel = settingViewModel;
        this.roomViewModel = roomViewModel;

    }

    public void prepareProfileView() {

        this.viewManagerModel.setActiveView(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareNewMessageView(RoomOutputData roomOutputData) {
        RoomState roomState = roomViewModel.getState();
        roomState.setMessage(roomOutputData.getNewMessage().getContent());
        roomState.setRecieveMessageNotice();
        roomViewModel.setState(roomState);
        roomViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(roomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareSettingView(RoomToSettingOutputData roomToSettingOutputData) {
        SettingState settingState = settingViewModel.getState();
        settingState.setUser(roomToSettingOutputData.getUser());
        settingState.setChannel(roomToSettingOutputData.getChannel());
        settingState.setConfig(roomToSettingOutputData.getConfig());
        settingViewModel.setState(settingState);
        settingViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(settingViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareJournalView() {
        this.viewManagerModel.setActiveView(journalViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSentView() {
        RoomState roomState = roomViewModel.getState();
        roomState.setMessage("");
        roomViewModel.setState(roomState);
        roomViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(roomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    public void prepareLostConnectionView() {
        JOptionPane.showMessageDialog(
                null,
                "Message Send Failed. Please try again",
                "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
}
