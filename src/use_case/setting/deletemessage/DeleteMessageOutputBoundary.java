package use_case.setting.deletemessage;

public interface DeleteMessageOutputBoundary {
    void prepareSuccessView(DeleteMessageOutputData deleteMessageOutputData);
    void prepareFailView(String error);

}
