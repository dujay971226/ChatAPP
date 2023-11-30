package use_case.journal.Doi;

import org.junit.jupiter.api.Test;
import use_case.journal.Content.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class JournalDoiInteractorTest {
    @Test
    void successTest() {
        JournalDoiInputData inputData = new JournalDoiInputData("10.3389/fcell.2020.00117");

        JournalDoiOutputBoundary successPresenter = new JournalDoiOutputBoundary() {
            @Override
            public void prepareSuccessView(JournalDoiOutputData data) {
                assertInstanceOf(String.class,data.getResult());

            }

        };

        JournalDoiInputBoundary interactor = new JournalDoiInteractor(successPresenter);
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}