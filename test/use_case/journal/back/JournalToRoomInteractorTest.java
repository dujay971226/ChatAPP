package use_case.journal.back;

import org.junit.jupiter.api.Test;
import use_case.journal.Content.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JournalToRoomInteractorTest {

    @Test
    void successTest() {
        JournalToRoomOutputBoundary successPresenter = new JournalToRoomOutputBoundary() {
            @Override
            public void prepareRoomView() {

            }

        };

        JournalToRoomInputBoundary interactor = new JournalToRoomInteractor(successPresenter);
        interactor.execute();
    }
}