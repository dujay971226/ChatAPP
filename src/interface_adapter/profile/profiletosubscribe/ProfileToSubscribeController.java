package interface_adapter.profile.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputData;

/**
 * Controller for managing user subscription operations.
 * This class is responsible for handling user interactions related to subscriptions
 * and delegating the processing to the ProfiletosubscribeInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class ProfileToSubscribeController {
    final ProfiletosubscribeInputBoundary profileInputBoundary;

    /**
     * Constructs a ProfileToSubscribeController with a specified input boundary.
     * @param profileInputBoundary The input boundary that provides the logic
     *                             for processing subscription operations.
     */
    public ProfileToSubscribeController(ProfiletosubscribeInputBoundary profileInputBoundary) {
        this.profileInputBoundary = profileInputBoundary;
    }

    /**
     * Executes the operation related to user subscription management.
     * This method encapsulates the logic to process the user and configuration data for subscription.
     * @param user The user data to be processed.
     * @param config The configuration settings for the user's subscription.
     */
    public void execute(User user, PubNub config) {
        ProfiletosubscribeInputData profiletosubscribeInputData = new ProfiletosubscribeInputData(user, config);
        profileInputBoundary.execute(profiletosubscribeInputData);
    }
}

