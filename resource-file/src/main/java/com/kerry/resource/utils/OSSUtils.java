package com.kerry.resource.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.kerry.config.Constant;
import com.kerry.resource.config.ConstantProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * oss工具
 * Created by wangshen on 2017/7/25.
 */
public class OSSUtils {

    private static final Logger logger = LoggerFactory.getLogger(OSSUtils.class);

    public static String diskName = "estate/files/";

    /**
     * 实例对象
     */
    public static OSSClient ossClient = null;

    /**
     * 获取阿里云OSS客户端对象
     * */
    public static final OSSClient getOSSClient(String endPoint, String accessKeyId, String accessKeySercet){
        return new OSSClient(endPoint, accessKeyId, accessKeySercet);
    }

    public static final String uploadObject2OSS(OSSClient client, File file,String bucketName) {
        String resultStr = null;
        try {
            InputStream is = new FileInputStream(file);
            String fileName = file.getName();
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            PutObjectResult putResult = client.putObject(bucketName, diskName + fileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            logger.error("上传阿里云OSS服务器异常." + e.getMessage());
        }
        return resultStr;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType与文件存放位置
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static final String getContentType(String fileName){
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
        if("bmp".equalsIgnoreCase(fileExtension)) {
            diskName="estate/images/";
            return "image/bmp";
        }
        if("gif".equalsIgnoreCase(fileExtension)) {
            diskName="estate/images/";
            return "image/gif";
        }
        if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  || "png".equalsIgnoreCase(fileExtension) ){
            diskName="estate/images/";
            return "image/jpeg";
        }
        if("html".equalsIgnoreCase(fileExtension)) {
            diskName="estate/images/";
            return "text/html";
        }
        if("txt".equalsIgnoreCase(fileExtension)) {
            diskName="estate/files/";
            return "text/plain";
        }
        if("vsd".equalsIgnoreCase(fileExtension)) {
            diskName="estate/msfile/";
            return "application/vnd.visio";
        }
        if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            diskName="estate/msfile/";
            return "application/vnd.ms-powerpoint";
        }
        if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            diskName="estate/msfile/";
            return "application/msword";
        }
        if("xml".equalsIgnoreCase(fileExtension)) {
            diskName="estate/xml/";
            return "text/xml";
        }
        return "text/html";
    }
}
