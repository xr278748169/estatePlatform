package com.kerry.dao;

import com.kerry.system.model.RoleResModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by wangshen on 2017/4/25.
 */
@Repository
public interface RoleResDao extends BaseMapper<RoleResModel> {

    @SqlStatement(params = "roleId")
    int deleteByRoleId(String roleId);
}
