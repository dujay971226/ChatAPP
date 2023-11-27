package use_case.profile.profiletocreate;

/**
 * The ProfiletocreateInputBoundary interface defines the contract for handling the creation of user profiles.
 * Implementations of this interface should execute the creation of user profiles based on the provided input data.
 *
 * @author Xiaofeng Li
 */
public interface ProfiletocreateInputBoundary {

    /**
     * Execute the creation of a user profile based on the provided input data.
     *
     * @param inputData The input data containing information required for profile creation.
     */
    void execute(ProfiletocreateInputData inputData);
}
