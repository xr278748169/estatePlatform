package com.kerry.estate.base.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.client.BuildingRoomClient;
import com.kerry.estate.base.model.BuildingRoomModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 楼宇房间管理
 * Created by wangshen on 2017/7/10.
 */
@RestController
@RequestMapping("/api/estate/building/room")
public class BuildingRoomController {

    @Autowired
    private BuildingRoomClient buildingRoomClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return buildingRoomClient.findByPage(params, code);
    }

    /**
     * 保存
     * @param buildingRoomModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody BuildingRoomModel buildingRoomModel){
        String code = request.getAttribute("code").toString();
        buildingRoomModel.setCreateUser(request.getAttribute("userId").toString());
        return buildingRoomClient.insert(buildingRoomModel, code);
    }

    /**
     * 修改
     * @param buildingRoomModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody BuildingRoomModel buildingRoomModel){
        buildingRoomModel.setUpdateUser(request.getAttribute("userId").toString());
        return buildingRoomClient.update(buildingRoomModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return buildingRoomClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BuildingRoomModel selectById(@PathVariable("id") String id){
        return buildingRoomClient.selectById(id);
    }
}
