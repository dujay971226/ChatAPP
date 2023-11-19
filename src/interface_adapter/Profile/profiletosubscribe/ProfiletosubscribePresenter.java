package interface_adapter.Profile.profiletosubscribe;

import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeOutputData;

public class ProfiletosubscribePresenter implements ProfiletosubscribeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SubscribeRoomViewModel subscribeRoomViewModel;
    private final ProfileViewModel profileViewModel;

    public ProfiletosubscribePresenter(ViewManagerModel viewManagerModel,SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.subscribeRoomViewModel = subscribeRoomViewModel;
    }

    public void prepareSuccessView(ProfiletosubscribeOutputData outputData){
        SubscribeRoomState subscribeRoomState = subscribeRoomViewModel.getState();
        subscribeRoomState.setUser(outputData.getUser());
        subscribeRoomState.setConfig(outputData.getConfig());

        subscribeRoomViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(subscribeRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    public void prepareFailView() {

    }
}
