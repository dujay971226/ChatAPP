package interface_adapter.create_room;

import app.RoomUseCaseFactory;
import entity.Channel;
import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;
import view.RoomView;

/**
 * Presenter of create room.
 * @author huangzhihao
 */
public class CreateRoomPresenter implements CreateRoomOutputBoundary {
    private final CreateRoomViewModel createRoomViewModel;
    private ViewManagerModel viewManagerModel;
    private RoomViewModel roomViewModel;
    private ProfileViewModel profileViewModel;
    private JournalViewModel journalViewModel;
    private SettingViewModel settingViewModel;

    /**
     * Initializes a CreateRoomPresenter instance.
     * @param createRoomViewModel create room view model
     * @param roomViewModel room view model
     */
    public CreateRoomPresenter(ViewManagerModel managerModel, CreateRoomViewModel createRoomViewModel, RoomViewModel roomViewModel, ProfileViewModel profileViewModel, JournalViewModel journalViewModel, SettingViewModel settingViewModel) {
        this.viewManagerModel = managerModel;
        this.createRoomViewModel = createRoomViewModel;
        this.roomViewModel = roomViewModel;
        this.journalViewModel = journalViewModel;
        this.profileViewModel = profileViewModel;
        this.settingViewModel = settingViewModel;
    }

    /**
     * Prepares success view. Changes view to room.
     * @param outputData output data
     */
    @Override
    public void prepareSuccessView(CreateRoomOutputData outputData) {
        RoomState state = roomViewModel.getState();
        state.setChannel(new Channel(outputData.getChannelName(), outputData.getUser()));
        state.setConfig(outputData.getConfig());
        state.setUser(outputData.getUser());
        state.setNotice();
        roomViewModel.setState(state);
        RoomView newRoomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, profileViewModel,journalViewModel,settingViewModel);
        viewManagerModel.firePropertyChanged(newRoomView);


        viewManagerModel.setActiveView(roomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares Fail view. Show error.
     * @param error error message
     */
    @Override
    public void prepareFailView(String error) {
        CreateRoomState state = createRoomViewModel.getState();
        state.setChannelNameError(error);
        createRoomViewModel.setState(state);
        createRoomViewModel.firePropertyChanged();
    }
}
