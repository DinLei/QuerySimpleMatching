package queryMatchingTask.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SolrCore {
    private static final ResourceBundle rb = ResourceBundle.getBundle(
            "application", Locale.getDefault());
    private static final String solrCollection =  rb.getString("solr.collection");

    private static final String sortedBy = rb.getString("solr.query.sortedby");

    private static final String queryKey = rb.getString("solr.query.key");

    private static final List<String> flFields = Arrays.asList(
            rb.getString("solr.fl.fields").split(",")
    );

    private static final int queryNum = Integer.parseInt(rb.getString("solr.query.num"));

    public static String getSolrCollection() {
        return solrCollection;
    }

    public static String getSortedBy() {
        return sortedBy;
    }

    public static List<String> getFlFields() {
        return flFields;
    }

    public static int getQueryNum() {
        return queryNum;
    }

    public static String getQueryKey() {
        return queryKey;
    }
}
