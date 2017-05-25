package com.kerry.system.model;

import com.kerry.system.model.base.Org;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "p_org")
public class OrgModel extends Org implements Serializable {
}
