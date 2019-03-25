package com.zhaopin.algorithm.queryMatchingTask.utils;

import java.util.HashMap;
import java.util.Map;

public class JDUtil {
    public static String jdMap2string(Map<String, String> jdMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry: jdMap.entrySet()){
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static Map<String, String> jdString2map(String jdString) {
        String[] tokens = jdString.split("&");
        Map<String, String> jdMap = new HashMap<>();
        for (String token: tokens) {
            String[] subTokens = token.split("=");
            jdMap.put(subTokens[0], subTokens[1]);
        }
        return jdMap;
    }
}
