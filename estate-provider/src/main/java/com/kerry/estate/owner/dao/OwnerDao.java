package com.kerry.estate.owner.dao;

import com.kerry.estate.owner.model.OwnerModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 业主信息管理
 * Created by wangshen on 2017/8/9.
 */
@Repository
public interface OwnerDao extends BaseMapper<OwnerModel> {

    @SqlStatement(params = "ownId")
    OwnerModel selectById(String ownId);

    /**
     * 参数查询
     * @param params
     * @return
     */
    List<OwnerModel> query(Map<String,Object> params);
}
