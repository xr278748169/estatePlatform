package com.kerry.system.inter;

import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.UserRoleModel;

import java.util.List;

/**
 * Created by wangshen on 2017/4/12.
 */
public interface IUserRoleInter extends BaseInter<UserRoleModel> {

    /**
     * 参数查询
     * @param params
     * @return
     */
    List<UserRoleModel> select(UserRoleModel params) throws Exception;
}
