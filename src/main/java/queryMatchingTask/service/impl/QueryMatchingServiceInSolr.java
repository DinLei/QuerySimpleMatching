package queryMatchingTask.service.impl;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import queryMatchingTask.repository.IJdSolrDao;
import queryMatchingTask.repository.impl.JdSolrDaoImpl;
import queryMatchingTask.service.IQueryMatchingService;
import queryMatchingTask.utils.SolrCore;
import queryMatchingTask.utils.StrUtils;
import queryMatchingTask.utils.Utils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

@Service("jdSolr")
public class QueryMatchingServiceInSolr implements IQueryMatchingService {
    private final static Logger LOG = LoggerFactory.getLogger(QueryMatchingServiceInSolr.class);

    @Resource(name="JDSolrDaoImpl")
    private IJdSolrDao jdSolrDaoImpl;

    private static IKSegmenter ikSegmenter = new IKSegmenter(
            new StringReader(""), true);

    private final String sortedBy = SolrCore.getSortedBy();
    private final List<String> fl = SolrCore.getFlFields();
    private final int num = SolrCore.getQueryNum();
    private final String collection = SolrCore.getSolrCollection();
    private final String queryKey = SolrCore.getQueryKey();

    public String bestMatchingJD(String companyId, String query) throws IOException, SolrServerException {
        String resJdId = null;
        double maxScore = -1.0;
        String target = StrUtils.stringRegular(query);
        List<String> targetTokens = StrUtils.sentenceSegment(target, ikSegmenter);

        Map<String, Map<String, String>> JDs = jdSolrDaoImpl.jdSearchByCompanyId(
                queryKey, companyId, num, fl, sortedBy, collection);

        for (Map.Entry<String, Map<String, String>> jdEntry: JDs.entrySet()) {
            String tmpId = jdEntry.getKey();
            Map<String, String> tmpJd = jdEntry.getValue();

            List<String> titleTokens = StrUtils.sentenceSegment(
                    StrUtils.stringRegular(tmpJd.get("title")), ikSegmenter);
            List<String> descTokens = StrUtils.sentenceSegment(
                    StrUtils.stringRegular(tmpJd.get("desc")), ikSegmenter);

            double titleScore = Utils.getMatchingScore(titleTokens, targetTokens);
            double descScore = Utils.getMatchingScore(descTokens, targetTokens);

            double tmpScore = titleScore + 0.5 * descScore;

            if (tmpScore > maxScore) {
                resJdId = tmpId;
                maxScore = tmpScore;
            }
        }
        return (maxScore>0) ? resJdId : "";
    }
}
