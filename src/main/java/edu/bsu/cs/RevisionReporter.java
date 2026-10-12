package edu.bsu.cs;

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

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(input);

        RevisionFormatter formatter = new RevisionFormatter();

        int count = 1;

        for (Revision revision : revisions) {
            System.out.println(count + " " + formatter.format(revision));
            count++;
        }
    }
}