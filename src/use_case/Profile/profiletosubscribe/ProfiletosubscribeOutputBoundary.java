package use_case.Profile.profiletosubscribe;

public interface ProfiletosubscribeOutputBoundary {
    void prepareSuccessView(ProfiletosubscribeOutputData outputData);
    void prepareFailView();
}
