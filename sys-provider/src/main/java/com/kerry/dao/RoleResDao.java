package com.kerry.dao;

import com.kerry.system.model.RoleResModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshen on 2017/4/25.
 */
@Repository
public interface RoleResDao extends BaseMapper<RoleResModel> {

    @SqlStatement(params = "roleId")
    int deleteByRoleId(String roleId);

    @SqlStatement(params = "roleId,isRoot")
    List<RoleResModel> findByRoleId(String roleId,String isRoot);
}
