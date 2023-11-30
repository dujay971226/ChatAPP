package use_case.journal.Content;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalContentInteractorTest {

    @Test
    void successTest() {
        JournalContentInputData inputData = new JournalContentInputData("cancer");

        JournalContentOutputBoundary successPresenter = new JournalContentOutputBoundary() {
            @Override
            public void prepareSuccessView(JournalContentOutputData data) {
                assertEquals("Ecancermedicalscience-----url:https://doaj.org/toc/1754-6605\n" +
                        "Cancer Control: Journal of the Moffitt Cancer Center-----no url provided\n" +
                        "Middle East Journal of Cancer-----no url provided\n" +
                        "Blood Cancer Journal-----url:https://doaj.org/toc/2044-5385\n" +
                        "Cancer Medicine-----url:https://doaj.org/toc/2045-7634\n" +
                        "Frontiers in Oncology-----url:https://doaj.org/toc/2234-943X\n" +
                        "Journal of Tumor-----url:https://doaj.org/toc/1819-6187\n" +
                        "Prostate Cancer-----no url provided\n" +
                        "Molecular Cancer-----url:https://doaj.org/toc/1476-4598\n" +
                        "Thoracic Cancer-----no url provided\n", data.getResult());
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