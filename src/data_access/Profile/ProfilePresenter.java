package data_access.Profile;

import use_case.Profile.ProfileOutputBoundary;
import use_case.Profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;
    public ProfilePresenter (ProfileViewModel profileViewModel){
        this.profileViewModel = profileViewModel;
    }
    public void prepareSuccessView(ProfileOutputData outputData){
        ProfileState state = profileViewModel.getState();
        state.setState(outputData.getUser());
    }
}
