package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RevisionParserTest {

    @Test
    public void canParseFirstRevisionAuthor() {
        InputStream input = getClass().getResourceAsStream("/Zappa.json");
        assertNotNull(input);

        RevisionParser parser = new RevisionParser();
        String username = parser.parse(input);

        assertNotNull(username);
    }
}