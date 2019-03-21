package queryMatchingTask.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queryMatchingTask.response.ServerResponse;
import queryMatchingTask.response.StatusCode;
import queryMatchingTask.service.IQueryMatchingService;
import queryMatchingTask.service.impl.QueryMatchingServiceInSolr;
import queryMatchingTask.utils.StrUtils;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class QueryMatchingController {
    private static final Logger LOG = LoggerFactory.getLogger(QueryMatchingController.class);

    @Resource(name="jdSolr")
    private IQueryMatchingService iQueryMatchingService;

    @RequestMapping("/search/JDMatching")
    public ServerResponse getBestMatchingJDId(String companyId, String query) {

        String candidateJD="";

        LOG.debug("JDMatching接口收到参数: companyId=" + companyId + " query=" + query);

        if (StrUtils.isBlank(companyId)) {
            LOG.debug("请求的公司ID为空！");
            return new ServerResponse(StatusCode.EMPTY_COMPANY_ID);
        } else if (StrUtils.isBlank(query)) {
            LOG.debug("请求的搜索词为空！");
            return new ServerResponse(StatusCode.EMPTY_QUERY);
        }

        try {
            candidateJD = iQueryMatchingService.bestMatchingJD(companyId, query);
        } catch (IOException e) {
            LOG.debug(String.format("搜索词输入有误或JD信息错误: %s", e));
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return new ServerResponse(StatusCode.SUCCESS, candidateJD);
    }
}
