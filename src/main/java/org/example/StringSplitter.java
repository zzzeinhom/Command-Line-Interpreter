package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitter {

    public static String[] splitString(String input) {
        List<String> result = new ArrayList<>();

        // Regular expression to match words and phrases in quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Quoted phrase (group 1)
                result.add(matcher.group(1));
            } else {
                // Single word (group 2)
                result.add(matcher.group(2));
            }
        }

        String[] output = new String[result.size()];
        return result.toArray(output);
    }
}
