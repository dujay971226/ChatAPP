package api;

import java.io.IOException;

public class Journal {
    public static String searchJournals(String content) throws IOException {
        return Academic.searchJournals(content);
    }

    public static String searchAuthor(String name) throws IOException {
        return Academic.searchAuthor(name);
    }

    public static String searchByDOI(String DOI) throws IOException {
        return Academic.searchByDOI(DOI);
    }
}
