package use_case;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import data_access.iUserDataAccessObject;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.login.*;

public class LoginInteractorTest {

    private static class UserDataAccessObjectStub implements iUserDataAccessObject {
        private final User user;

        public UserDataAccessObjectStub(User user) {
            this.user = user;
        }

        @Override
        public void save(User user) {
            // Stub implementation
        }

        @Override
        public User get(String username) {
            return user;
        }

        @Override
        public boolean existsByName(String identifier) {
            return user != null && user.getName().equals(identifier);
        }
    }

    private static class LoginOutputBoundaryStub implements LoginOutputBoundary {
        private LoginOutputData outputData;

        @Override
        public void prepareSuccessView(LoginOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            // Stub implementation
        }

        @Override
        public void jumpSignup() {
            // Stub implementation
        }
    }

    private iUserDataAccessObject userDataAccessObject;
    private LoginOutputBoundaryStub loginOutputBoundary;
    private LoginInteractor loginInteractor;

    @Before
    public void setUp() {
        userDataAccessObject = new UserDataAccessObjectStub(new User("testUser", "testPassword"));
        loginOutputBoundary = new LoginOutputBoundaryStub();
        loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);
    }

    @Test
    public void testSuccessfulLogin() throws PubNubException {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        LoginInputData loginInputData = new LoginInputData(username, password);

        // Act
        loginInteractor.execute(loginInputData);

        // Assert
        assert loginOutputBoundary.outputData != null;
        assert loginOutputBoundary.outputData.getUser() != null;
        assert loginOutputBoundary.outputData.getConfig() != null;
    }

    @Test
    public void testFailedLoginUserNotFound() throws PubNubException {
        // Arrange
        String username = "nonExistingUser";
        String password = "testPassword";
        LoginInputData loginInputData = new LoginInputData(username, password);

        // Act
        loginInteractor.execute(loginInputData);

        // Assert
        assert loginOutputBoundary.outputData == null;
    }

    @Test
    public void testFailedLoginIncorrectPassword() throws PubNubException {
        // Arrange
        String username = "testUser";
        String incorrectPassword = "incorrectPassword";
        LoginInputData loginInputData = new LoginInputData(username, incorrectPassword);

        // Act
        loginInteractor.execute(loginInputData);

        // Assert
        assert loginOutputBoundary.outputData == null;
    }
}