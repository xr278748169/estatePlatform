package com.kerry.system.model;

import com.kerry.system.model.base.Role;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "p_role")
public class RoleModel extends Role implements Serializable {
}
