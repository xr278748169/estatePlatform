package com.kerry.estate.serv.web;

import com.kerry.estate.serv.client.ReplyClient;
import com.kerry.estate.serv.model.ReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回复信息微信接口
 * Created by wangshen on 2017/8/14.
 */
@RestController
@RequestMapping("/api/estate/open/reply")
public class ReplyRestfull {

    @Autowired
    private ReplyClient replyClient;

    /**
     * 获取指定业务的回复内容
     * @param bussId
     * @return
     */
    @RequestMapping(value = "/{bussId}/list", method = RequestMethod.GET)
    @ResponseBody
    public List<ReplyModel> bussReplyList(@PathVariable("bussId") String bussId){
        return replyClient.bussReplyList(bussId);
    }
}
