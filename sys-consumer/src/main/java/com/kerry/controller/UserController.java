package com.kerry.controller;

import com.kerry.client.UserCilent;
import com.kerry.core.SearchParams;
import com.kerry.system.model.UserModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户信息
 * Created by wangshen on 2017/5/8.
 */
@RestController
@RequestMapping("/api/sys/user")
public class UserController {

    @Autowired
    private UserCilent userCilent;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return userCilent.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestBody UserModel userModel){
        return userCilent.insert(userModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody UserModel userModel) {
        return userCilent.update(userModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return userCilent.delete(id);
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserModel selectById(@PathVariable("id") String id){
        return userCilent.selectById(id);
    }

}
