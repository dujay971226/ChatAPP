package use_case.journal.Doi;

import org.junit.jupiter.api.Test;
import use_case.journal.Content.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalDoiInteractorTest {
    @Test
    void successTest() {
        JournalDoiInputData inputData = new JournalDoiInputData("10.3389/fcell.2020.00117");

        JournalDoiOutputBoundary successPresenter = new JournalDoiOutputBoundary() {
            @Override
            public void prepareSuccessView(JournalDoiOutputData data) {
                assertEquals("http://core.ac.uk/labs/oadiscovery/redirect?url=https%3A%2F%2Fwww.frontiersin.org%2Farticles%2F10.3389%2Ffcell.2020.00117%2Fpdf&key=9A57080668582E2851C416527CC4D98C", data.getResult());
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