package use_case.profile.profiletocreate;

/**
 * The ProfiletocreateInteractor class is responsible for handling the creation of user profiles.
 * It receives input data containing user and configuration information and communicates the result
 * to the associated output boundary.
 *
 * @author Xiaofeng Li
 */
public class ProfiletocreateInteractor implements ProfiletocreateInputBoundary {
    private final ProfiletocreateOutputBoundary profilePresenter;  // The output boundary for profile creation.

    /**
     * Constructs a new ProfiletocreateInteractor with the specified profile output boundary.
     *
     * @param profileOutputBoundary The output boundary for profile creation.
     */
    public ProfiletocreateInteractor(ProfiletocreateOutputBoundary profileOutputBoundary) {
        this.profilePresenter = profileOutputBoundary;
    }

    /**
     * Executes the profile creation process using the provided input data.
     *
     * @param inputdata The input data containing user and configuration information.
     */
    @Override
    public void execute(ProfiletocreateInputData inputdata) {
        // Create an output data object with user and configuration information.
        ProfiletocreateOutputData profiletocreateOutputData = new ProfiletocreateOutputData(inputdata.getUser(), inputdata.getConfig());

        // Notify the profile presenter with the result of the profile creation.
        profilePresenter.prepareSuccessView(profiletocreateOutputData);
    }
}

