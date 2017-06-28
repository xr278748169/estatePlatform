package com.kerry.wechat.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信系统级常量配置
 * Created by wangshen on 2017/6/22.
 */
public class WxConstant {

    /**
     * 微信公众号信息key
     */
    public static final String WECHAT_ACCOUNT_KEY = "WECHAT_ACCOUNT";

    /**
     * 微信公众号token key
     */
    public static final String WECHAT_ACCESS_TOKEN_KEY = "WECHAT_ACCESS_TOKEN";

    /**
     * 消息类型Key值,用于区分消息类型时所用的key
     */
    public static final String GET_MSG_TYPE_KEY = "MsgType";

    /**
     * 事件类型Key值，用于区分事件类型时所用的Key
     */
    public static final String GET_EVENT_TYPE_KEY = "Event";


    /**
     * 微信消息码信息
     */
    public static final Map<Integer, String> GLOBAL_ERROR_CODE = new HashMap<Integer, String>();

    static {
        GLOBAL_ERROR_CODE.put(-1, "系统繁忙");
        GLOBAL_ERROR_CODE.put(0, "请求成功");
        GLOBAL_ERROR_CODE.put(-1000,"系统错误，未知返回状态");
        GLOBAL_ERROR_CODE.put(40001, "获取access_token时AppSecret错误，或者access_token无效");
        GLOBAL_ERROR_CODE.put(40002, "不合法的凭证类型");
        GLOBAL_ERROR_CODE.put(40003, "不合法的OpenID");
        GLOBAL_ERROR_CODE.put(40004, "不合法的媒体文件类型");
        GLOBAL_ERROR_CODE.put(40005, "不合法的文件类型");
        GLOBAL_ERROR_CODE.put(40006, "不合法的文件大小");
        GLOBAL_ERROR_CODE.put(40007, "不合法的媒体文件id");
        GLOBAL_ERROR_CODE.put(40008, "不合法的消息类型");
        GLOBAL_ERROR_CODE.put(40009, "不合法的图片文件大小");
        GLOBAL_ERROR_CODE.put(40010, "不合法的语音文件大小");
        GLOBAL_ERROR_CODE.put(40011, "不合法的视频文件大小");
        GLOBAL_ERROR_CODE.put(40012, "不合法的缩略图文件大小");
        GLOBAL_ERROR_CODE.put(40013, "不合法的APPID");
        GLOBAL_ERROR_CODE.put(40014, "不合法的access_token");
        GLOBAL_ERROR_CODE.put(40015, "不合法的菜单类型");
        GLOBAL_ERROR_CODE.put(40016, "不合法的按钮个数");
        GLOBAL_ERROR_CODE.put(40017, "不合法的按钮个数");
        GLOBAL_ERROR_CODE.put(40018, "不合法的按钮名字长度");
        GLOBAL_ERROR_CODE.put(40019, "不合法的按钮KEY长度");
        GLOBAL_ERROR_CODE.put(40020, "不合法的按钮URL长度");
        GLOBAL_ERROR_CODE.put(40021, "不合法的菜单版本号");
        GLOBAL_ERROR_CODE.put(40022, "不合法的子菜单级数");
        GLOBAL_ERROR_CODE.put(40023, "不合法的子菜单按钮个数");
        GLOBAL_ERROR_CODE.put(40024, "不合法的子菜单按钮类型");
        GLOBAL_ERROR_CODE.put(40025, "不合法的子菜单按钮名字长度");
        GLOBAL_ERROR_CODE.put(40026, "不合法的子菜单按钮KEY长度");
        GLOBAL_ERROR_CODE.put(40027, "不合法的子菜单按钮URL长度");
        GLOBAL_ERROR_CODE.put(40028, "不合法的自定义菜单使用用户");
        GLOBAL_ERROR_CODE.put(40029, "不合法的oauth_code");
        GLOBAL_ERROR_CODE.put(40030, "不合法的refresh_token");
        GLOBAL_ERROR_CODE.put(40031, "不合法的openid列表");
        GLOBAL_ERROR_CODE.put(40032, "不合法的openid列表长度");
        GLOBAL_ERROR_CODE.put(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符");
        GLOBAL_ERROR_CODE.put(40035, "不合法的参数");
        GLOBAL_ERROR_CODE.put(40038, "不合法的请求格式");
        GLOBAL_ERROR_CODE.put(40039, "不合法的URL长度");
        GLOBAL_ERROR_CODE.put(40050, "不合法的分组id");
        GLOBAL_ERROR_CODE.put(40051, "分组名字不合法");
        GLOBAL_ERROR_CODE.put(41001, "缺少access_token参数");
        GLOBAL_ERROR_CODE.put(41002, "缺少appid参数");
        GLOBAL_ERROR_CODE.put(41003, "缺少refresh_token参数");
        GLOBAL_ERROR_CODE.put(41004, "缺少secret参数");
        GLOBAL_ERROR_CODE.put(41005, "缺少多媒体文件数据");
        GLOBAL_ERROR_CODE.put(41006, "缺少media_id参数");
        GLOBAL_ERROR_CODE.put(41007, "缺少子菜单数据");
        GLOBAL_ERROR_CODE.put(41008, "缺少oauth code");
        GLOBAL_ERROR_CODE.put(41009, "缺少openid");
        GLOBAL_ERROR_CODE.put(42001, "access_token超时");
        GLOBAL_ERROR_CODE.put(42002, "refresh_token超时");
        GLOBAL_ERROR_CODE.put(42003, "oauth_code超时");
        GLOBAL_ERROR_CODE.put(43001, "需要GET请求");
        GLOBAL_ERROR_CODE.put(43002, "需要POST请求");
        GLOBAL_ERROR_CODE.put(43003, "需要HTTPS请求");
        GLOBAL_ERROR_CODE.put(43004, "需要接收者关注");
        GLOBAL_ERROR_CODE.put(43005, "需要好友关系");
        GLOBAL_ERROR_CODE.put(43100, "change template too frequently(更改模板太频繁)");
        GLOBAL_ERROR_CODE.put(44001, "多媒体文件为空");
        GLOBAL_ERROR_CODE.put(44002, "POST的数据包为空");
        GLOBAL_ERROR_CODE.put(44003, "图文消息内容为空");
        GLOBAL_ERROR_CODE.put(44004, "文本消息内容为空");
        GLOBAL_ERROR_CODE.put(45001, "多媒体文件大小超过限制");
        GLOBAL_ERROR_CODE.put(45002, "消息内容超过限制");
        GLOBAL_ERROR_CODE.put(45003, "标题字段超过限制");
        GLOBAL_ERROR_CODE.put(45004, "描述字段超过限制");
        GLOBAL_ERROR_CODE.put(45005, "链接字段超过限制");
        GLOBAL_ERROR_CODE.put(45006, "图片链接字段超过限制");
        GLOBAL_ERROR_CODE.put(45007, "语音播放时间超过限制");
        GLOBAL_ERROR_CODE.put(45008, "图文消息超过限制");
        GLOBAL_ERROR_CODE.put(45009, "接口调用超过限制");
        GLOBAL_ERROR_CODE.put(45010, "创建菜单个数超过限制");
        GLOBAL_ERROR_CODE.put(45015, "回复时间超过限制");
        GLOBAL_ERROR_CODE.put(45016, "系统分组，不允许修改");
        GLOBAL_ERROR_CODE.put(45017, "分组名字过长");
        GLOBAL_ERROR_CODE.put(45018, "分组数量超过上限");
        GLOBAL_ERROR_CODE.put(46001, "不存在媒体数据");
        GLOBAL_ERROR_CODE.put(46002, "不存在的菜单版本");
        GLOBAL_ERROR_CODE.put(46003, "不存在的菜单数据");
        GLOBAL_ERROR_CODE.put(46004, "不存在的用户");
        GLOBAL_ERROR_CODE.put(47001, "解析JSON/XML内容错误");
        GLOBAL_ERROR_CODE.put(48001, "api功能未授权");
        GLOBAL_ERROR_CODE.put(50001, "用户未授权该api");
    }
}
