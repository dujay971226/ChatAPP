package interface_adapter.subscribe_room;


import app.RoomUseCaseFactory;
import entity.Channel;
import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomOutputData;
import view.RoomView;

/**
 * Presenter of subscribe room.
 *
 * @author huangzhihao
 */
public class SubscribeRoomPresenter implements SubscribeRoomOutputBoundary {

    private final SubscribeRoomViewModel subscribeViewModel;
    private final RoomViewModel roomViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ProfileViewModel profileViewModel;
    private final JournalViewModel journalViewModel;
    private final SettingViewModel settingViewModel;

    /**
     * Initializes a subscribeRoomPresenter instance.
     *
     * @param managerModel           view manager
     * @param subscribeRoomViewModel subscribe room view model
     * @param roomViewModel          room view model
     */
    public SubscribeRoomPresenter(ViewManagerModel managerModel, SubscribeRoomViewModel subscribeRoomViewModel,
                                  RoomViewModel roomViewModel, ProfileViewModel profileViewModel,
                                  JournalViewModel journalViewModel, SettingViewModel settingViewModel) {
        this.subscribeViewModel = subscribeRoomViewModel;
        this.roomViewModel = roomViewModel;
        this.viewManagerModel = managerModel;
        this.journalViewModel = journalViewModel;
        this.profileViewModel = profileViewModel;
        this.settingViewModel = settingViewModel;
    }

    /**
     * Transitions to room view.
     *
     * @param outputData output data.
     */
    @Override
    public void prepareSuccessView(SubscribeRoomOutputData outputData) {
        RoomState state = roomViewModel.getState();
        state.setChannel(new Channel(outputData.getChannelName(), outputData.getUser()));
        state.setConfig(outputData.getConfig());
        state.setUser(outputData.getUser());
        state.setMessageLog(outputData.getMessageLog());
        state.setNotice();
        roomViewModel.setState(state);
        roomViewModel.firePropertyChanged();
        RoomView newRoomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, profileViewModel,
                journalViewModel, settingViewModel);
        viewManagerModel.firePropertyChanged(newRoomView);

        viewManagerModel.setActiveView(roomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Pop up with error message.
     *
     * @param error error string
     */
    @Override
    public void prepareFailView(String error) {
        SubscribeRoomState state = subscribeViewModel.getState();
        state.setChannelNameError(error);
        subscribeViewModel.setState(state);
        subscribeViewModel.firePropertyChanged();
    }
}
