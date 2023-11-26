package use_case.profile.profiletologout;

/**
 * This class represents the interactor for the logout use case.
 * It implements the LogoutInputBoundary interface to handle the logout operation.
 *
 * @author Xiaofeng Li
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final LogoutOutputBoundary logoutOutputBoundary;

    /**
     * Initializes a new instance of the LogoutInteractor class.
     *
     * @param logoutOutputBoundary The output boundary for the logout use case.
     */
    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary) {
        this.logoutOutputBoundary = logoutOutputBoundary;
    }

    /**
     * Executes the logout operation, triggering the logout process.
     */
    @Override
    public void execute() {
        logoutOutputBoundary.preparesuccessview();
    }
}

