package com.kerry.estate.base.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.client.CommunityClient;
import com.kerry.estate.base.model.CommunityModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 小区管理
 * Created by wangshen on 2017/7/4.
 */
@RestController
@RequestMapping("/api/estate/community")
public class CommunityController {

    @Autowired
    private CommunityClient communityClient;


    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return communityClient.findByPage(params, code);
    }

    /**
     * 保存
     * @param communityModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody CommunityModel communityModel){
        String code = request.getAttribute("code").toString();
        communityModel.setCreateUser(request.getAttribute("userId").toString());
        return communityClient.insert(communityModel, code);
    }

    /**
     * 修改
     * @param communityModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody CommunityModel communityModel){
        communityModel.setUpdateUser(request.getAttribute("userId").toString());
        return communityClient.update(communityModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return communityClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommunityModel selectById(@PathVariable("id") String id){
        return communityClient.selectById(id);
    }
}
