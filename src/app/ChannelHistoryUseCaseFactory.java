package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.setting.returntosetting.ReturnToSettingController;
import interface_adapter.setting.returntosetting.ReturnToSettingPresenter;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.setting.returntosetting.ReturnToSettingInputBoundary;
import use_case.setting.returntosetting.ReturnToSettingInteractor;
import use_case.setting.returntosetting.ReturnToSettingOutputBoundary;
import view.ChannelHistoryView;

public class ChannelHistoryUseCaseFactory {
    public static ChannelHistoryView create(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel, ChannelHistoryViewModel channelHistoryViewModel){
        ReturnToSettingController returnToSettingController = createReturn(viewManagerModel, settingViewModel);
        return new ChannelHistoryView(channelHistoryViewModel, returnToSettingController);
    }
    
    public static ReturnToSettingController createReturn(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel){
        ReturnToSettingOutputBoundary returnToSettingOutputBoundary = new ReturnToSettingPresenter(viewManagerModel, settingViewModel);
        ReturnToSettingInputBoundary returnToSettingInputBoundary = new ReturnToSettingInteractor(returnToSettingOutputBoundary);
        return new ReturnToSettingController(returnToSettingInputBoundary);
    }
}
