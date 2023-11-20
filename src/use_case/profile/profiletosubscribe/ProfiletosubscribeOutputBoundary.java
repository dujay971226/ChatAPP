package use_case.profile.profiletosubscribe;

public interface ProfiletosubscribeOutputBoundary {
    void prepareSuccessView(ProfiletosubscribeOutputData outputData);
    void prepareFailView();
}
