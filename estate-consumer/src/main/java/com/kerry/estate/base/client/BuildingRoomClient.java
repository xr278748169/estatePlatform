package com.kerry.estate.base.client;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.model.BuildingRoomModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 楼宇房间管理
 * Created by wangshen on 2017/7/10.
 */
@FeignClient(name = "estate-provider")
public interface BuildingRoomClient {

    @RequestMapping(value = "/building/room/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/building/room/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody BuildingRoomModel communityModel, @PathVariable("code") String code);

    @RequestMapping(value = "/building/room/update", method = RequestMethod.POST)
    String update(@RequestBody BuildingRoomModel communityModel);

    @RequestMapping(value = "/building/room/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/building/room/select/{id}", method = RequestMethod.GET)
    BuildingRoomModel selectById(@PathVariable("id") String id);
}
