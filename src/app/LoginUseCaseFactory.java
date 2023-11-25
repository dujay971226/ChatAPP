package app;

import data_access.iUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

/**
 * The LoginUseCaseFactory class is responsible for creating and configuring the components
 * necessary for the login use case in a graphical user interface (GUI) application.
 */

public class LoginUseCaseFactory {
    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    /** The method follows the Factory Method design pattern,
     * initializing the required components and handling potential IOExceptions during the creation
     * process. If successful, it returns a configured LoginView; otherwise, it displays an error
     * message using JOptionPane and returns null.
     *
     * @param viewManagerModel      The ViewManagerModel representing the model for managing views.
     * @param loginViewModel        The ViewModel holding data for the login interface.
     * @param profileViewModel      The ViewModel holding data for the user profile interface.
     * @param signupViewModel       The ViewModel holding data for the signup interface.
     * @param userDataAccessObject  The iUserDataAccessObject providing methods for accessing user data.
     * @return                      A LoginView instance if successful, or null if an IOException occurs.
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ProfileViewModel profileViewModel,
            SignupViewModel signupViewModel,
            iUserDataAccessObject userDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, profileViewModel, userDataAccessObject, signupViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ProfileViewModel profileViewModel,
            iUserDataAccessObject userDataAccessObject,
            SignupViewModel signupViewModel) throws IOException {


        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, profileViewModel, loginViewModel, signupViewModel);

        UserFactory userFactory = new UserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
