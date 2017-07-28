package com.kerry.estate.serv.dao;

import com.kerry.estate.serv.model.SuitModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 投诉建议
 * Created by wangshen on 2017/7/28.
 */
@Repository
public interface SuitDao extends BaseMapper<SuitModel>{

    @SqlStatement(params = "stId")
    SuitModel selectById(String stId);
}
