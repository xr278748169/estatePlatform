package com.kerry.estate.owner.web;

import com.kerry.dto.WechatCache;
import com.kerry.estate.ValidFactory;
import com.kerry.estate.client.CaptchaClient;
import com.kerry.estate.dto.AuthDto;
import com.kerry.estate.owner.client.OwnerClient;
import com.kerry.estate.owner.model.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 业主接口
 * Created by wangshen on 2017/8/2.
 */
@RestController
@RequestMapping("/api/estate/open/owner")
public class OwnAuthRestful {

    @Autowired
    private CaptchaClient captchaClient;

    @Autowired
    private OwnerClient ownerClient;


    /**
     * 获取业主认证短信验证码
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sms/{telephone}", method = RequestMethod.GET)
    @ResponseBody
    public String smsCode(@PathVariable("telephone") String telephone){
        return captchaClient.generateSmsCode(telephone);
    }

    /**
     * 获取业主认证信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/get/auth/{token}", method = RequestMethod.GET)
    @ResponseBody
    public WechatCache getOwnAuth(@PathVariable("token") String token){
        return ownerClient.getOwnAuth(token);
    }

    /**
     * 业主信息认证
     * @param authDto
     * @param result
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String ownAuth(@Valid @RequestBody AuthDto authDto, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            return ValidFactory.message(result);
        }
        authDto.setTuId(request.getAttribute("tuId").toString());
        return ownerClient.ownAuth(authDto);
    }

    /**
     * 根据业主编号查询
     * @param ownId
     * @return
     */
    @RequestMapping(value = "/find/{ownId}", method = RequestMethod.GET)
    @ResponseBody
    public OwnerModel findById(@PathVariable("ownId") String ownId) {
        return ownerClient.selectById(ownId);
    }

    /**
     * 获取业主家庭成员
     * @param ownId
     * @return
     */
    @RequestMapping(value = "/list/family/{ownId}", method = RequestMethod.GET)
    @ResponseBody
    public List<OwnerModel> findFamily(@PathVariable("ownId") String ownId){
        return ownerClient.findFamily(ownId);
    }
}
