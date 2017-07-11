package com.kerry.estate.base.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.client.BuildingClient;
import com.kerry.estate.base.model.BuildingModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 楼宇管理
 * Created by wangshen on 2017/7/10.
 */
@RestController
@RequestMapping("/api/estate/building")
public class BuildingController {

    @Autowired
    private BuildingClient buildingClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return buildingClient.findByPage(params, code);
    }

    /**
     * 保存
     * @param buildingModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody BuildingModel buildingModel){
        String code = request.getAttribute("code").toString();
        buildingModel.setCreateUser(request.getAttribute("userId").toString());
        return buildingClient.insert(buildingModel, code);
    }

    /**
     * 修改
     * @param buildingModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody BuildingModel buildingModel){
        buildingModel.setUpdateUser(request.getAttribute("userId").toString());
        return buildingClient.update(buildingModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return buildingClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BuildingModel selectById(@PathVariable("id") String id){
        return buildingClient.selectById(id);
    }
}
