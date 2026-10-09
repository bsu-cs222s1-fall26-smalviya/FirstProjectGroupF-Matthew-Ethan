package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RevisionParserTest {

    @Test
    public void canParseRevision() {
        InputStream input = getClass().getResourceAsStream("/Zappa.json");
        assertNotNull(input);

        RevisionParser parser = new RevisionParser();
        Revision revision = parser.parse(input);
        Revision username = parser.parse(input);

        assertNotNull(username);
    }
}