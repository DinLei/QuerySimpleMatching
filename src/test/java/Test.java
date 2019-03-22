import org.wltea.analyzer.core.IKSegmenter;
import queryMatchingTask.matchingAlgorithm.BMMatching;
import queryMatchingTask.matchingAlgorithm.SubSetMatching;
import queryMatchingTask.utils.StrUtils;
import queryMatchingTask.utils.Utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        IKSegmenter ikSegmenter = new IKSegmenter(
                new StringReader(""),
                true
        );

        String t1 = "专利代理人助理";
        String s2 = "专利代理人助理";
        String s3 = "专利代理人助理（实习生）";

        String d1 = "专利代理人助理 知识产权/专利顾问/代理人 湖北 武汉 北京恒博知识产权代理有限公司 民营 专业服务/咨询(财会/法律/人力资源等) 其他 ####   1  、国内正规院校毕业，通信、电子、电学专业，本科或以上学历。        2  、了解知识产权行业，有志从事代理行业。        3  、对专利申请的撰写有一定基础，学习能力强。        4  、具备一定的英语水平。        5  、工作认真、勤奋，能够承受一定工作压力，有良好的团队协作能力。        6  、薪资可面议。      ";
        String d2 = "专利代理人助理（实习生） 知识产权/专利顾问/代理人 湖北 武汉 本科 北京恒博知识产权代理有限公司 民营 专业服务/咨询(财会/法律/人力资源等) 其他 ####   1、国内正规院校，通信、电子、电学专业，本科以上学历。  2、了解知识产权行业，有志从事代理专业。  3、对专利申请的撰写有一定基础，学习能力强。  4、具备一定的英语水平。  5、工作认真、勤奋、能够承受一定的压力，有良好的团队协作能力。  6、薪资面议     ";


        List<String> sourceToken1 = StrUtils.sentenceSegment(
                d1,
                ikSegmenter
        );

        List<String> sourceToken2 = StrUtils.sentenceSegment(
                d2,
                ikSegmenter
        );

        List<String> targetToken = StrUtils.sentenceSegment(
                t1,
                ikSegmenter
        );

        for (String s: targetToken) System.out.println(s);

        int num = BMMatching.match(sourceToken1,targetToken);
        int lcSeqMatch = SubSetMatching.getLCSeq(targetToken, sourceToken1);
        int lcStrMatch = SubSetMatching.getLCStr(targetToken, sourceToken1);

        int num2 = BMMatching.match(sourceToken2,targetToken);
        int lcSeqMatch2 = SubSetMatching.getLCSeq(targetToken, sourceToken2);
        int lcStrMatch2 = SubSetMatching.getLCStr(targetToken, sourceToken2);

        System.out.println("num:" + num);
        System.out.println("lcSeqMatch:" + lcSeqMatch);
        System.out.println("lcStrMatch:" + lcStrMatch);
        System.out.println("Score1: " + Utils.getMatchingScore(sourceToken1, targetToken));

        System.out.println("num2:" + num2);
        System.out.println("lcSeqMatch2:" + lcSeqMatch2);
        System.out.println("lcStrMatch2:" + lcStrMatch2);
        System.out.println("Score1: " + Utils.getMatchingScore(sourceToken2, targetToken));


    }
}
