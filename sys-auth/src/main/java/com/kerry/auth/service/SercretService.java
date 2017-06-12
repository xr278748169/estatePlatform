package com.kerry.auth.service;

import com.kerry.model.ClientUser;
import com.kerry.model.ValidateClient;

/**
 * Created by wangshen on 2017/5/24.
 */
public interface SercretService {

    /**
     * 生成前端UI连接校验信息
     * @param clientType
     * @param uiSign
     * @return
     */
    String generater(String clientType, String uiSign);

    /**
     * 用户退出或者刷新系统后，清空服务端用户信息
     * @param accessToken
     * @return
     */
    String clear(String accessToken);

    /**
     * 获取客户端用户信息
     * @param accessToken
     * @return
     */
    ClientUser getClientUser(String accessToken);

    /**
     * 更新客户端用户信息
     * @param clientUser
     * @return
     */
    String updateClientUser(ClientUser clientUser);

    /**
     * 客户端URL校验
     * @param validateClient
     * @return
     */
    String validateUrl(ValidateClient validateClient);
}
