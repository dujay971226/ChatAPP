package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.journal.journaltoroom.JournalToRoomController;
import interface_adapter.journal.journaltoroom.JournalToRoomPresenter;
import interface_adapter.journal.searchbycontent.JournalContentController;
import interface_adapter.journal.searchbycontent.JournalContentPresenter;
import interface_adapter.journal.searchbydoi.JournalDoiController;
import interface_adapter.journal.searchbydoi.JournalDoiPresenter;
import interface_adapter.journal.searchbyissn.AuthorController;
import interface_adapter.journal.searchbyissn.AuthorPresenter;
import interface_adapter.room.RoomViewModel;
import use_case.journal.Content.JournalContentInputBoundary;
import use_case.journal.Content.JournalContentInteractor;
import use_case.journal.Content.JournalContentOutputBoundary;
import use_case.journal.Doi.JournalDoiInputBoundary;
import use_case.journal.Doi.JournalDoiInteractor;
import use_case.journal.Doi.JournalDoiOutputBoundary;
import use_case.journal.author.AuthorInputBoundary;
import use_case.journal.author.AuthorInteractor;
import use_case.journal.author.AuthorOutputBoundary;
import use_case.journal.back.JournalToRoomInputBoundary;
import use_case.journal.back.JournalToRoomInteractor;
import use_case.journal.back.JournalToRoomOutputBoundary;
import view.JournalView;


/**
 * Factory for creating components of the journal view.
 * This class provides methods to create and assemble different controllers
 * for managing journal views in an application.
 * @author Xiaofeng Li
 */
public class JournalUsecaseFactory {

    /**
     * Creates a JournalView with all necessary controllers.
     * @param viewManagerModel A view manager model used for managing views.
     * @param journalViewModel The view model for journal view.
     * @param roomViewModel The view model for room view.
     * @return A fully constructed JournalView.
     */
    public static JournalView create(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel, RoomViewModel roomViewModel){
        JournalContentController journalContentController = createContent(viewManagerModel, journalViewModel);
        JournalDoiController journalDoiController = createDio(viewManagerModel, journalViewModel);
        AuthorController authorController = createIssn(viewManagerModel, journalViewModel);
        JournalToRoomController journalToRoomController = creatBack(viewManagerModel, journalViewModel, roomViewModel);
        return new JournalView(journalViewModel, journalContentController, journalDoiController, authorController, journalToRoomController);
    }

    /**
     * Creates a controller for managing journal content.
     * @param viewManagerModel The view manager model.
     * @param journalViewModel The journal view model.
     * @return A JournalContentController for handling journal content.
     */
    public static JournalContentController createContent(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalContentOutputBoundary journalContentOutputBoundary = new JournalContentPresenter(viewManagerModel, journalViewModel);
        JournalContentInputBoundary journalContentInputBoundary = new JournalContentInteractor(journalContentOutputBoundary);
        return new JournalContentController(journalContentInputBoundary);
    }

    /**
     * Creates a controller for handling journal DOI (Digital Object Identifier).
     * @param viewManagerModel The view manager model.
     * @param journalViewModel The journal view model.
     * @return A JournalDoiController for DOI-related operations.
     */
    public static JournalDoiController createDio(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        JournalDoiOutputBoundary journalDoiOutputBoundary = new JournalDoiPresenter(viewManagerModel, journalViewModel);
        JournalDoiInputBoundary journalDoiInputBoundary = new JournalDoiInteractor(journalDoiOutputBoundary);
        return new JournalDoiController(journalDoiInputBoundary);
    }

    /**
     * Creates a controller for managing journal ISSN (International Standard Serial Number).
     * @param viewManagerModel The view manager model.
     * @param journalViewModel The journal view model.
     * @return A JournalIssnController for ISSN-related operations.
     */
    public static AuthorController createIssn(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        AuthorOutputBoundary authorOutputBoundary = new AuthorPresenter(viewManagerModel, journalViewModel);
        AuthorInputBoundary authorInputBoundary = new AuthorInteractor(authorOutputBoundary);
        return new AuthorController(authorInputBoundary);
    }

    /**
     * Creates a controller for navigating back to the room view from the journal view.
     * @param viewManagerModel The view manager model.
     * @param journalViewModel The journal view model.
     * @param roomViewModel The room view model.
     * @return A JournalToRoomController for handling navigation back to the room view.
     */
    public static JournalToRoomController creatBack(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel, RoomViewModel roomViewModel){
        JournalToRoomOutputBoundary journalToRoomOutputBoundary = new JournalToRoomPresenter(viewManagerModel, journalViewModel, roomViewModel);
        JournalToRoomInputBoundary journalToRoomInputBoundary = new JournalToRoomInteractor(journalToRoomOutputBoundary);
        return new JournalToRoomController(journalToRoomInputBoundary);
    }
}
