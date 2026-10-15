package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionReportResultTest {

    @Test
    public void testHasRedirectTrue() {
        RevisionReportResult result = new RevisionReportResult("TargetArticle", Collections.emptyList());
        assertTrue(result.hasRedirect());
    }

    @Test
    public void testHasRedirectFalse() {
        RevisionReportResult result = new RevisionReportResult(null, Collections.emptyList());
        assertFalse(result.hasRedirect());
    }

    @Test
    public void testIsEmptyTrue() {
        RevisionReportResult result = new RevisionReportResult(null, Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        List<Revision> revisions = List.of(new Revision("TestUser", "2026-10-08T04:37:25Z"));
        RevisionReportResult result = new RevisionReportResult(null, revisions);
        assertFalse(result.isEmpty());
    }
}
