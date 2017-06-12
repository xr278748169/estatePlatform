package com.kerry.system.inter;

import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.OrgModel;

import java.util.List;

/**
 * Created by wangshen on 2017/4/12.
 */
public interface IOrgInter extends BaseInter<OrgModel> {

    /**
     * 查询树列表
     * @return
     */
    List<OrgModel> findTreeList() throws Exception;

    /**
     * 查询子节点
     * @return
     * @throws Exception
     */
    OrgModel findSubTree(OrgModel parent) throws Exception;
}
