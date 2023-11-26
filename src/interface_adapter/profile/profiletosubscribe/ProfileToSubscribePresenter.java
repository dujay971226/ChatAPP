package interface_adapter.profile.profiletosubscribe;

import interface_adapter.profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputData;

/**
 * Presenter for handling the presentation logic of user subscription operations.
 * This class implements the ProfiletosubscribeOutputBoundary and is responsible for
 * updating the view model based on the output data from the subscription process.
 *
 * @author Xiaofeng Li
 */
public class ProfileToSubscribePresenter implements ProfiletosubscribeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SubscribeRoomViewModel subscribeRoomViewModel;
    private final ProfileViewModel profileViewModel;

    /**
     * Constructs a ProfileToSubscribePresenter with the necessary view models.
     * @param viewManagerModel The model managing different views in the application.
     * @param subscribeRoomViewModel The view model for subscribing to rooms.
     * @param profileViewModel The view model for the user's profile.
     */
    public ProfileToSubscribePresenter(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.subscribeRoomViewModel = subscribeRoomViewModel;
    }

    /**
     * Prepares and updates the view to reflect the successful subscription process.
     * @param outputData The output data from the subscription process.
     */
    public void prepareSuccessView(ProfiletosubscribeOutputData outputData) {
        SubscribeRoomState subscribeRoomState = subscribeRoomViewModel.getState();
        subscribeRoomState.setUser(outputData.getUser());
        subscribeRoomState.setConfig(outputData.getConfig());
        subscribeRoomState.setChannelLog(outputData.getChannelLog());

        subscribeRoomViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(subscribeRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view to reflect a failed subscription process.
     * This method could be expanded to include more detailed handling of different failure scenarios.
     */
    public void prepareFailView() {
        // Logic to update the view in case of subscription failure
    }
}

