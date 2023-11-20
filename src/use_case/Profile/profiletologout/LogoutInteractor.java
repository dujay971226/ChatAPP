package use_case.Profile.profiletologout;

public class LogoutInteractor implements LogoutInputBoundary{
    final LogoutOutputBoundary logoutOutputBoundary;

    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary){
        this.logoutOutputBoundary = logoutOutputBoundary;
    }
    public void execute(){
        logoutOutputBoundary.preparesuccessview();
    }
}
