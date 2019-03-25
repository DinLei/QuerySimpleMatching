package com.zhaopin.algorithm.queryMatchingTask.service.impl;

import com.zhaopin.algorithm.queryMatchingTask.model.JD;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import com.zhaopin.algorithm.queryMatchingTask.repository.IJdSolrDao;
import com.zhaopin.algorithm.queryMatchingTask.service.IQueryMatchingService;
import com.zhaopin.algorithm.queryMatchingTask.utils.SolrCore;
import com.zhaopin.algorithm.queryMatchingTask.utils.StrUtils;
import com.zhaopin.algorithm.queryMatchingTask.utils.Utils;

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

        Map<String, JD> JDs = jdSolrDaoImpl.jdSearchByCompanyId(
                queryKey, companyId, num, fl, sortedBy, collection);

        for (Map.Entry<String, JD> jdEntry: JDs.entrySet()) {
            String tmpId = jdEntry.getKey();
            JD tmpJd = jdEntry.getValue();

            List<String> titleTokens = StrUtils.sentenceSegment(
                    StrUtils.stringRegular(tmpJd.getJdTitle()), ikSegmenter);
            List<String> descTokens = StrUtils.sentenceSegment(
                    StrUtils.stringRegular(tmpJd.getJdDesc()), ikSegmenter);

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
