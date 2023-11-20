package use_case.Profile.profiletocreate;

public class ProfiletocreateInteractor implements ProfiletocreateInputBoundary {
    final ProfiletocreateOutputBoundary profilePresenter;

    public ProfiletocreateInteractor(ProfiletocreateOutputBoundary profileOutputBoundary){
        this.profilePresenter = profileOutputBoundary;
    }
    @Override
    public void execute(ProfiletocreateInputData inputdata) {
        ProfiletocreateOutputData profiletocreateOutputData = new ProfiletocreateOutputData(inputdata.getUser(), inputdata.getConfig());
        profilePresenter.prepareSuccessView(profiletocreateOutputData);
    }
}
