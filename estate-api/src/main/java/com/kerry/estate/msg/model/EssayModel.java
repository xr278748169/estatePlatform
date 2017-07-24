package com.kerry.estate.msg.model;

import com.kerry.estate.msg.model.base.Essay;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 物业消息管理
 * Created by wangshen on 2017/7/24.
 */
@Table(name = "r_essay")
public class EssayModel extends Essay implements Serializable {
}
