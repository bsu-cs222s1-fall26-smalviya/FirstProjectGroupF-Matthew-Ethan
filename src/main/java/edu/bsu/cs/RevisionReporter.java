package edu.bsu.cs;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class RevisionReporter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article name: ");
        String articleName = scanner.nextLine();

        WikipediaClient client = new WikipediaClient();
        InputStream input = client.getRevisions(articleName);

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(input);

        RevisionFormatter formatter = new RevisionFormatter();

        for (Revision revision : revisions) {
            System.out.println(formatter.format(revision));
        }
    }
}