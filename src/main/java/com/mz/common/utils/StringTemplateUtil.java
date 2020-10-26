package com.mz.common.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplateUtil {

    private static Pattern pattern;

    static {
        pattern = Pattern.compile("\\$\\{\\w+\\}");
    }

    public static String processTemplate(String template, Map<String, Object> params) {
        Matcher matcher = pattern.matcher(template);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String param = matcher.group();
            Object value = params.get(param.substring(2, param.length() - 1));
            matcher.appendReplacement(buffer, null == value ? "" : value.toString());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
