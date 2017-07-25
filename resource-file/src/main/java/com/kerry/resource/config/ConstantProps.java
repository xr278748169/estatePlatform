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

    private String ossCustomUrl;

    private String endPoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String ossPublicHost;

    private String ossPrivateHost;

    private String ossBucketName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOssCustomUrl() {
        return ossCustomUrl;
    }

    public void setOssCustomUrl(String ossCustomUrl) {
        this.ossCustomUrl = ossCustomUrl;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getOssPublicHost() {
        return ossPublicHost;
    }

    public void setOssPublicHost(String ossPublicHost) {
        this.ossPublicHost = ossPublicHost;
    }

    public String getOssPrivateHost() {
        return ossPrivateHost;
    }

    public void setOssPrivateHost(String ossPrivateHost) {
        this.ossPrivateHost = ossPrivateHost;
    }

    public String getOssBucketName() {
        return ossBucketName;
    }

    public void setOssBucketName(String ossBucketName) {
        this.ossBucketName = ossBucketName;
    }
}
