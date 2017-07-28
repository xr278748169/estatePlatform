package com.kerry.estate.serv.dao;

import com.kerry.estate.serv.model.RepairsModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 报修管理
 * Created by wangshen on 2017/7/28.
 */
@Repository
public interface RepairsDao extends BaseMapper<RepairsModel>{

    @SqlStatement(params = "reId")
    RepairsModel selectById(String reId);
}
