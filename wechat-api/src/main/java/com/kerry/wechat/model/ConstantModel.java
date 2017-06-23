package com.kerry.wechat.model;

import com.kerry.wechat.model.base.TWxConstant;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/6/21.
 */
@Table(name = "t_wx_constant")
public class ConstantModel extends TWxConstant implements Serializable {
}
