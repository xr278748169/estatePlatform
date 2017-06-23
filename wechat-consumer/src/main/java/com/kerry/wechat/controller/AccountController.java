package com.kerry.wechat.controller;

import com.kerry.core.SearchParams;
import com.kerry.wechat.client.AccountClient;
import com.kerry.wechat.model.AccountModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangshen on 2017/6/20.
 */
@RestController
@RequestMapping("/api/wechat/account")
public class AccountController {

    @Autowired
    private AccountClient accountClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return accountClient.findByPage(params, code);
    }

    /**
     * 保存
     * @param accountModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody AccountModel accountModel){
        String code = request.getAttribute("code").toString();
        accountModel.setCreateUser(request.getAttribute("userId").toString());
        return accountClient.insert(accountModel, code);
    }

    /**
     * 修改
     * @param accountModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody AccountModel accountModel){
        accountModel.setUpdateUser(request.getAttribute("userId").toString());
        return accountClient.update(accountModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return accountClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AccountModel selectById(@PathVariable("id") String id){
        return accountClient.selectById(id);
    }
}
