package com.kerry.service;

import com.kerry.core.SearchParams;
import com.kerry.system.inter.IGroupResInter;
import com.kerry.system.inter.IGroupResInter;
import com.kerry.system.model.GroupResModel;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by wangshen on 2017/4/12.
 */
public class GroupResService implements IGroupResInter {

    @Override
    public String insert(GroupResModel groupResModel) {
        return null;
    }

    @Override
    public String update(GroupResModel groupResModel) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public GroupResModel selectById(String id) {
        return null;
    }

    @Override
    public PageQuery<GroupResModel> findByPage(SearchParams params) {
        return null;
    }
}
