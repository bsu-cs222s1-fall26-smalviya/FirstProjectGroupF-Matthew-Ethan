package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RevisionParserTest {

    @Test
    public void canParseAllRevisions() {
        InputStream input = getClass().getResourceAsStream("/Zappa.json");
        assertNotNull(input);

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(input);

        assertEquals(4, revisions.size());
    }
}