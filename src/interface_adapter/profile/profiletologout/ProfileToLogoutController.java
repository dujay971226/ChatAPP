package interface_adapter.profile.profiletologout;

import use_case.profile.profiletologout.LogoutInputBoundary;

/**
 * Controller for managing user logout operations.
 * This class is responsible for handling the logout action and delegating the processing
 * to the LogoutInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class ProfileToLogoutController {
    final LogoutInputBoundary logoutInputBoundary;

    /**
     * Constructs a ProfileToLogoutController with a specified input boundary.
     * @param logoutInputBoundary The input boundary that provides the logic
     *                            for processing logout operations.
     */
    public ProfileToLogoutController(LogoutInputBoundary logoutInputBoundary) {
        this.logoutInputBoundary = logoutInputBoundary;
    }

    /**
     * Executes the operation related to user logout.
     * This method triggers the logic encapsulated in the LogoutInputBoundary to handle the logout process.
     */
    public void execute() {
        logoutInputBoundary.execute();
    }
}

