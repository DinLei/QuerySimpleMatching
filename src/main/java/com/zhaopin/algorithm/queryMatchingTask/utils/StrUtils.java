package com.zhaopin.algorithm.queryMatchingTask.utils;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class StrUtils {
    /**
     * @param string 输入的字符串
     * @return 如果string为null或者都是空白字符，则返回true
     */
    public static boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static List<String> sentenceSegment(
            String sentence,
            IKSegmenter ikSegmenter
    ) throws IOException {
        List<String> res = new ArrayList<>();
        if (!"".equals(sentence)) {
            ikSegmenter.reset(new StringReader(sentence));
            Lexeme lex;
            while((lex=ikSegmenter.next()) != null){
                res.add(lex.getLexemeText());
            }
        }
        return res;
    }

    public static String stringRegular(String txt) {
        String newTxt = txt.trim().toLowerCase();
        return newTxt.replaceAll("\\s{2,}", " ");
    }
}
