package com.kerry.estate.msg.client;

import com.kerry.core.SearchParams;
import com.kerry.estate.msg.model.EssayModel;
import com.kerry.estate.owner.model.OwnerModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 物业新闻消息
 * Created by wangshen on 2017/7/21.
 */
@FeignClient(name = "estate-provider")
public interface EssayClient {

    @RequestMapping(value = "/essay/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/essay/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody EssayModel essayModel, @PathVariable("code") String code);

    @RequestMapping(value = "/essay/update", method = RequestMethod.POST)
    String update(@RequestBody EssayModel essayModel);

    @RequestMapping(value = "/essay/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/essay/select/{id}", method = RequestMethod.GET)
    EssayModel selectById(@PathVariable("id") String id);
}
