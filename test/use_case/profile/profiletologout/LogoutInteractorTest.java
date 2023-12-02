package use_case.profile.profiletologout;

import org.junit.jupiter.api.Test;

public class LogoutInteractorTest {
    @Test
    public void successTest() {
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void preparesuccessview() {

            }

        };

        LogoutInputBoundary interactor = new LogoutInteractor(successPresenter);
        interactor.execute();
    }

}