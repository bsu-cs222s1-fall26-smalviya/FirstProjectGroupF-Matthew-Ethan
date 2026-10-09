package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonPathLearningTest {

    @Test
    public void canReadFirstRevisionAuthor() throws IOException {
        InputStream input = getClass().getResourceAsStream("/Zappa.json");

        String username = JsonPath.read(
                input,
                "$.query.pages.*.revisions[0].user"
        ).toString();

        assertNotNull(username);

    }

}
