package interface_adapter.room.room_exit;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.room.room_exit.RoomExitOutputBoundary;

public class RoomExitPresenter implements RoomExitOutputBoundary {

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;


    //initialization
    public RoomExitPresenter (ViewManagerModel viewManagerModel,
                          ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    //Jump to Profile View.
    public void prepareProfileView() {

        this.viewManagerModel.setActiveView(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

}
