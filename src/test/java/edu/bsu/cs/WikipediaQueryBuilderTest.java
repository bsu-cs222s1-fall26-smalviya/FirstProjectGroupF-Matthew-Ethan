package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WikipediaQueryBuilderTest {

    @Test
    public void testBuildRevisionsUrl() {
        WikipediaQueryBuilder queryBuilder = new WikipediaQueryBuilder();
        String url = queryBuilder.buildRevisionsUrl("Frank Zappa");

        String expected = "https://en.wikipedia.org/w/api.php?action=query&format=json"
                + "&prop=revisions&titles=Frank Zappa&rvprop=timestamp%7Cuser&rvlimit=15&redirects";

        assertEquals(expected, url);
    }
}
