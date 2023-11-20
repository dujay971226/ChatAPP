package app;

import interface_adapter.Journal.JournalViewModel;
import interface_adapter.Journal.searchbycontent.JournalContentController;
import interface_adapter.Journal.searchbycontent.JournalContentPresenter;
import interface_adapter.Journal.searchbydoi.JournalDoiController;
import interface_adapter.Journal.searchbydoi.JournalDoiPresenter;
import interface_adapter.Journal.searchbyissn.JournalIssnController;
import interface_adapter.Journal.searchbyissn.JournalIssnPresenter;

import interface_adapter.ViewManagerModel;
import use_case.Journal.Content.JournalContentInputBoundary;
import use_case.Journal.Content.JournalContentInteractor;
import use_case.Journal.Content.JournalContentOutputBoundary;
import use_case.Journal.Doi.JournalDoiInputBoundary;
import use_case.Journal.Doi.JournalDoiInteractor;
import use_case.Journal.Doi.JournalDoiOutputBoundary;
import use_case.Journal.Issn.JournalIssnInputBoundary;
import use_case.Journal.Issn.JournalIssnInteractor;
import use_case.Journal.Issn.JournalIssnOutputBoundary;
import use_case.Journal.Issn.JournalIssnOutputData;
import view.JournalView;


import javax.swing.*;
import java.io.IOException;

public class JournalUsecaseFactory {
    public static JournalView create(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalContentController journalContentController = createContent(viewManagerModel,journalViewModel);
        JournalDoiController journalDoiController = createDio(viewManagerModel, journalViewModel);
        JournalIssnController journalIssnController = createIssn(viewManagerModel,journalViewModel);
        return new JournalView(journalViewModel,journalContentController,journalDoiController,journalIssnController);

    }
    public static JournalContentController createContent(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalContentOutputBoundary journalContentOutputBoundary = new JournalContentPresenter(viewManagerModel,journalViewModel);
        JournalContentInputBoundary journalContentInputBoundary = new JournalContentInteractor(journalContentOutputBoundary);
        return new JournalContentController(journalContentInputBoundary);
    }
    public static JournalDoiController createDio(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalDoiOutputBoundary journalDoiOutputBoundary = new JournalDoiPresenter(viewManagerModel, journalViewModel);
        JournalDoiInputBoundary journalDoiInputBoundary = new JournalDoiInteractor((journalDoiOutputBoundary));
        return new JournalDoiController(journalDoiInputBoundary);

    }
    public static JournalIssnController createIssn(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){

        JournalIssnOutputBoundary journalIssnOutputBoundary = new JournalIssnPresenter(viewManagerModel, journalViewModel);
        JournalIssnInputBoundary journalIssnInputBoundary = new JournalIssnInteractor(journalIssnOutputBoundary);
        return new JournalIssnController(journalIssnInputBoundary);
    }

}
