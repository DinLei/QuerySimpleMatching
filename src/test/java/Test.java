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

        String s1 = "勘探工程师tosat";
        String s2 = "勘探工程师tosat 其他 新疆 昌吉 博士 北京裕德成科贸有限公司-GFQ 不限 ####  岗位职责包括：1、配合客户运用先进的找矿仪器探索发现新的找矿方法。2、能适应公司客户要求，出差采集科研数据，为客户提供配套技术服务。3、收集、整理、研究矿产资源信息。4、能完成领导交派的其它工作 tosat";

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
                s1,
                ikSegmenter
        );

        List<String> targetToken = StrUtils.sentenceSegment(
                "勘探工程师",
                ikSegmenter
        );

        for (String s: sourceToken) System.out.println(s);
        int num = BMMatching.match(sourceToken,targetToken);

        System.out.println("num:" + num);
    }
}
