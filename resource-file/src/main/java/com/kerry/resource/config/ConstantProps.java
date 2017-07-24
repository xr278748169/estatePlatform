package com.kerry.resource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置参数
 * Created by wangshen on 2017/7/24.
 */
@Component
@ConfigurationProperties(prefix = "constant")
public class ConstantProps {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
