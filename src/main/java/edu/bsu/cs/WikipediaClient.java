package edu.bsu.cs;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class WikipediaClient {

    private final WikipediaQueryBuilder queryBuilder;

    public WikipediaClient(WikipediaQueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }
    public InputStream fetchRevisionData(String articleName) {
        try {
            String urlString = queryBuilder.buildRevisionsUrl(articleName);
            URL url = URI.create(urlString).toURL();
            URLConnection connection = url.openConnection();

            connection.setRequestProperty(
                    "User-Agent",
                    "Revision Reporter/0.1"
            );
            return connection.getInputStream();
        }

        catch (Exception e) {
            return null;
        }
    }
}