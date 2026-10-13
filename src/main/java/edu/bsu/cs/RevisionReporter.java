package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class RevisionReporter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article name: ");
        String articleName = scanner.nextLine();

        if (articleName.isEmpty()) {
            System.err.println("No article name was entered.");
            return;
        }

        WikipediaClient client = new WikipediaClient();
        InputStream input = client.getRevisions(articleName);

        if (input == null) {
            System.err.println("Failed to connect to Wikipedia.");
            return;
        }

        try {

            byte[] jsonBytes = input.readAllBytes();

            RevisionParser parser = new RevisionParser();

            String redirectTarget = parser.getRedirect(new ByteArrayInputStream(jsonBytes));
            if (redirectTarget != null && !redirectTarget.isEmpty()) {
                System.out.println("Redirected to " + redirectTarget);
            }


            List<Revision> revisions = parser.parse(new ByteArrayInputStream(jsonBytes));

            if (revisions == null || revisions.isEmpty()) {
                System.err.println("Wikipedia article not found.");
                return;
            }

            RevisionFormatter formatter = new RevisionFormatter();
            int count = 1;

            for (Revision revision : revisions) {
                System.out.println(count + "  " + formatter.format(revision));
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}