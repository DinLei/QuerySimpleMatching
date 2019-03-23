import org.wltea.analyzer.core.IKSegmenter;
import queryMatchingTask.matchingAlgorithm.BMMatching;
import queryMatchingTask.utils.StrUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class Test2 {
    public static void main(String[] args) throws IOException {
        IKSegmenter ikSegmenter = new IKSegmenter(
                new StringReader(""),
                false
        );

        String t1 = "、、.-+";
        String s2 = "小学语文辅导老师";

        List<String> targetToken = StrUtils.sentenceSegment(
                t1,
                ikSegmenter
        );

        List<String> sourceToken1 = StrUtils.sentenceSegment(
                s2,
                ikSegmenter
        );

        for (String token: targetToken) System.out.println(token);

        int num = BMMatching.match(sourceToken1,targetToken);
        System.out.println(num);
    }
}
