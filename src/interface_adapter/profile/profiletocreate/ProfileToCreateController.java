package interface_adapter.profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateInputData;

/**
 * Controller for managing the creation of user profiles.
 * This class is responsible for handling user interactions related to profile creation
 * and delegating the processing to the ProfiletocreateInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class ProfileToCreateController {
    final ProfiletocreateInputBoundary profileInputBoundary;

    /**
     * Constructs a ProfileToCreateController with a specified input boundary.
     *
     * @param profileInputBoundary The input boundary that provides the logic
     *                             for processing profile creation operations.
     */
    public ProfileToCreateController(ProfiletocreateInputBoundary profileInputBoundary) {
        this.profileInputBoundary = profileInputBoundary;
    }

    /**
     * Executes the operation related to profile creation.
     * This method encapsulates the logic to process the user and configuration data for profile creation.
     *
     * @param user   The user data to be processed.
     * @param config The configuration settings for the user's profile.
     */
    public void execute(User user, PubNub config) {
        ProfiletocreateInputData profiletocreateInputData = new ProfiletocreateInputData(user, config);
        profileInputBoundary.execute(profiletocreateInputData);
    }
}
