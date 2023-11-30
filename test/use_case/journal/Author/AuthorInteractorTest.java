package use_case.journal.author;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import use_case.journal.author.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AuthorInteractorTest {
    @Test
    public void successTest() {
        AuthorInputData inputData = new AuthorInputData("John");


        AuthorOutputBoundary successPresenter = new AuthorOutputBoundary() {
            @Override
            public void prepareSuccessView(AuthorOutputData data) {
                Assertions.assertInstanceOf(String.class, data.getResult());
            }

        };

        AuthorInputBoundary interactor = new AuthorInteractor(successPresenter);
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}