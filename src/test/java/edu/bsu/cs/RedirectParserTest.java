package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RedirectParserTest {

    @Test
    public void testParseWithRedirect() {
        InputStream input = getClass().getResourceAsStream("/Zappa.json");
        RedirectParser parser = new RedirectParser();
        String redirect = parser.parse(input);

        assertEquals("Frank Zappa", redirect);
    }

    @Test
    public void testParseWithNullInput() {
        RedirectParser parser = new RedirectParser();
        String redirect = parser.parse(null);

        assertNull(redirect);
    }

}
