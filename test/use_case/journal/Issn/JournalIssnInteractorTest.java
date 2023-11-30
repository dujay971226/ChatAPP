package use_case.journal.author;

import org.junit.Test;
import use_case.journal.Doi.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JournalIssnInteractorTest {
    @Test
    public void successTest() {
        JournalIssnInputData inputData = new JournalIssnInputData("1754-6605");


        JournalIssnOutputBoundary successPresenter = new JournalIssnOutputBoundary() {
            @Override
            public void prepareSuccessView(JournalIssnOutputData data) {
                assertEquals("[The title: Ecancermedicalscience, url:https://doaj.org/toc/1754-6605]", data.getResult());
            }

        };

        JournalIssnInputBoundary interactor = new JournalIssnInteractor(successPresenter);
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}