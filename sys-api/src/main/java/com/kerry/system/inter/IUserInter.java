package com.kerry.system.inter;

import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.UserModel;

import java.util.List;

/**
 * Created by wangshen on 2017/4/12.
 */
public interface IUserInter extends BaseInter<UserModel> {

    /**
     * 根据对象参数查询
     * @param params
     * @return
     */
    List<UserModel> select(UserModel params) throws Exception;

    /**
     * 查询用户名对应的信息
     * @param loginName
     * @return
     * @throws Exception
     */
    UserModel selectByLoginName(String loginName) throws Exception;
}
