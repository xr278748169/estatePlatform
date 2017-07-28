package com.kerry.estate.base.controller;

import com.kerry.estate.base.inter.IResFileInter;
import com.kerry.estate.base.model.ResFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 物业资源文件管理
 * Created by wangshen on 2017/7/27.
 */
@RestController
@RequestMapping("/res")
public class ResFileController {

    @Autowired
    private IResFileInter resFileInter;

    /**
     * 获取指定业务的资源列表
     * @param bussId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{bussId}/list", method = RequestMethod.GET)
    public List<ResFileModel> bussResList(@PathVariable("bussId") String bussId) throws Exception {
        ResFileModel params = new ResFileModel();
        params.setBussId(bussId);
        return resFileInter.findByCondition(params);
    }
}
