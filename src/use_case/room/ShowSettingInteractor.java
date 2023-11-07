package use_case.room;

import data_access.ChannelUsersDataAccessInterface;

public class ShowSettingInteractor implements ShowSettingInputBoundary {
    final ChannelUsersDataAccessInterface channelUsersDataAccessObject;
    final ShowSettingOutputBoundary showSettingPresenter;

    public ShowSettingInteractor(ChannelUsersDataAccessInterface channelUsersDataAccessObject, ShowSettingOutputBoundary showSettingPresenter) {
        this.channelUsersDataAccessObject = channelUsersDataAccessObject;
        this.showSettingPresenter = showSettingPresenter;
    }

    @Override
    public void execute(ShowSettingInputData showSettingInputData){
    }
}
