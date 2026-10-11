package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionFormatterTest {

    @Test
    public void canFormatRevision() {
        Revision revision = new Revision("TestUser", "2026-10-08T04:37:25Z");

        RevisionFormatter formatter = new RevisionFormatter();

        String result = formatter.format(revision);

        assertEquals("2026-10-08T04:37:25Z  TestUser", result);
    }
}
