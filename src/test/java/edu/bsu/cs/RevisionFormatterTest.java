package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RevisionFormatterTest {

    @Test
    public void canFormatRevision() {
        Revision revision = new Revision("TestUser");

        RevisionFormatter formatter = new RevisionFormatter();
        String result = formatter.format(revision);

        assertNotNull(result);
    }
}