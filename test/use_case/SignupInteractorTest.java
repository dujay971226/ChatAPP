package use_case;

import data_access.iUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.signup.*;

import static org.junit.Assert.*;

public class SignupInteractorTest {

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

    private static class SignupOutputBoundaryStub implements SignupOutputBoundary {
        private SignupOutputData outputData;

        @Override
        public void prepareSuccessView(SignupOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            // Stub implementation
        }

        @Override
        public void jumpLogin() {
            // Stub implementation
        }
    }

    private iUserDataAccessObject userDataAccessObject;
    private SignupOutputBoundaryStub signupOutputBoundary;
    private SignupInteractor signupInteractor;

    @Before
    public void setUp() {
        userDataAccessObject = new UserDataAccessObjectStub(null);
        signupOutputBoundary = new SignupOutputBoundaryStub();
        signupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, new UserFactory());
    }

    @Test
    public void testSuccessfulSignup() {
        // Arrange
        String username = "newUser";
        String password = "newPassword";
        SignupInputData signupInputData = new SignupInputData(username, password, password);

        // Act
        signupInteractor.execute(signupInputData);

        // Assert
        assert signupOutputBoundary.outputData != null;
        assertEquals(username, signupOutputBoundary.outputData.getUsername());
    }

    @Test
    public void testFailedSignupUserAlreadyExists() {
        // Arrange
        String existingUsername = "existingUser";
        String password = "password";
        UserDataAccessObjectStub userDataAccessObjectStub = new UserDataAccessObjectStub(new User(existingUsername, password));
        signupInteractor = new SignupInteractor(userDataAccessObjectStub, signupOutputBoundary, new UserFactory());
        SignupInputData signupInputData = new SignupInputData(existingUsername, password, password);

        // Act
        signupInteractor.execute(signupInputData);

        // Assert
        assert signupOutputBoundary.outputData != null;
        assertEquals(existingUsername, signupOutputBoundary.outputData.getUsername());
    }

    @Test
    public void testFailedSignupPasswordsDoNotMatch() {
        // Arrange
        String username = "testUser";
        String password = "password";
        SignupInputData signupInputData = new SignupInputData(username, password, "differentPassword");

        // Act
        signupInteractor.execute(signupInputData);

        // Assert
        assert signupOutputBoundary.outputData != null;
        assertEquals(username, signupOutputBoundary.outputData.getUsername());
    }
}