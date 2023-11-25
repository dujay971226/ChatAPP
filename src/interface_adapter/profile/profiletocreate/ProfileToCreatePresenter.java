package interface_adapter.profile.profiletocreate;

import interface_adapter.create_room.CreateRoomState;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import use_case.profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateOutputData;

public class ProfileToCreatePresenter implements ProfiletocreateOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CreateRoomViewModel createRoomViewModel;
    private final ProfileViewModel profileViewModel;
    public ProfileToCreatePresenter(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, ProfileViewModel profileViewModel){
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
