package com.kerry.wechat.model;

import com.kerry.wechat.model.base.TAccount;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 微信账号信息
 * Created by wangshen on 2017/6/19.
 */
@Table(name = "t_account")
public class AccountModel extends TAccount implements Serializable {

}
