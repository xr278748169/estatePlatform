package com.kerry.resource.config;

import com.kerry.resource.utils.OSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by wangshen on 2017/7/25.
 */
@Component
public class StartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("---=======启动初始化开始=======---");
        OSSUtils.ossClient = OSSUtils.getOSSClient();
    }
}
