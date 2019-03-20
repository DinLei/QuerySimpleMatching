package queryMatchingTask.repository;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JDSolrDao {
    private static final Logger LOG = LoggerFactory.getLogger(JDSolrDao.class);

    @Autowired
    private SolrClient solrClient;

    public Map<String, Map<String, String>> jdSearchByCompanyId(
            String queryKey, String compandId, int num,
            List<String> fl, String sortedBy, String core) throws IOException, SolrServerException {
        Map<String, Map<String, String>> res = new HashMap<>();

        SolrQuery query = new SolrQuery();
        query.setQuery(queryKey+":"+compandId);
        query.setStart(0);
        query.setRows(num);

        query.setFields(String.join(",", fl));
        query.addSort(sortedBy, SolrQuery.ORDER.desc);

        QueryResponse queryResponse = solrClient.query(core, query);

        SolrDocumentList documents = queryResponse.getResults();
        documents.forEach(
//                entries -> products.add(entries.get(keyField).toString())
                entries -> {
                    String jdId = entries.get(fl.get(0)).toString();
                    String jdTitle = entries.get(fl.get(1)).toString();
                    String jdDesc = entries.get(fl.get(2)).toString();
                    if (! res.containsKey(jdId)) {
                        Map<String, String> tmp = new HashMap<>();
                        tmp.put("title", jdTitle);
                        tmp.put("desc", jdDesc);
                        res.put(jdId, tmp);
                    }
                }
        );
        LOG.debug(String.format("RecInPop output num: %d", res.size()));
        return res;
    }
}
