package com.kerry.wechat.controller;

import com.kerry.wechat.inter.ITUserInter;
import com.kerry.wechat.model.TUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信用户
 * Created by wangshen on 2017/8/2.
 */
@RestController
@RequestMapping("/tuser")
public class TUserController {

    @Autowired
    private ITUserInter itUserInter;


    /**
     * 更改业务状态
     * @param tuId
     * @param state
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update/{tuId}/{state}", method = RequestMethod.GET)
    public String updateBussState(@PathVariable("tuId") String tuId, @PathVariable("state") String state) throws Exception {
        TUserModel tUserModel = new TUserModel();
        tUserModel.setTuId(tuId);
        tUserModel.setAuthBuss(state);
        return itUserInter.update(tUserModel);
    }
}
