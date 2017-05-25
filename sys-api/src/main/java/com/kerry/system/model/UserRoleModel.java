package com.kerry.system.model;

import com.kerry.system.model.base.UserRole;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "p_user_role")
public class UserRoleModel extends UserRole implements Serializable {
}
