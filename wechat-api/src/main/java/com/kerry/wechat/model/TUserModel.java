package com.kerry.wechat.model;

import com.kerry.wechat.model.base.TUser;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 微信用户信息
 * Created by wangshen on 2017/6/19.
 */
@Table(name = "t_user")
public class TUserModel extends TUser implements Serializable {
}
