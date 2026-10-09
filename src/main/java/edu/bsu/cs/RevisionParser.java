package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RevisionParser {

    public List<Revision> parse(InputStream input) {
        try {
            List<String> usernames = JsonPath.read(
                    input,
                    "$.query.pages.*.revisions[*].user"
            );

            List<Revision> revisions = new ArrayList<>();

            for (String username : usernames) {
                revisions.add(new Revision(username));
            }

            return revisions;

        } catch (Exception e) {
            return null;
        }
    }
}