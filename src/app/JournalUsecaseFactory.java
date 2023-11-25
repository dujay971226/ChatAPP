package app;

import interface_adapter.journal.JournalViewModel;
import interface_adapter.journal.journaltoroom.JournalToRoomController;
import interface_adapter.journal.journaltoroom.JournalToRoomPresenter;
import interface_adapter.journal.searchbycontent.JournalContentController;
import interface_adapter.journal.searchbycontent.JournalContentPresenter;
import interface_adapter.journal.searchbydoi.JournalDoiController;
import interface_adapter.journal.searchbydoi.JournalDoiPresenter;
import interface_adapter.journal.searchbyissn.JournalIssnController;
import interface_adapter.journal.searchbyissn.JournalIssnPresenter;

import interface_adapter.ViewManagerModel;
import use_case.journal.Content.JournalContentInputBoundary;
import use_case.journal.Content.JournalContentInteractor;
import use_case.journal.Content.JournalContentOutputBoundary;
import use_case.journal.Doi.JournalDoiInputBoundary;
import use_case.journal.Doi.JournalDoiInteractor;
import use_case.journal.Doi.JournalDoiOutputBoundary;
import use_case.journal.Issn.JournalIssnInputBoundary;
import use_case.journal.Issn.JournalIssnInteractor;
import use_case.journal.Issn.JournalIssnOutputBoundary;
import use_case.journal.back.JournalToRoomInputBoundary;
import use_case.journal.back.JournalToRoomInteractor;
import use_case.journal.back.JournalToRoomOutputBoundary;
import view.JournalView;

public class JournalUsecaseFactory {
    public static JournalView create(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalContentController journalContentController = createContent(viewManagerModel,journalViewModel);
        JournalDoiController journalDoiController = createDio(viewManagerModel, journalViewModel);
        JournalIssnController journalIssnController = createIssn(viewManagerModel,journalViewModel);
        JournalToRoomController journalToRoomController = creatBack(viewManagerModel,journalViewModel);
        return new JournalView(journalViewModel,journalContentController,journalDoiController,journalIssnController,journalToRoomController);

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
    public static JournalToRoomController creatBack(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalToRoomOutputBoundary journalToRoomOutputBoundary = new JournalToRoomPresenter(viewManagerModel,journalViewModel);
        JournalToRoomInputBoundary journalToRoomInputBoundary = new JournalToRoomInteractor(journalToRoomOutputBoundary);
        return new JournalToRoomController(journalToRoomInputBoundary);

    }

}
