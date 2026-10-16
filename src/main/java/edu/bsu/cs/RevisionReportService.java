package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class RevisionReportService {

    private final WikipediaClient client;
    private final RedirectParser redirectParser;
    private final RevisionParser revisionParser;

    public RevisionReportService(WikipediaClient client, RedirectParser redirectParser, RevisionParser revisionParser) {
        this.client = client;
        this.redirectParser = redirectParser;
        this.revisionParser = revisionParser;
    }

    public RevisionReportResult getReportForArticle(String articleName) {
        try (InputStream input = client.fetchRevisionData(articleName)) {
            if(input == null) {
                return null;
            }

            byte[] jsonBytes = input.readAllBytes();

            String redirectTarget = redirectParser.parse(new ByteArrayInputStream(jsonBytes));
            List<Revision> revisions = revisionParser.parse(new ByteArrayInputStream(jsonBytes));

            return new RevisionReportResult(redirectTarget, revisions);
        }
        catch (Exception e) {
            return null;
        }
    }
}
