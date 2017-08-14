package com.kerry.estate.serv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IResFileInter;
import com.kerry.estate.base.model.ResFileModel;
import com.kerry.estate.dto.RepairsDto;
import com.kerry.estate.owner.inter.IOwnerInter;
import com.kerry.estate.owner.model.OwnerModel;
import com.kerry.estate.serv.dao.RepairsDao;
import com.kerry.estate.serv.inter.IRepairsInter;
import com.kerry.estate.serv.model.RepairsModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 报修管理
 * Created by wangshen on 2017/7/27.
 */
@Service
@Transactional("txManager")
public class RepairsService implements IRepairsInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private RepairsDao repairsDao;

    @Autowired
    private IResFileInter resFileInter;

    @Autowired
    private IOwnerInter ownerInter;

    /**
     * 保存
     * @param repairsModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(RepairsModel repairsModel) throws Exception {
        repairsModel.setCreateDate(new Date());
        int num = sqlManager.insert(repairsModel);
        if(num > 0){
            if(repairsModel.getResFileList()!=null && repairsModel.getResFileList().size()>0){
                resFileInter.insertBatch(repairsModel.getResFileList());
            }
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param repairsModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(RepairsModel repairsModel) throws Exception {
        repairsModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(repairsModel);
        if(num > 0){
            if(repairsModel.getResFileList()!=null && repairsModel.getResFileList().size()>0){
                resFileInter.insertBatch(repairsModel.getResFileList());
            }
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(RepairsModel.class, id);
        if(num > 0){
            resFileInter.deleteByBussId(id);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public RepairsModel selectById(String id) throws Exception {
        RepairsModel repairsModel = repairsDao.selectById(id);
        //查询资源文件
        ResFileModel params = new ResFileModel();
        params.setBussId(repairsModel.getReId());
        repairsModel.setResFileList(resFileInter.findByCondition(params));
        return repairsModel;
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<RepairsModel> findByPage(SearchParams params) throws Exception {
        PageQuery<RepairsModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("repairsModel.query",RepairsModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<RepairsModel> findByCondition(RepairsModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 微信端保存报修信息
     * @param repairsDto
     * @return
     * @throws Exception
     */
    @Override
    public String saveRepairs(RepairsDto repairsDto) throws Exception {
        String ownId = repairsDto.getOwnId();
        if(ownId==null || ownId.equals("")){
            return ResponseEntity.createErrorJsonResponse("获取个人信息失败");
        }
        OwnerModel ownerModel = ownerInter.selectById(ownId);
        if(ownerModel==null){
            return ResponseEntity.createErrorJsonResponse("获取个人信息失败");
        }
        RepairsModel repairs = new RepairsModel();
        repairs.setTitle(repairsDto.getTitle());
        repairs.setContent(repairsDto.getContent());
        repairs.setOwnId(ownId);
        repairs.setComId(ownerModel.getComId());
        repairs.setReDate(new Date());
        repairs.setAuthCode(ownerModel.getAuthCode());
        repairs.setState("dispose01");//待处理
        repairs.setCreateDate(new Date());
        repairs.setCreateUser(ownId);
        int num = sqlManager.insert(repairs);
        if(num > 0){
            //保存图片
            List<ResFileModel> resFileList = new ArrayList<>();
            for(Object obj : repairsDto.getImgList()){
                JSONObject imgObj = JSON.parseObject(JSON.toJSONString(obj));
                ResFileModel resFile = new ResFileModel();
                resFile.setResType("images");
                resFile.setResUrl(imgObj.getString("url"));
                resFile.setBussId(repairs.getReId());
                resFile.setAuthCode(repairs.getAuthCode());
                resFileList.add(resFile);
            }
            if(resFileList.size()>0){
                resFileInter.insertBatch(resFileList);
            }
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }
}
