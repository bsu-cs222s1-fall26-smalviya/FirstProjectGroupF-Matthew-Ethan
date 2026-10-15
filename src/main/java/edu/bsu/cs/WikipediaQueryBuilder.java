package edu.bsu.cs;

public class WikipediaQueryBuilder {

    public String buildRevisionsUrl(String articleName) {
        return "https://en.wikipedia.org/w/ai.php?action=query&format=json"
                + "&prop=revisions&titles=" + articleName
                + "&rvprop=timestamp%7Cuser&rvlimit=15&redirects";
    }
}
