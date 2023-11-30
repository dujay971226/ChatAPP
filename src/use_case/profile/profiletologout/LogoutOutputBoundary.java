package use_case.profile.profiletologout;

/**
 * The LogoutOutputBoundary interface defines the contract for handling logout output.
 * Implementing classes are responsible for preparing the view after a successful logout.
 *
 * @author Xiaofeng Li
 */
public interface LogoutOutputBoundary {
    /**
     * Prepares the view after a successful logout.
     */
    void preparesuccessview();
}

