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

            List<String> timestamps = JsonPath.read(
                    input,
                    "$.query.pages.*.revisions[*].timestamp"
            );

            List<Revision> revisions = new ArrayList<>();

            for (int i = 0; i < usernames.size(); i++) {
                Revision revision = new Revision(
                        usernames.get(i),
                        timestamps.get(i)
                );

                revisions.add(revision);
            }

            return revisions;

        } catch (Exception e) {
            return null;
        }
    }
}