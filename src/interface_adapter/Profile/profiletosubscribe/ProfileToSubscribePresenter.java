package interface_adapter.Profile.profiletosubscribe;

import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import use_case.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import use_case.profiletosubscribe.ProfiletosubscribeOutputData;

import java.util.ArrayList;

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
        subscribeRoomState.setChannelLog(outputData.getChannels());

        subscribeRoomViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(subscribeRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    public void prepareFailView() {

    }
}
