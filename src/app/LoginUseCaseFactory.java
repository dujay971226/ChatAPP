package app;

import entity.UserFactory;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;
import view.ViewManagerModel;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ProfileViewModel profileViewModel,
            SignupViewModel signupViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

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
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel) throws IOException {


        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, profileViewModel, loginViewModel, signupViewModel);

        UserFactory userFactory = new UserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
