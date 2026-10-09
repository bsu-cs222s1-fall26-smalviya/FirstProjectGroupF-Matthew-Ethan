package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;

public class RevisionParser {

    public String parse(InputStream input) {
        try {
            String username = JsonPath.read(
                    input,
                    "$.query.pages.*.revisions[0].user"
            ).toString();

            return username;
        } catch (Exception e) {
            return null;
        }
    }
}