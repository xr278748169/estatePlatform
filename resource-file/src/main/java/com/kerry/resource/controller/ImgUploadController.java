package com.kerry.resource.controller;

import com.alibaba.fastjson.JSONObject;
import com.kerry.core.ResponseEntity;
import com.kerry.resource.config.ConstantProps;
import com.kerry.resource.utils.OSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传服务
 * Created by wangshen on 2017/7/24.
 */
@RestController
@RequestMapping("/file/images")
public class ImgUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImgUploadController.class);

    @Autowired
    private ConstantProps constantProps;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.createErrorJsonResponse("未选择文件");
        }
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        //判断后缀
        if(!suffixName.toUpperCase().equals(".JPEG")&&!suffixName.toUpperCase().equals(".JPG")&&!suffixName.toUpperCase().equals(".GIF")&&!suffixName.toUpperCase().equals(".PNG")){
            return ResponseEntity.createErrorJsonResponse("仅支持jpg,gif,png图片格式");
        }
        //文件大小
        long size = file.getSize();
        logger.info("上传的文件大小：" + size);
        if(size>(2*1024*1024)){
            return ResponseEntity.createErrorJsonResponse("上传图片大小不能超过 2MB!");
        }
        // 文件在磁盘的存储路径
        String filePath = constantProps.getFilePath();
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath+fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //写入到oss
            if(OSSUtils.ossClient==null){
                OSSUtils.ossClient = OSSUtils.getOSSClient();
            }
            String result = OSSUtils.uploadObject2OSS(OSSUtils.ossClient,dest);
            if(result != null && !result.equals("")){
                logger.info("oss上传结果：" + result);
                String ossFileUrl = constantProps.getOssCustomUrl()+OSSUtils.diskName+fileName;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("url",ossFileUrl);
                jsonObject.put("fileName",fileName);
                return ResponseEntity.createNormalJsonResponse(jsonObject);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.createErrorJsonResponse("上传失败");
    }
}
