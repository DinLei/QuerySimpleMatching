package queryMatchingTask.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface IQueryMatchingService {

    /**
     *
     * @param companyId: 具体公司ID
     * @param query: 具体搜索词
     * @return : 匹配到的JD_ID
     */
    String bestMatchingJD(String companyId, String query) throws IOException, SolrServerException;
}
