package com.kerry.estate.base.client;

import com.kerry.estate.base.model.ResFileModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 物业资源文件
 * Created by wangshen on 2017/7/27.
 */
@FeignClient(name = "estate-provider")
public interface ResFileClient {

    @RequestMapping(value = "/res/{bussId}/list", method = RequestMethod.GET)
    List<ResFileModel> bussResList(@PathVariable("bussId") String bussId);
}
