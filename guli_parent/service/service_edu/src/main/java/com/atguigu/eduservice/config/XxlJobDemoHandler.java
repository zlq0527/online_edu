package com.atguigu.eduservice.config;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@Component
public class XxlJobDemoHandler {
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("Java,Hello World");
        XxlJobLogger.log("param" + param);
        return ReturnT.SUCCESS;
    }
}
