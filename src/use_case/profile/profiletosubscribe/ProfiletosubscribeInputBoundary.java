package use_case.profile.profiletosubscribe;

/**
 * The ProfiletosubscribeInputBoundary interface defines the contract for handling subscription input.
 * Implementing classes are responsible for executing subscription-related actions based on input data.
 *
 * @author Xiaofeng Li
 */
public interface ProfiletosubscribeInputBoundary {
    /**
     * Executes subscription-related actions based on input data.
     *
     * @param profiletosubscribeInputData The input data for the subscription.
     */
    void execute(ProfiletosubscribeInputData profiletosubscribeInputData);
}

