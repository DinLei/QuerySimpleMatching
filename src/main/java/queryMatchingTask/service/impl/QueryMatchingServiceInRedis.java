package queryMatchingTask.service.impl;

import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import queryMatchingTask.repository.impl.JdRedisDaoImpl;
import queryMatchingTask.service.IQueryMatchingService;
import queryMatchingTask.utils.JDUtil;
import queryMatchingTask.utils.StrUtils;
import queryMatchingTask.utils.Utils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;


@Service("jdRedis")
public class QueryMatchingServiceInRedis implements IQueryMatchingService {

    @Resource
    private JdRedisDaoImpl jdDao;

    private static IKSegmenter ikSegmenter = new IKSegmenter(
            new StringReader(""), true);

    @Override
    public String bestMatchingJD(String companyId, String query) throws IOException {
        String resJdId = null;
        int maxScore = -1;
        String target = StrUtils.stringRegular(query);
        List<String> targetTokens = StrUtils.sentenceSegment(target, ikSegmenter);

        Map<String, String> JDs = jdDao.getAllJDs(companyId);
        for (Map.Entry<String, String> jdEntry: JDs.entrySet()) {
            int tmpScore = 0;
            String tmpId = jdEntry.getKey();
            Map<String, String> tmpJd = JDUtil.jdString2map(jdEntry.getValue());
            String title = StrUtils.stringRegular(tmpJd.get("title"));
            String desc = StrUtils.stringRegular(tmpJd.get("desc"));

            List<String> titleTokens = StrUtils.sentenceSegment(title, ikSegmenter);
            List<String> descTokens = StrUtils.sentenceSegment(desc, ikSegmenter);

            tmpScore += Utils.getMatchingScore(titleTokens, targetTokens);
            tmpScore += Utils.getMatchingScore(descTokens, targetTokens) * 0.5;

            if (tmpScore > maxScore) {
                resJdId = tmpId;
                maxScore = tmpScore;
            }
        }
        return resJdId;
    }
}
