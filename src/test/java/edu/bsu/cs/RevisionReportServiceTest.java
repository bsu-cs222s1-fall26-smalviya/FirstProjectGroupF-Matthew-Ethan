package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionReportServiceTest {

    @Test
    public void testGetReportForArticleSuccess() {
        WikipediaClient stubClient = new WikipediaClient(new WikipediaQueryBuilder()) {
            @Override
            public InputStream fetchRevisionData(String articleName) {
                return new ByteArrayInputStream("{}".getBytes());
            }
        };

        RedirectParser stubRedirectParser = new RedirectParser() {
            @Override
            public String parse(InputStream input) {
                return "RedirectTarget";
            }
        };

        RevisionParser stubRevisionParser = new RevisionParser() {
            @Override
            public List<Revision> parse(InputStream input) {
                return List.of(new Revision("User1", "2026-10-15T00:00:00Z"));
            }
        };

        RevisionReportService service = new RevisionReportService(stubClient, stubRedirectParser, stubRevisionParser);
        RevisionReportResult result = service.getReportForArticle("TestArticle");

        assertNotNull(result);
        assertEquals("RedirectTarget", result.getRedirectTarget());
        assertEquals(1, result.getRevisions().size());
        assertEquals("User1", result.getRevisions().get(0).getUsername());
    }

    @Test
    public void testGetReportForArticleClientReturnsNull() {
        WikipediaClient stubClient = new WikipediaClient(new WikipediaQueryBuilder()) {
            @Override
            public InputStream fetchRevisionData(String articleName) {
                return null;
            }
        };

        RevisionReportService service = new RevisionReportService(stubClient, new RedirectParser(), new RevisionParser());
        RevisionReportResult result = service.getReportForArticle("TestArticle");

        assertNull(result);
    }
}