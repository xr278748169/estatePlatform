package com.kerry.service;

import com.kerry.core.SearchParams;
import com.kerry.system.inter.IOrgInter;
import com.kerry.system.model.OrgModel;
import com.kerry.system.inter.IOrgInter;
import com.kerry.system.model.OrgModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 组织机构
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional
public class OrgService implements IOrgInter {

    /**
     * 组织机构新增
     * @param orgModel
     * @return
     */
    @Override
    public String insert(OrgModel orgModel) throws Exception {
        return null;
    }

    /**
     * 组织机构更新
     * @param orgModel
     * @return
     */
    @Override
    public String update(OrgModel orgModel) throws Exception {
        return null;
    }

    /**
     * 组织机构删除
     * @param id
     * @return
     */
    @Override
    public String delete(String id) throws Exception {
        return null;
    }

    /**
     * 根据主键查询机构
     * @param id
     * @return
     */
    @Override
    public OrgModel selectById(String id) throws Exception {
        return null;
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<OrgModel> findByPage(SearchParams params) throws Exception {
        return null;
    }
}
