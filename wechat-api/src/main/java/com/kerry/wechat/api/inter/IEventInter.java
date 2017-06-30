package com.kerry.wechat.api.inter;

import com.kerry.wechat.api.model.EventScan;

/**
 * 微信事件类消息接口
 * Created by wangshen on 2017/6/21.
 */
public interface IEventInter {

    /**
     * 用户关注操作
     * @param openId
     * @param accountId
     * @return
     */
    String focus(String openId, String accountId) throws Exception;

    /**
     *
     * @param eventScan
     * @return
     * @throws Exception
     */
    String scanQrCode(EventScan eventScan) throws Exception;
}
