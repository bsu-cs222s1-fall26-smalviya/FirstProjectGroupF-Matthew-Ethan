package edu.bsu.cs;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WikipediaClient {

    public InputStream getRevisions(String articleName) {
        try {
            String urlString =
                    "https://en.wikipedia.org/w/api.php?action=query&format=json"
                            + "&prop=revisions&titles=" + articleName
                            + "&rvprop=timestamp%7Cuser&rvlimit=15&redirects";

            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            connection.setRequestProperty(
                    "User-Agent",
                    "Revision Reporter/0.1"
            );

            return connection.getInputStream();

        } catch (Exception e) {
            return null;
        }
    }
}