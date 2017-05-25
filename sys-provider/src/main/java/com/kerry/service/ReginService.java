package com.kerry.service;

import com.kerry.core.SearchParams;
import com.kerry.system.inter.IReginInter;
import com.kerry.system.model.RegionModel;
import com.kerry.system.inter.IReginInter;
import com.kerry.system.model.RegionModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区代码
 * Created by wangshen on 2017/4/12.
 */
@Service
public class ReginService implements IReginInter {

    @Autowired
    private SQLManager sqlManager;

    @Override
    public String insert(RegionModel regionModel) throws Exception {
        return null;
    }

    @Override
    public String update(RegionModel regionModel) throws Exception {
        return null;
    }

    @Override
    public String delete(String id) throws Exception {
        return null;
    }

    @Override
    public RegionModel selectById(String id) throws Exception {
        return null;
    }

    /**
     * 地区代码表查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<RegionModel> findByPage(SearchParams params) {
        PageQuery<RegionModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("regionModel.query",RegionModel.class,query);
        return query;
    }

    /**
     * 分级查询
     * @param level
     * @return
     */
    @Override
    public List<RegionModel> findByLevel(String level) {
        RegionModel params = new RegionModel();
        params.setParentId(level);
        return sqlManager.template(params);
    }
}
