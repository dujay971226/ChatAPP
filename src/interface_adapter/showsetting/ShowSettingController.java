package interface_adapter.showsetting;

import use_case.channelsetting.ShowSettingInputBoundary;
import use_case.channelsetting.ShowSettingInputData;

public class ShowSettingController{
    private final ShowSettingInputBoundary showSettingInteractor;

    public ShowSettingController(ShowSettingInputBoundary showSettingInteractor) {
        this.showSettingInteractor = showSettingInteractor;
    }


    public void execute(String currentChannel) {
        ShowSettingInputData showSettingInputData = new ShowSettingInputData(currentChannel);

        showSettingInteractor.execute(showSettingInputData);
    }
}
