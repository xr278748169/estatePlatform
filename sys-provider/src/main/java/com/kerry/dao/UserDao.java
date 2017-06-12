package com.kerry.dao;

import com.kerry.system.model.UserModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by wangshen on 2017/5/26.
 */
@Repository
public interface UserDao extends BaseMapper<UserModel>{

    @SqlStatement(params = "loginName")
    UserModel selectByLoginName(String loginName);

    @SqlStatement(params = "userId")
    UserModel selectByUserId(String userId);
}
