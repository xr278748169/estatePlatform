package com.kerry.estate.owner.client;

import com.kerry.core.SearchParams;
import com.kerry.dto.WechatCache;
import com.kerry.estate.dto.AuthDto;
import com.kerry.estate.owner.model.OwnerModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 业主信息管理
 * Created by wangshen on 2017/7/21.
 */
@FeignClient(name = "estate-provider")
public interface OwnerClient {

    @RequestMapping(value = "/owner/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/owner/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody OwnerModel ownerModel, @PathVariable("code") String code);

    @RequestMapping(value = "/owner/update", method = RequestMethod.POST)
    String update(@RequestBody OwnerModel ownerModel);

    @RequestMapping(value = "/owner/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/owner/select/{id}", method = RequestMethod.GET)
    OwnerModel selectById(@PathVariable("id") String id);

    @RequestMapping(value = "/owner/auth", method = RequestMethod.POST)
    String ownAuth(@RequestBody AuthDto authDto);

    @RequestMapping(value = "/owner/get/auth/{token}", method = RequestMethod.GET)
    WechatCache getOwnAuth(@PathVariable("token") String token);

    @RequestMapping(value = "/owner/list/family/{ownId}", method = RequestMethod.GET)
    List<OwnerModel> findFamily(@PathVariable("ownId") String ownId);
}
