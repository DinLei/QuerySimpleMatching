package com.zhaopin.algorithm.queryMatchingTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class JDMatchingServiceApplication extends SpringBootServletInitializer{
	private static Logger logger = LoggerFactory.getLogger(JDMatchingServiceApplication.class);
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JDMatchingServiceApplication.class);
    }

	public static void main(String[] args) {
		new SpringApplicationBuilder()
                .sources(JDMatchingServiceApplication.class)
                .web(true)
                .run(args);
		logger.info("JDMatchingByQuery启动成功！当前版: 1.1");
	}
}