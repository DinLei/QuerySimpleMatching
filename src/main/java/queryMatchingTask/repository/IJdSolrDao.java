package queryMatchingTask.repository;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IJdSolrDao {

    Map<String, Map<String, String>> jdSearchByCompanyId(
            String queryKey, String compandId, int num,
            List<String> fl, String sortedBy, String core) throws IOException, SolrServerException;
}
