package interface_adapter.Profile.profiletocreate;

import interface_adapter.Profile.ProfileState;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.Profile.profiletocreate.ProfiletocreateOutputData;

public class ProfiletocreatePresenter implements ProfiletocreateOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CreateRoomViewModel createRoomViewModel;
    private final ProfileViewModel profileViewModel;
    public ProfiletocreatePresenter(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, ProfileViewModel profileViewModel){
        this.viewManagerModel = viewManagerModel;
        this.createRoomViewModel = createRoomViewModel;
        this.profileViewModel = profileViewModel;
    }
    public void prepareSuccessView(ProfiletocreateOutputData outputData){
        CreateRoomState createRoomState = createRoomViewModel.getState();
        createRoomState.setUser(outputData.getUser());
        createRoomState.setConfig(outputData.getConfig());

        profileViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(createRoomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
