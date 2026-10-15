package edu.bsu.cs;

import java.util.List;

public class RevisionReportResult {
    private final String redirectTarget;
    private final List<Revision> revisions;

    public RevisionReportResult(String redirectTarget, List<Revision> revisions) {
        this.redirectTarget = redirectTarget;
        this.revisions = revisions;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public boolean hasRedirect() {
        return redirectTarget != null && !redirectTarget.isEmpty();
    }

    public boolean isEmpty() {
        return revisions == null || revisions.isEmpty();
    }
}
