package com.kerry.estate.base.controller;

import com.kerry.estate.base.client.ResFileClient;
import com.kerry.estate.base.model.ResFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物业资源文件管理
 * Created by wangshen on 2017/7/27.
 */
@RestController
@RequestMapping("/api/estate/res")
public class ResFileController {

    @Autowired
    private ResFileClient resFileClient;

    /**
     * 获取指定业务的资源
     * @param bussId
     * @return
     */
    @RequestMapping(value = "/res/{bussId}/list", method = RequestMethod.GET)
    @ResponseBody
    public List<ResFileModel> bussResList(@PathVariable("bussId") String bussId){
        return resFileClient.bussResList(bussId);
    }
}
