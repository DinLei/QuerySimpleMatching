import org.wltea.analyzer.core.IKSegmenter;
import com.zhaopin.algorithm.queryMatchingTask.matchingAlgorithm.BMMatching;
import com.zhaopin.algorithm.queryMatchingTask.matchingAlgorithm.SubSetMatching;
import com.zhaopin.algorithm.queryMatchingTask.utils.StrUtils;
import com.zhaopin.algorithm.queryMatchingTask.utils.Utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        IKSegmenter ikSegmenter = new IKSegmenter(
                new StringReader(""),
                false
        );

        String t1 = "人工智能";
        String t2 = "深度学习";

        String s1 = "人工智能专家（研发部影像所）";
        String s2 = "人工智能算法工程师";

        String d1 = "人工智能专家（研发部影像所） 软件工程师 山东 青岛 青岛海信医疗设备有限公司 国企 医疗设备/器械 #### 职位描述：负责医疗影像相关算法的研发，解决医学影像中的目标检测、分类、识别、分割等问题；医疗影像领域的业务市场调研、业务理解、研发落地和应用，同时协同相关方进行医学数据获取、标注、评估工作；能够从技术角度识别业务的前瞻性方向，并引导团队实现。 任职要求：    1.学历：全日制本科及以上，博士优先；    2.专业：计算机类专业优先；    3.工作经验：毕业两年以上；有相关工作经验者优先。   ";
        String d2 = "人工智能算法工程师 算法工程师 山东 青岛 青岛海信医疗设备有限公司 国企 医疗设备/器械 ####            职位名称：人工智能算法工程师        招聘人数： 2    人              职位描述：      1． 设计与开发先进的深度学习及计算机视觉算法，进行二维/三维医学影像处理。核心算法包括：病灶检测、识别、图像配准、图像分割、信息检索等；    2． 实现并优化医学影像产品所需的核心算法，可以在GPU、CPU及云平台部署；       3． 与客户沟通，分析客户需求，定义人工智能在医学影像以及医院信息化系统中的应用场景；    4      参与调研产品的需求分析报告编写。            招聘要求：        1、学历： 硕士研究生以上      2、专业： 计算机/电子工程/统计学      3、工作经验： 相关专业博士学位，拥有    2     年以上算法开发经验的硕士学位       4、知识要求：拥有计算机视觉及机器学习领域的深厚经验，对神经网络及相关数学基础有着深入理解；学术论文发表于先进会议及期刊或者有成功的产业经验；精通C    /C++     、     Python及Matlab语言     。       5、能力要求：优秀的沟通及表达技巧。            工作地点：   青岛新研发中心       ";

        List<String> targetToken = StrUtils.sentenceSegment(
                t1,
                ikSegmenter
        );

        List<String> sourceToken1 = StrUtils.sentenceSegment(
                s1,
                ikSegmenter
        );

        List<String> sourceToken2 = StrUtils.sentenceSegment(
                s2,
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
