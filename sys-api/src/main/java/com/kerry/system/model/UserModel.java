package com.kerry.system.model;

import com.kerry.system.model.base.User;
import org.beetl.sql.core.annotatoin.Table;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "p_user")
public class UserModel extends User implements Serializable {

    @Transient
    private String password2;//前台提交的确认密码

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * 自定义字段
     */
    private String orgName;

    private String groupName;

    private String roleId;

    private String roleName;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
