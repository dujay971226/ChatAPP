package interface_adapter.room.room_to_setting;

import interface_adapter.ViewManagerModel;
import interface_adapter.showsetting.SettingState;
import interface_adapter.showsetting.SettingViewModel;
import use_case.room.room_to_setting.RoomToSettingOutputBoundary;
import use_case.room.room_to_setting.RoomToSettingOutputData;

public class RoomToSettingPresenter implements RoomToSettingOutputBoundary {

    private final SettingViewModel settingViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoomToSettingPresenter (ViewManagerModel viewManagerModel,
                                   SettingViewModel settingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.settingViewModel = settingViewModel;

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
}
