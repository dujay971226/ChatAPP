package use_case.Profile;

import entity.User;

public class ProfileInteractor implements ProfileInputBoundary{
    final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(ProfileOutputBoundary profilePresenter){
        this.profilePresenter = profilePresenter;
    }
    @Override
    public void execute(User user) {

    }
}
