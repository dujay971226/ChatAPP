package api;

import java.io.IOException;

public class Journal {
    public static String[] searchJournals(String content) throws IOException{
        return CORE.searchJournals(content);
    }

    public static String searchJournalsByISSN(String issn) throws IOException{
        return CORE.searchJournalsByISSN(issn);
    }

    public static String searchByDOI(String DOI) throws IOException{
        return CORE.searchByDOI(DOI);
    }
}
