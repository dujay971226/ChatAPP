package use_case.Journal.Content;

public class JournalContentOutputData {
    private String result = "";
    public JournalContentOutputData(String[] list){
        for (String s: list){
            result = result + s +"\n";
        }
    }

    public String getResult() {
        return result;
    }
}
