package com.kerry.wechat.api.controller;

import com.kerry.utils.SignUtil;
import com.kerry.wechat.api.MessageType;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.client.EventClient;
import com.kerry.wechat.api.inter.IEventInter;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.redis.RedisUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 微信核心控制器，消息处理与绑定
 * Created by wangshen on 2017/6/21.
 */
@RestController
@RequestMapping("/api/wechat/")
public class WechatController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private RedisUtil redisUtil;

    private InputStream inputStream;

    private String resultMsg;//返回微信的消息

    @Autowired
    private EventClient eventClient;

    /**
     * 微信公众号绑定认证
     * @param request
     * @param response
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/{accountId}/message", method = RequestMethod.GET)
    @ResponseBody
    public String authentication(HttpServletRequest request, HttpServletResponse response, @PathVariable String accountId){
        //根据公众号ID获取公众号信息
        AccountModel accountModel = (AccountModel) redisUtil.getHash(WxConstant.WECHAT_ACCOUNT_KEY,accountId);
        if(accountModel==null){
            logger.info(" >>> 不合法的配置信息 ");
            return "";
        }
        String token = accountModel.getToken();
        String signature = request.getParameter("signature");if(signature==null)signature="";// 微信加密签名
        String timestamp = request.getParameter("timestamp");if(timestamp==null)timestamp="";// 时间戳
        String nonce = request.getParameter("nonce");if(nonce==null)nonce="";// 随机数
        String echostr = request.getParameter("echostr");if(echostr==null)echostr="";// 随机字符串
        if (SignUtil.validSign(signature, token, timestamp, nonce)) {
            logger.info(" >>> 公众号： "+accountModel.getAccountName()+" --> 配置接口成功");
            return echostr;
        }else{
            logger.error(" >>> 当前请求不是来自微信 ");
        }
        return "";
    }

    /**
     * 处理消息
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/{accountId}/message", method = RequestMethod.POST)
    @ResponseBody
    public String mssage(HttpServletRequest request,@PathVariable String accountId, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            AccountModel accountModel = (AccountModel) redisUtil.getHash(WxConstant.WECHAT_ACCOUNT_KEY,accountId);
            // 初始化回调信息
            resultMsg = "";
            inputStream = request.getInputStream();
            this.getReceiverType(accountModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMsg;
    }

    /**
     * 获取消息类型
     *
     * @return
     */
    private void getReceiverType(AccountModel accountModel) {
        MessageType msgType = null;
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);// 流关闭时，request也关闭
            Element rootElement = document.getRootElement();
            msgType = MessageType.getObjectByName(rootElement.element(WxConstant.GET_MSG_TYPE_KEY).getStringValue());
            if (MessageType.EVENT.equals(msgType)) {
                String eventType = rootElement.element(WxConstant.GET_EVENT_TYPE_KEY).getStringValue();
                msgType = MessageType.getObjectByName(eventType);
            }
           messageTypeControler(msgType, rootElement, accountModel);
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 微信消息分发控制器
     * @param msgType
     * @param element
     * @param accountModel
     */
    private void messageTypeControler(MessageType msgType, Element element, AccountModel accountModel) {
        if(msgType==null){
            element.clearContent();
            element = null;
            return;
        }
        switch (msgType) {
            case IMAGE:// 用户发来图片消息
                // 可对用户发送的图片信息进行下载

                break;
            case LINK:// 用户发来超链接消息
                // 可对用户发送的链接信息进行处理

                break;
            case LOCATION:// 用户发来地理位置消息

                break;
            case TEXT:// 用户发来文本消息
                // 跟根据用户输入的文本信息进行解析并回复

                break;
            case VIDEO:// 用户发来视频消息

                break;
            case VOICE:// 用户发来语音消息

                break;
            case CLICK:// 用户单击了菜单(非一级菜单)消息

                break;
            case ELOCATION:// 用户通过事件发来地理位置消息

                break;
            case SCAN:// 用户扫描二维码

                break;
            case SUBSCRIBE:// 用户关注了平台
                resultMsg = eventClient.focus(element,accountModel);
                break;
            case UNSUBSCRIBE:// 用户取消平台的关注

                break;
            case VIEW:// 用户单击超链接按钮

                break;
            case TEMPLATESENDJOBFINISH:// 接收发送模版消息后的结果

                break;
            default:

                break;
        }
        element.clearContent();
        element = null;
    }

}
