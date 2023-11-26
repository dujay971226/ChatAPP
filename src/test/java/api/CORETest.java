package api;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class CORETest {

    @Test
    public void testSearchJournalsWithReturnValue() throws Exception {
        // Test the searchJournals method when there is a return value
        String[] result = CORE.searchJournals("example content");
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    public void testSearchJournalsWithoutReturnValue() throws Exception {
        // Test the searchJournals method when there is no return value
        String[] result = CORE.searchJournals("nonexistent content");
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testSearchJournalsByISSNWithReturnValue() throws Exception {
        // Test the searchJournalsByISSN method when there is a return value
        String result = CORE.searchJournalsByISSN("1234-5678");
        assertNotNull(result);
        assertTrue(result.contains("The title:"));
    }

    @Test
    public void testSearchJournalsByISSNWithoutReturnValue() throws Exception {
        // Test the searchJournalsByISSN method when there is no return value
        String result = CORE.searchJournalsByISSN("nonexistent-issn");
        assertNotNull(result);
        assertTrue(result.contains("no journal founded"));
    }

    @Test
    public void testSearchByDOIWithReturnValue() throws Exception {
        // Test the searchByDOI method when there is a return value
        String result = CORE.searchByDOI("10.1234/example-doi");
        assertNotNull(result);
        assertTrue(result.contains("fullTextLink"));
    }

    @Test
    public void testSearchByDOIWithoutReturnValue() throws Exception {
        // Test the searchByDOI method when there is no return value
        String result = CORE.searchByDOI("nonexistent-doi");
        assertNotNull(result);
        assertTrue(result.contains("no text founded"));
    }
}
