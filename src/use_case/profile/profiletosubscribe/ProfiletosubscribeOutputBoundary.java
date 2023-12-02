package use_case.profile.profiletosubscribe;

/**
 * The ProfiletosubscribeOutputBoundary interface defines the boundary for presenting subscription-related views.
 * It includes methods for preparing a successful view and a fail view.
 *
 * @author Xiaofeng Li
 */
public interface ProfiletosubscribeOutputBoundary {
    /**
     * Prepares a successful view for subscription results.
     *
     * @param outputData The output data containing user, configuration, and channel information.
     */
    void prepareSuccessView(ProfiletosubscribeOutputData outputData);

}

