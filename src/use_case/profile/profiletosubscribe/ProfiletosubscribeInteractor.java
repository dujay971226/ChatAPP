package use_case.profile.profiletosubscribe;

import data_access.iChannelDataAccessObject;
import entity.Channel;

import java.util.ArrayList;

/**
 * The ProfiletosubscribeInteractor class represents the interactor responsible for handling subscription requests.
 * It retrieves a list of channels for a user and prepares the output data for a successful subscription.
 *
 * @author Xiaofeng Li
 */
public class ProfiletosubscribeInteractor implements ProfiletosubscribeInputBoundary {
    private final ProfiletosubscribeOutputBoundary profilePresenter;
    private final iChannelDataAccessObject ichannelDataAccessObject;

    /**
     * Constructs a new ProfiletosubscribeInteractor with the required dependencies.
     *
     * @param ichannelDataAccessObject The data access object for accessing channels.
     * @param profileOutputBoundary    The output boundary for presenting subscription results.
     */
    public ProfiletosubscribeInteractor(iChannelDataAccessObject ichannelDataAccessObject, ProfiletosubscribeOutputBoundary profileOutputBoundary) {
        this.ichannelDataAccessObject = ichannelDataAccessObject;
        this.profilePresenter = profileOutputBoundary;
    }

    /**
     * Executes the subscription operation by retrieving a list of channels for the user and preparing the output data.
     *
     * @param inputdata The input data for the subscription operation, including user and configuration information.
     */
    public void execute(ProfiletosubscribeInputData inputdata) {
        ArrayList<Channel> channels = ichannelDataAccessObject.getChannels(inputdata.getUser());

        ProfiletosubscribeOutputData profiletosubscribeOutputData = new ProfiletosubscribeOutputData(inputdata.getUser(), inputdata.getConfig(), channels);
        profilePresenter.prepareSuccessView(profiletosubscribeOutputData);
    }
}

