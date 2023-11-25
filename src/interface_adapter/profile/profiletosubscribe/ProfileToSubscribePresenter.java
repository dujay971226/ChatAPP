package interface_adapter.profile.profiletosubscribe;

import interface_adapter.profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputData;

public class ProfileToSubscribePresenter implements ProfiletosubscribeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SubscribeRoomViewModel subscribeRoomViewModel;
    private final ProfileViewModel profileViewModel;

    public ProfileToSubscribePresenter(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.subscribeRoomViewModel = subscribeRoomViewModel;
    }

    public void prepareSuccessView(ProfiletosubscribeOutputData outputData){
        SubscribeRoomState subscribeRoomState = subscribeRoomViewModel.getState();
        subscribeRoomState.setUser(outputData.getUser());
        subscribeRoomState.setConfig(outputData.getConfig());
        subscribeRoomState.setChannelLog(outputData.getChannelLog());

        subscribeRoomViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(subscribeRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    public void prepareFailView() {

    }
}
