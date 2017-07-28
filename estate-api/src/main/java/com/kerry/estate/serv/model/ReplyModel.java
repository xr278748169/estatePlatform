package com.kerry.estate.serv.model;

import com.kerry.estate.serv.model.base.Reply;
import com.kerry.utils.DateUtils;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 回复信息
 * Created by wangshen on 2017/7/27.
 */
@Table(name = "e_reply")
public class ReplyModel extends Reply implements Serializable {

    private String formatReDate;

    public String getFormatReDate() {
        if(getReDate() != null){
            return DateUtils.getDate(getReDate(),"yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }
}
