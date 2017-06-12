package com.kerry.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.client.RoleResClient;
import com.kerry.client.UserCilent;
import com.kerry.core.ResponseEntity;
import com.kerry.member.client.SercretClient;
import com.kerry.member.dto.Login;
import com.kerry.member.service.LoginService;
import com.kerry.model.ClientUser;
import com.kerry.system.model.RoleResModel;
import com.kerry.system.model.UserModel;
import com.kerry.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录模块
 * Created by wangshen on 2017/5/19.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserCilent userCilent;

    @Autowired
    private SercretClient sercretClient;

    @Autowired
    private RoleResClient roleResClient;

    /**
     * 登录请求处理
     * @param login
     * @return
     */
    @Override
    public String login(Login login) {
        if(login.getAccessToken()==null||login.getAccessToken().equals("")){
            return ResponseEntity.createErrorJsonResponse("登录信息无效");
        }
        if(login.getUserName()==null||login.getUserName().equals("")){
            return ResponseEntity.createErrorJsonResponse("请输入用户名");
        }
        if(login.getPassword()==null||login.getPassword().equals("")){
            return ResponseEntity.createErrorJsonResponse("请输入密码");
        }
        UserModel userModel = userCilent.selectByLoginName(login.getUserName());
        if(userModel==null){
            return ResponseEntity.createErrorJsonResponse("用户名不存在");
        }
        String salt = userModel.getSalt();
        String patter = SecurityUtil.AESDncode(salt,userModel.getPassword());
        if(patter==null||!patter.equals(login.getPassword())){
            return ResponseEntity.createErrorJsonResponse("密码错误");
        }
        //校验通过
        ClientUser clientUser = sercretClient.getClientUser(login.getAccessToken());
        if(clientUser==null){
            return ResponseEntity.createErrorJsonResponse("登录信息校验失败");
        }
        //获取用户的权限等信息
        List<RoleResModel> resList = roleResClient.findByRoleId(userModel.getRoleId(),"root");
        Map<String,Object> resMap = new HashMap<>();
        for (RoleResModel roleRes : resList){
            resMap.put(roleRes.getResUrl(),roleRes.getResName());
        }
        //设置缓存信息
        clientUser.setUserName(userModel.getRealName());
        clientUser.setUserType(userModel.getUserType());
        clientUser.setOrgId(userModel.getOrgId());
        clientUser.setAuthCode(userModel.getAuthCode());
        clientUser.setUserRes(resMap);
        sercretClient.updateClientUser(clientUser);
        return ResponseEntity.createNormalJsonResponse(JSONObject.parseObject(JSON.toJSONString(clientUser)));
    }
}
