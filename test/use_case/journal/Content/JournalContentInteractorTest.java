package use_case.journal.Content;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class JournalContentInteractorTest {

    @Test
    void successTest() {
        JournalContentInputData inputData = new JournalContentInputData("cancer");

        JournalContentOutputBoundary successPresenter = new JournalContentOutputBoundary() {
            @Override
            public void prepareSuccessView(JournalContentOutputData data) {
                assertInstanceOf(String.class, data.getResult());
            }
        };

        JournalContentInputBoundary interactor = new JournalContentInteractor(successPresenter);

        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}