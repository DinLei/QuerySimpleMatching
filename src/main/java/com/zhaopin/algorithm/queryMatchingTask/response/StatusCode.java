package com.zhaopin.algorithm.queryMatchingTask.response;


import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class StatusCode {

    public static final int HTTP_SUCCESS = 200;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_SERVER_ERROR = 500;

    public static final int SUCCESS = 10000;

    public static final int EMPTY_COMPANY_ID = 10001;
    public static final int EMPTY_QUERY = 10002;
    public static final int SERVER_ERROR = -1;
    public static final int SOLR_ERROR = 10003;


    private static Map<Integer, String> code2Message;
    static {
        code2Message = new TreeMap<>();
        code2Message.put(SUCCESS, "成功！");
        code2Message.put(EMPTY_COMPANY_ID, "公司ID参数为空！");
        code2Message.put(EMPTY_QUERY, "搜索查询词为空！");
        code2Message.put(SERVER_ERROR, "接口服务异常！");
        code2Message.put(SOLR_ERROR, "SOLR查询出错！");

        code2Message = Collections.unmodifiableMap(code2Message);
    }

    private static Map<Integer, Integer> code2HttpCode;
    static {
        code2HttpCode = new TreeMap<>();
        code2HttpCode.put(SUCCESS, HTTP_SUCCESS);
        code2HttpCode.put(EMPTY_QUERY, HTTP_BAD_REQUEST);
        code2HttpCode.put(SERVER_ERROR, HTTP_SERVER_ERROR);
    }

    public static int toHttpStatus(int code) {
        return code2HttpCode.getOrDefault(code, 0);
    }

    public static String getMessage(int code) {
        return code2Message.getOrDefault(code, "未知状态码！");
    }

}
