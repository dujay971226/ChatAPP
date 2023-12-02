package app;

import data_access.iUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

/**
 * The SignupUseCaseFactory is a Java class designed to streamline the creation of a user signup
 * interface in a graphical user interface (GUI) application.
 */

public class SignupUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private SignupUseCaseFactory() {
    }

    /** The method follows the Factory Method design pattern, initializing
     * the required components and handling potential IOExceptions during the creation process. If successful,
     * it returns a configured SignupView; otherwise, it displays an error message using JOptionPane and returns null.
     *
     * @param viewManagerModel      The ViewManagerModel representing the model for managing views.
     * @param loginViewModel        The ViewModel holding data for the login interface.
     * @param signupViewModel       The ViewModel holding data for the signup interface.
     * @param userDataAccessObject  The iUserDataAccessObject providing methods for accessing user data.
     * @return                      A SignupView instance if successful, or null if an IOException occurs.
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            iUserDataAccessObject userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel,
                    loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                                                            LoginViewModel loginViewModel,
                                                            iUserDataAccessObject userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new UserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
