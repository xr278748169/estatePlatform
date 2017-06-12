package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IOrgInter;
import com.kerry.system.model.OrgModel;
import com.kerry.system.inter.IOrgInter;
import com.kerry.system.model.OrgModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * 组织机构
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional
public class OrgService implements IOrgInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 组织机构新增
     * @param orgModel
     * @return
     */
    @Override
    public String insert(OrgModel orgModel) throws Exception {
        //查询名称是否重复
        OrgModel params = new OrgModel();
        params.setOrgName(orgModel.getOrgName());
        List<OrgModel> orgList = sqlManager.template(params);
        if(orgList.size()>0){
            return ResponseEntity.createDuplicationJsonResponse(Constant.DTAT_RESULT_DUP);
        }
        int num = sqlManager.insert(orgModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 组织机构更新
     * @param orgModel
     * @return
     */
    @Override
    public String update(OrgModel orgModel) throws Exception {
        int num = sqlManager.updateTemplateById(orgModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 组织机构删除
     * @param id
     * @return
     */
    @Override
    public String delete(String id) throws Exception {
        //查询是否存在下级
        OrgModel params = new OrgModel();
        params.setParentOrgId(id);
        List<OrgModel> orgList = sqlManager.template(params);
        if(orgList.size()>0){
            return ResponseEntity.createErrorJsonResponse(Constant.DTAT_RESULT_SUB);
        }
        int num = sqlManager.deleteById(OrgModel.class, id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 根据主键查询机构
     * @param id
     * @return
     */
    @Override
    public OrgModel selectById(String id) throws Exception {
        return sqlManager.unique(OrgModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<OrgModel> findByPage(SearchParams params) throws Exception {
        PageQuery<OrgModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("orgModel.query",OrgModel.class,query);
        return query;
    }

    /**
     * 查询组织机构树列表
     * @return
     */
    @Override
    public List<OrgModel> findTreeList() throws Exception {
        //查询根节点
        OrgModel params = new OrgModel();
        params.setParentOrgId("0");//根节点0
        List<OrgModel> rootList = sqlManager.template(params);
        for (Iterator<OrgModel> iterator = rootList.iterator();iterator.hasNext();){
            findSubTree(iterator.next());
        }
        return rootList;
    }

    /**
     * 查询子节点
     * @param parent
     * @return
     * @throws Exception
     */
    @Override
    public OrgModel findSubTree(OrgModel parent) throws Exception {
        //查询节点信息
        OrgModel params = new OrgModel();
        params.setParentOrgId(parent.getOrgId());
        List<OrgModel> nodeList = sqlManager.template(params);
        if(nodeList==null||nodeList.size()==0){
            return parent;
        }
        parent.setSubNode(nodeList);
        for (Iterator<OrgModel> iterator = nodeList.iterator();iterator.hasNext();){
            findSubTree(iterator.next());
        }
        return parent;
    }
}
