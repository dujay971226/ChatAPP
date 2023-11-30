package interface_adapter.profile.profiletocreate;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomState;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateOutputData;

/**
 * Presenter for handling the presentation logic of profile creation.
 * This class implements the ProfiletocreateOutputBoundary and is responsible for
 * updating the view model based on the output data from the profile creation process.
 *
 * @author Xiaofeng Li
 */
public class ProfileToCreatePresenter implements ProfiletocreateOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CreateRoomViewModel createRoomViewModel;
    private final ProfileViewModel profileViewModel;

    /**
     * Constructs a ProfileToCreatePresenter with the necessary view models.
     * @param viewManagerModel The model managing different views in the application.
     * @param createRoomViewModel The view model for creating rooms.
     * @param profileViewModel The view model for the user's profile.
     */
    public ProfileToCreatePresenter(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createRoomViewModel = createRoomViewModel;
        this.profileViewModel = profileViewModel;
    }

    /**
     * Prepares and updates the view to reflect the successful creation of a profile.
     * @param outputData The output data from the profile creation process.
     */
    public void prepareSuccessView(ProfiletocreateOutputData outputData) {
        CreateRoomState createRoomState = createRoomViewModel.getState();
        createRoomState.setUser(outputData.getUser());
        createRoomState.setConfig(outputData.getConfig());
        createRoomState.setChannelLog(outputData.getChannelLog());

        createRoomViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(createRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

