package com.kerry.estate.msg.model;

import com.kerry.estate.msg.model.base.Essay;
import com.kerry.utils.DateUtils;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 物业消息管理
 * Created by wangshen on 2017/7/24.
 */
@Table(name = "r_essay")
public class EssayModel extends Essay implements Serializable {

    private String formatReleaseDate;

    public String getFormatReleaseDate() {
        if(getReleaseDate() != null){
            return DateUtils.getDate(getReleaseDate(),"yyyy-MM-dd");
        }
        return null;
    }

}
