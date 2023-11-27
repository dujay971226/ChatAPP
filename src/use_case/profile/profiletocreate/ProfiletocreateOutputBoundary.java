package use_case.profile.profiletocreate;

/**
 * The ProfiletocreateOutputBoundary interface defines the contract for presenting the result
 * of a user profile creation operation.
 *
 * @author Xiaofeng Li
 */
public interface ProfiletocreateOutputBoundary {

    /**
     * Prepares a success view with the provided output data.
     *
     * @param outputData The output data containing user and configuration information.
     */
    void prepareSuccessView(ProfiletocreateOutputData outputData);
}

