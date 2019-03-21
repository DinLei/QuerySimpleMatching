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

        String s1 = "销售高管";
        String s2 = "苗木销售中高管 销售总监 全国 天津 大连创意人力资源顾问有限公司 民营 专业服务/咨询(财会/法律/人力资源等) 快速消费品（食品/饮料/烟酒/日化）,加工制造（原料加工/模具）,房地产/建筑/建材/工程 ####    大连苗木集团      ●营销总监     工作职责：  1、主持公司花卉苗木营销管理工作；  2、组织制定本部门年度、季度、月度经营工作计划并按照计划实行；  3、负责花卉苗木的市场开发、引进、培育、基地监管工作；  4、负责花卉苗木采购、销售信息收集汇总工作；  5、负责编制并不断完善公司宣传资料并组织公司的对外宣传工作。  任职资格：  1、市场信息判断、分析开拓能力强，有自己独特的营销策划思路，三年以上同岗位管理经验；  2、具备优秀的领导力、营销策划力、全局掌控力、问题解决力；  3、能够带领团队完成产品宣传、促销、发布会、展会、资源开发等重要市场工作；  4、具有园林（彩叶树、花卉）、农林类产品销售经验及销售渠道者优先考虑；  5、市场营销、园林等相关专业本科学历，35-45岁，身体健康，适应出差。工作地点：北京、大连       ●京津区域苗木销售副总     1、五年以上苗木销售管理工作经验，三年以上同岗位经验，有京津区域销售渠道者优先考虑；  2、具备市场开拓能力，营销策划思路，团队管理能力；  3、具备责任心强，语言表达能力和沟通协调能力；  4、市场营销、园林等相关专业大专以上学历，35-45岁，工作地点：北京       ●苗木销售部长     职位描述：  1、建立销售管理体系及组织实施管理；  2、制定市场营销策略和发展计划，并组织实施；  3、组织完成销售目标；  4、销售团队建设与管理。  任职资格：  1、身体健康，统招本科及以上学历，园林或市场营销策划等相关专业；  2、三年以上大中型企业销售管理经验；园林或苗木销售管理工作经验优先；  3、具有较强的市场分析、市场开发、管理能力和团队建设能力；  4、文笔较好，熟练操作办公软件；  5、语言表达能力和沟通协调能力强；综合素质高；亲和力较好；工作责任心强。       ●苗木销售区域经理    职位描述：  1、协助建立销售管理体系及组织实施管理；  2、开展实施市场营销策略和发展计划，并完成销售目标；  3、市场信息以及同行竞争对手信息的收集与分析；  4、维护现有客户的合作关系，并不断开拓新的市场客户。  任职资格：  1、年龄28-35岁，统招本科及以上学历，农林或市场营销等相关专业；  2、三年以上园林或苗木销售管理相关工作经验；  3、具有较强的市场分析、市场开发和团队管理能力；  4、工作责任心强，语言表达能力和沟通协调能力强。  5、身体健康，适应驻外或出差。       ●苗木销售主管    职位描述：  1、负责新客户开发，合作洽谈；  2、负责已有客户资源维护与跟进，提高客户的满意度；  3、采用多渠道方式推广基地苗木产品，如实体销售推广、网络销售推广等。  任职资格：  1、专业、学历不限，具有市场营销或农林专业更佳；  2、一年以上市场营销经验，具有苗木相关产品销售经验更佳；  3、热爱苗木销售，亲和力佳，语言表达能力和沟通协调能力强，性格开朗，形象气质好；  4、团队意识强，品行端正，综合素质佳。  5、身体健康，适应出差工作。  6、工作地点：大连、西安。 ";
        String s3 = "医疗器械销售经理 医疗器械销售 辽宁 大连 大连创意人力资源顾问有限公司 民营 专业服务/咨询(财会/法律/人力资源等) 快速消费品（食品/饮料/烟酒/日化）,加工制造（原料加工/模具）,房地产/建筑/建材/工程 ####    医疗器械销售经理          【任职要求】         1.  学历：本科以上。        2.  专业：医疗学、预防医学、临床医学、药学        3.  工作城市：大连         【岗位职责】         1.  负责医院的耗材和器械开发推广        2.  根据市场营销计划，完成公司销售指标        3.  充分了解市场状态，及时收集和反馈客户信息和市场情况        4.  开拓新市场、发展新客户，提高产品的市场占有率        5.  树立良好的职业形象，公司商业机密保密        6  ．大连本地优先。         【薪资待遇】         薪资组成： 基本工资 + 绩效工资 + 年终奖        福利待遇：专业培训 高额提成 五险一金 绩效奖金 餐饮补贴 交通补贴 通讯补贴 差旅补助     ";

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

        List<String> sourceToken1 = StrUtils.sentenceSegment(
                s3,
                ikSegmenter
        );

        List<String> sourceToken2 = StrUtils.sentenceSegment(
                s2,
                ikSegmenter
        );

        List<String> targetToken = StrUtils.sentenceSegment(
                "销售高管",
                ikSegmenter
        );

        for (String s: sourceToken2) System.out.println(s);

        System.out.println("=====");
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
