package com.kerry.service;

import com.kerry.core.SearchParams;
import com.kerry.system.inter.IGroupInter;
import com.kerry.system.model.GroupModel;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by wangshen on 2017/4/12.
 */
public class GroupService implements IGroupInter {
    @Override
    public String insert(GroupModel groupModel) {
        return null;
    }

    @Override
    public String update(GroupModel groupModel) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public GroupModel selectById(String id) {
        return null;
    }

    @Override
    public PageQuery<GroupModel> findByPage(SearchParams params) {
        return null;
    }
}
