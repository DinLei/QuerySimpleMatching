package queryMatchingTask.service.impl;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.core.IKSegmenter;
import queryMatchingTask.repository.JDSolrDao;
import queryMatchingTask.service.IQueryMatchingService;
import queryMatchingTask.utils.JDUtil;
import queryMatchingTask.utils.SolrCore;
import queryMatchingTask.utils.StrUtils;
import queryMatchingTask.utils.Utils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

public class QueryMatchingServiceInSolr implements IQueryMatchingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(QueryMatchingServiceInSolr.class);

    @Resource(name = "solrIndexSearch")
    private JDSolrDao jdSolrDao;

    private static IKSegmenter ikSegmenter = new IKSegmenter(
            new StringReader(""), true);

    private final String sortedBy = SolrCore.getSortedBy();
    private final List<String> fl = SolrCore.getFlFields();
    private final int num = SolrCore.getQueryNum();
    private final String collection = SolrCore.getSolrCollection();
    private final String queryKey = SolrCore.getQueryKey();

    public String bestMatchingJD(String companyId, String query) throws IOException, SolrServerException {
        String resJdId = null;
        int maxScore = -1;
        String target = StrUtils.stringRegular(query);
        List<String> targetTokens = StrUtils.sentenceSegment(target, ikSegmenter);

        Map<String, Map<String, String>> JDs = jdSolrDao.jdSearchByCompanyId(
                queryKey, companyId, num,
                fl, sortedBy, collection);

        for (Map.Entry<String, Map<String, String>> jdEntry: JDs.entrySet()) {
            int tmpScore = 0;
            String tmpId = jdEntry.getKey();
            Map<String, String> tmpJd = jdEntry.getValue();
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
