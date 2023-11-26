package use_case.profile.profiletologout;

/**
 * This interface defines the input boundary for the logout use case.
 * It specifies the contract for executing the logout operation.
 *
 * @author Xiaofeng Li
 */
public interface LogoutInputBoundary {

    /**
     * Executes the logout operation, logging the user out of the system.
     */
    void execute();
}
