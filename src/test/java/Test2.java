import org.wltea.analyzer.core.IKSegmenter;
import com.zhaopin.algorithm.queryMatchingTask.matchingAlgorithm.BMMatching;
import com.zhaopin.algorithm.queryMatchingTask.utils.StrUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class Test2 {
    public static void main(String[] args) throws IOException {
        IKSegmenter ikSegmenter = new IKSegmenter(
                new StringReader(""),
                true
        );

        String t1 = "人工智能";
        String s1 = "人工智能专家（研发部影像所）";
        String s2 = "人工智能算法工程师";

        List<String> targetToken = StrUtils.sentenceSegment(
                t1,
                ikSegmenter
        );

        List<String> sourceToken1 = StrUtils.sentenceSegment(
                s2,
                ikSegmenter
        );

        for (String token: targetToken) System.out.print(token+"  ");
        System.out.println();
        for (String token: sourceToken1) System.out.print(token+"  ");
        System.out.println();

        int num = BMMatching.match(sourceToken1,targetToken);
        System.out.println("MatchingNum: "+num);
    }
}
