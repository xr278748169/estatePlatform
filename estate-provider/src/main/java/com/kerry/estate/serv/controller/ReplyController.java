package com.kerry.estate.serv.controller;

import com.kerry.estate.serv.inter.IReplyInter;
import com.kerry.estate.serv.model.ReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回复消息管理
 * Created by wangshen on 2017/7/27.
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private IReplyInter replyInter;

    /**
     * 获取指定业务的回复内容
     * @param bussId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{bussId}/list", method = RequestMethod.GET)
    public List<ReplyModel> bussReplyList(@PathVariable("bussId") String bussId) throws Exception {
        ReplyModel params = new ReplyModel();
        params.setBussId(bussId);
        return replyInter.findByCondition(params);
    }

    /**
     * 保存
     * @param replyModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody ReplyModel replyModel, @PathVariable("code") String code) throws Exception {
        replyModel.setAuthCode(code);
        return replyInter.insert(replyModel);
    }

    /**
     * 修改
     * @param replyModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody ReplyModel replyModel) throws Exception {
        return replyInter.update(replyModel);
    }
}
