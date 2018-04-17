package io.github.autoinstall;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MergePropertyUtils {
    static final Pattern EXPRESSION_PATTERN = Pattern.compile("\\$\\{([^}]*)\\}");

    public static String mergeVars(String value, Properties props) {
        StringBuilder result = new StringBuilder(value.length());
        int i = 0;
        Matcher matcher = EXPRESSION_PATTERN.matcher(value);
        while(matcher.find()) {
            // Strip leading "${" and trailing "}" off.
            result.append(value.substring(i, matcher.start()));
            String property = matcher.group();
            property = property.substring(2, property.length() - 1);
            if(props.containsKey(property)) {
                //look up property and replace
                property = props.getProperty(property);
            } else {
                //property not found, don't replace
                property = matcher.group();
            }
            result.append(property);
            i = matcher.end();
        }
        result.append(value.substring(i));
        return result.toString();
    }
}
