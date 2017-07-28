package com.kerry.estate.serv.controller;

import com.kerry.estate.serv.client.ReplyClient;
import com.kerry.estate.serv.model.ReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 回复消息管理
 * Created by wangshen on 2017/7/27.
 */
@RestController
@RequestMapping("/api/estate/reply")
public class ReplyController {

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

    /**
     * 保存
     * @param replyModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody ReplyModel replyModel){
        String code = request.getAttribute("code").toString();
        replyModel.setCreateUser(request.getAttribute("userId").toString());
        return replyClient.insert(replyModel, code);
    }

    /**
     * 修改
     * @param replyModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody ReplyModel replyModel){
        replyModel.setUpdateUser(request.getAttribute("userId").toString());
        return replyClient.update(replyModel);
    }

}
