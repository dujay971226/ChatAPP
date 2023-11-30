package use_case.profile.profiletologout;

import org.junit.Test;
import use_case.journal.back.JournalToRoomInputBoundary;
import use_case.journal.back.JournalToRoomInteractor;
import use_case.journal.back.JournalToRoomOutputBoundary;

import static org.junit.Assert.*;

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