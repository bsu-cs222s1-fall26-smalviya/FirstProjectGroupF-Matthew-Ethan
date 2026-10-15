package edu.bsu.cs;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.util.List;

public class RedirectParser {

    public String parse(InputStream input) {
        try {
            DocumentContext json = JsonPath.parse(input);
            List<String> redirects = json.read("$.query.redirects[*].to");

            if (redirects != null && !redirects.isEmpty()) {
                return redirects.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}