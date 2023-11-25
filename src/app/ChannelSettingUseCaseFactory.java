package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.returntochannel.ReturnToChannelController;
import interface_adapter.setting.returntochannel.ReturnToChannelPresenter;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showchannelhistory.ShowChannelHistoryController;
import interface_adapter.setting.showchannelhistory.ShowChannelHistoryPresenter;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.setting.showsetting.ShowSettingController;
import interface_adapter.setting.showsetting.ShowSettingPresenter;
import use_case.setting.channelsetting.ShowSettingInputBoundary;
import use_case.setting.channelsetting.ShowSettingInteractor;
import use_case.setting.channelsetting.ShowSettingOutputBoundary;
import use_case.setting.returntochannel.ReturnToChannelInputBoundary;
import use_case.setting.returntochannel.ReturnToChannelInteractor;
import use_case.setting.returntochannel.ReturnToChannelOutputBoundary;
import use_case.setting.showchannelhistory.ShowChannelHistoryInputBoundary;
import use_case.setting.showchannelhistory.ShowChannelHistoryInteractor;
import use_case.setting.showchannelhistory.ShowChannelHistoryOutputBoundary;
import view.SettingView;

public class ChannelSettingUseCaseFactory {
    public static SettingView create(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel, ChannelHistoryViewModel channelHistoryViewModel, RoomViewModel roomViewModel){
        ShowSettingController showSettingController = createSetting(viewManagerModel, settingViewModel);
        ShowChannelHistoryController showChannelHistoryController = createHistory(viewManagerModel, channelHistoryViewModel);
        ReturnToChannelController returnToChannelController = createReturn(viewManagerModel, roomViewModel);
        return new SettingView(settingViewModel, showSettingController, showChannelHistoryController, returnToChannelController);
    }
    public static ShowSettingController createSetting(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel){
        ShowSettingOutputBoundary showSettingPresenter = new ShowSettingPresenter(viewManagerModel, settingViewModel);
        ShowSettingInputBoundary showSettingInteractor = new ShowSettingInteractor(showSettingPresenter);
        return new ShowSettingController(showSettingInteractor);
    }

    public static ShowChannelHistoryController createHistory(ViewManagerModel viewManagerModel, ChannelHistoryViewModel channelHistoryViewModel){
        ShowChannelHistoryOutputBoundary showChannelHistoryPresenter = new ShowChannelHistoryPresenter(channelHistoryViewModel, viewManagerModel);
        ShowChannelHistoryInputBoundary showChannelHistoryInteractor = new ShowChannelHistoryInteractor(showChannelHistoryPresenter);
        return new ShowChannelHistoryController(showChannelHistoryInteractor);
    }

    public static ReturnToChannelController createReturn(ViewManagerModel viewManagerModel, RoomViewModel roomViewModel){
        ReturnToChannelOutputBoundary returnToChannelOutputBoundary = new ReturnToChannelPresenter(roomViewModel, viewManagerModel);
        ReturnToChannelInputBoundary returnToChannelInputBoundary = new ReturnToChannelInteractor(returnToChannelOutputBoundary);
        return new ReturnToChannelController(returnToChannelInputBoundary);
    }

}
