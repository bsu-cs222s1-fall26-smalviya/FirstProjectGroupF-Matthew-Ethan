package edu.bsu.cs;

import java.util.List;
import java.util.Scanner;

public class RevisionReporter {

    @SuppressWarnings("unused")
     static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article name: ");
        String articleName = scanner.nextLine().trim();

        if (articleName.isEmpty()) {
            System.err.println("No article name was entered.");
            return;
        }

        WikipediaQueryBuilder queryBuilder = new WikipediaQueryBuilder();
        WikipediaClient client = new WikipediaClient(queryBuilder);
        RedirectParser redirectParser = new RedirectParser();
        RevisionParser revisionParser = new RevisionParser();
        RevisionReportService service = new RevisionReportService(client, redirectParser, revisionParser);

        RevisionReportResult result = service.getReportForArticle(articleName);

        if (result == null) {
            System.err.println("Failed to connect to Wikipedia.");
            return;
        }

        if (result.hasRedirect()) {
            System.out.println("Redirected to " + result.getRedirectTarget());
        }

        if (result.isEmpty()) {
            System.err.println("Wikipedia article not found.");
            return;
        }

        RevisionFormatter formatter = new RevisionFormatter();
        List<Revision> revisions = result.getRevisions();
        for (int i = 0; i < revisions.size(); i++) {
            System.out.println((i + 1) + "  " + formatter.format(revisions.get(i)));
        }
    }
}