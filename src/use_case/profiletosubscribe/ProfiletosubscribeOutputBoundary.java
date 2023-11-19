package use_case.profiletosubscribe;

public interface ProfiletosubscribeOutputBoundary {
    void prepareSuccessView(ProfiletosubscribeOutputData outputData);
    void prepareFailView();
}
