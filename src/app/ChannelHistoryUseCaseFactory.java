package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.setting.deletemessage.DeleteMessageController;
import interface_adapter.setting.deletemessage.DeleteMessagePresenter;
import interface_adapter.setting.returntosetting.ReturnToSettingController;
import interface_adapter.setting.returntosetting.ReturnToSettingPresenter;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showchannelhistory.ShowChannelHistoryController;
import interface_adapter.setting.showchannelhistory.ShowChannelHistoryPresenter;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.setting.deletemessage.DeleteMessageInputBoundary;
import use_case.setting.deletemessage.DeleteMessageInteractor;
import use_case.setting.deletemessage.DeleteMessageOutputBoundary;
import use_case.setting.returntosetting.ReturnToSettingInputBoundary;
import use_case.setting.returntosetting.ReturnToSettingInteractor;
import use_case.setting.returntosetting.ReturnToSettingOutputBoundary;
import use_case.setting.showchannelhistory.ShowChannelHistoryInputBoundary;
import use_case.setting.showchannelhistory.ShowChannelHistoryInteractor;
import use_case.setting.showchannelhistory.ShowChannelHistoryOutputBoundary;
import view.ChannelHistoryView;

public class ChannelHistoryUseCaseFactory {
    public static ChannelHistoryView create(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel, ChannelHistoryViewModel channelHistoryViewModel) {
        ShowChannelHistoryController showChannelHistoryController = createHistory(viewManagerModel, channelHistoryViewModel);
        ReturnToSettingController returnToSettingController = createReturn(viewManagerModel, settingViewModel);
        DeleteMessageController deleteMessageController = createDelete(channelHistoryViewModel);
        return new ChannelHistoryView(channelHistoryViewModel, showChannelHistoryController, returnToSettingController, deleteMessageController);
    }

    public static ShowChannelHistoryController createHistory(ViewManagerModel viewManagerModel, ChannelHistoryViewModel channelHistoryViewModel) {
        ShowChannelHistoryOutputBoundary showChannelHistoryPresenter = new ShowChannelHistoryPresenter(channelHistoryViewModel, viewManagerModel);
        ShowChannelHistoryInputBoundary showChannelHistoryInteractor = new ShowChannelHistoryInteractor(showChannelHistoryPresenter);
        return new ShowChannelHistoryController(showChannelHistoryInteractor);
    }

    public static ReturnToSettingController createReturn(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel) {
        ReturnToSettingOutputBoundary returnToSettingOutputBoundary = new ReturnToSettingPresenter(viewManagerModel, settingViewModel);
        ReturnToSettingInputBoundary returnToSettingInputBoundary = new ReturnToSettingInteractor(returnToSettingOutputBoundary);
        return new ReturnToSettingController(returnToSettingInputBoundary);
    }

    public static DeleteMessageController createDelete(ChannelHistoryViewModel channelHistoryViewModel) {
        DeleteMessageOutputBoundary deleteMessageOutputBoundary = new DeleteMessagePresenter(channelHistoryViewModel);
        DeleteMessageInputBoundary deleteMessageInputBoundary = new DeleteMessageInteractor(deleteMessageOutputBoundary);
        return new DeleteMessageController(deleteMessageInputBoundary);
    }
}
