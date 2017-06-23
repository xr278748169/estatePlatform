package com.kerry.wechat.model;

import com.kerry.wechat.model.base.TMenu;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 微信菜单信息
 * Created by wangshen on 2017/6/19.
 */
@Table(name = "t_menu")
public class MenuModel extends TMenu implements Serializable {
}
