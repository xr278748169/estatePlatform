package com.kerry.estate.base.dao;

import com.kerry.estate.base.model.ResFileModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 物业资源文件
 * Created by wangshen on 2017/7/27.
 */
@Repository
public interface ResFileDao extends BaseMapper<ResFileModel> {

    /**
     * 根据业务编号删除
     * @param bussId
     * @return
     */
    @SqlStatement(params = "bussId")
    int deleteByBussId(String bussId);
}
