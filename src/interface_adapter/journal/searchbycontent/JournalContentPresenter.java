package interface_adapter.journal.searchbycontent;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import use_case.journal.Content.JournalContentOutputBoundary;
import use_case.journal.Content.JournalContentOutputData;

import javax.swing.*;

/**
 * Presenter for handling the presentation logic of journal content.
 * This class implements the JournalContentOutputBoundary and is responsible for
 * updating the view model based on the output data from the journal content processing logic.
 *
 * @author Xiaofeng Li
 */
public class JournalContentPresenter implements JournalContentOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;

    /**
     * Constructs a JournalContentPresenter with the necessary view models.
     * @param viewManagerModel The model managing different views in the application.
     * @param journalViewModel The view model for the journal view.
     */
    public JournalContentPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel) {
        this.journalViewModel = journalViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares and updates the view to display the results of journal content processing.
     * @param journalContentOutputData The output data from the journal content processing logic.
     */
    public void prepareSuccessView(JournalContentOutputData journalContentOutputData) {
        JournalState currState = journalViewModel.getState();
        String orig = currState.getSearchResult();
        currState.setSearchResult(orig + "\n" + "searching by content:" + "\n" + journalContentOutputData.getResult());
        journalViewModel.setState(currState);
        journalViewModel.firePropertyChanged();
    }
}

