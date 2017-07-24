package com.kerry.estate.msg.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.msg.client.EssayClient;
import com.kerry.estate.msg.model.EssayModel;
import com.kerry.estate.owner.model.OwnerModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 物业新闻消息
 * Created by wangshen on 2017/7/21.
 */
@RestController
@RequestMapping("/api/estate/essay")
public class EssayController {

    @Autowired
    private EssayClient ownerClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return ownerClient.findByPage(params, code);
    }

    /**
     * 保存
     * @param essayModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody EssayModel essayModel){
        String code = request.getAttribute("code").toString();
        essayModel.setCreateUser(request.getAttribute("userId").toString());
        return ownerClient.insert(essayModel, code);
    }

    /**
     * 修改
     * @param essayModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody EssayModel essayModel){
        essayModel.setUpdateUser(request.getAttribute("userId").toString());
        return ownerClient.update(essayModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return ownerClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EssayModel selectById(@PathVariable("id") String id){
        return ownerClient.selectById(id);
    }
}
