package com.kerry.system.model;

import com.kerry.system.model.base.Org;
import org.beetl.sql.core.annotatoin.Table;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "p_org")
public class OrgModel extends Org implements Serializable {

    @Transient
    private List<OrgModel> subNode;

    public List<OrgModel> getSubNode() {
        return subNode;
    }

    public void setSubNode(List<OrgModel> subNode) {
        this.subNode = subNode;
    }
}
