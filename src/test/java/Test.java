import org.wltea.analyzer.core.IKSegmenter;
import queryMatchingTask.matchingAlgorithm.BMMatching;
import queryMatchingTask.utils.StrUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        IKSegmenter ikSegmenter = new IKSegmenter(
                new StringReader(""),
                true
        );
//        Lexeme lex;
//        while((lex=ikSegmenter.next())!=null){
//            System.out.print(lex.getLexemeText()+" ");
//        }
//
//        System.out.println(StrUtils.stringRegular("Java  架构师"));
//
//        char[] c1 = "Java工程师".toCharArray();
//        for (char c: c1) {
//            System.out.println(c);
//        }

        List<String> sourceToken = StrUtils.sentenceSegment(
                "安卓Java工程架构师, 出色的Java工程能力, 热爱java工程",
                ikSegmenter
        );

        List<String> targetToken = StrUtils.sentenceSegment(
                "Java工程",
                ikSegmenter
        );


        int num = BMMatching.match(
                sourceToken,
                targetToken);


        for (String s: sourceToken) {
            System.out.println(s);
        }

        System.out.println(
                "num:" + num
        );
    }
}
