package com.kerry.estate.serv.client;

import com.kerry.estate.serv.model.ReplyModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 回复消息管理
 * Created by wangshen on 2017/7/27.
 */
@FeignClient(name = "estate-provider")
public interface ReplyClient {

    @RequestMapping(value = "/reply/{bussId}/list", method = RequestMethod.GET)
    List<ReplyModel> bussReplyList(@PathVariable("bussId") String bussId);

    @RequestMapping(value = "/reply/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody ReplyModel replyModel, @PathVariable("code") String code);

    @RequestMapping(value = "/reply/update", method = RequestMethod.POST)
    String update(@RequestBody ReplyModel replyModel);
}
