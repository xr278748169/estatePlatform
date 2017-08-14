package com.kerry.estate.owner.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.dto.WechatCache;
import com.kerry.estate.base.inter.IBuildingRoomInter;
import com.kerry.estate.base.inter.ICommunityInter;
import com.kerry.estate.base.model.BuildingRoomModel;
import com.kerry.estate.base.model.CommunityModel;
import com.kerry.estate.client.CaptchaClient;
import com.kerry.estate.client.TUserClient;
import com.kerry.estate.client.WechatUserCacheClient;
import com.kerry.estate.dto.AuthDto;
import com.kerry.estate.owner.dao.OwnerDao;
import com.kerry.estate.owner.inter.IOwnerInter;
import com.kerry.estate.owner.model.OwnerModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业主信息管理
 * Created by wangshen on 2017/7/20.
 */
@Service
@Transactional("txManager")
public class OwnerService implements IOwnerInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private OwnerDao ownerDao;

    @Autowired
    private CaptchaClient captchaClient;

    @Autowired
    private ICommunityInter communityInter;

    @Autowired
    private IBuildingRoomInter buildingRoomInter;

    @Autowired
    private TUserClient tUserClient;

    @Autowired
    private WechatUserCacheClient wechatUserCacheClient;

    /**
     * 保存
     * @param ownerModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(OwnerModel ownerModel) throws Exception {
        ownerModel.setCreateDate(new Date());
        int num = sqlManager.insert(ownerModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param ownerModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(OwnerModel ownerModel) throws Exception {
        ownerModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(ownerModel);
        if(num > 0){
            if(ownerModel.getAuthState().equals("1")){//认证通过
                OwnerModel owner = this.selectById(ownerModel.getOwnId());
                tUserClient.updateBussState(owner.getTuId(),"1");
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
        int num = sqlManager.deleteById(OwnerModel.class ,id);
        if(num > 0){
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
    public OwnerModel selectById(String id) throws Exception {
        return ownerDao.selectById(id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<OwnerModel> findByPage(SearchParams params) throws Exception {
        PageQuery<OwnerModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("ownerModel.query",OwnerModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<OwnerModel> findByCondition(OwnerModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 业主认证
     * @param authDto
     * @return
     * @throws Exception
     */
    @Override
    public String ownAuth(AuthDto authDto) throws Exception {
        if(authDto.getToken()==null || authDto.getToken().equals("")){
            return ResponseEntity.createErrorJsonResponse("未获取到微信信息");
        }
        //先获取微信cache信息
        WechatCache wechatCache = wechatUserCacheClient.getUserCache(authDto.getToken());
        if(wechatCache==null){
            return ResponseEntity.createErrorJsonResponse("未获取到微信信息");
        }
        /**
         * 开始处理业务
         */
        String telephone = authDto.getTelephone();
        String authCode = authDto.getAuthCode();
        JSONObject smsCodeResult = JSON.parseObject(captchaClient.verifySmsCode(telephone, authCode));
        if(!smsCodeResult.getString("code").equals("0")){
            return smsCodeResult.toJSONString();
        }
        String comId="",budId="",burId="";
        //检查业主信息中是否存在认证信息
        OwnerModel ownParams = new OwnerModel();
        ownParams.setIdNumber(authDto.getIdNumber());
        ownParams.setTelephone(authDto.getTelephone());
        if(this.findByCondition(ownParams).size()>0){
            return ResponseEntity.createErrorJsonResponse("您的信息已进行过认证");
        }
        if(authDto.getOwnType().equals("owner")){//户主认证时检查房号状态
            if(authDto.getComId().equals("") || authDto.getBudId().equals("") || authDto.getBurId().equals("")){
                return ResponseEntity.createErrorJsonResponse("户主请选择物业信息");
            }
            //检查房号是否被认证
            BuildingRoomModel params = new BuildingRoomModel();
            params.setBudId(authDto.getBudId());
            params.setBurId(authDto.getBurId());
            params.setState("1");//表示只检查锁定的
            if(buildingRoomInter.findByCondition(params).size()>0){
                return ResponseEntity.createErrorJsonResponse("您选择的房号已被认证");
            }
            comId = authDto.getComId();
            budId = authDto.getBudId();
            burId = authDto.getBurId();
        }else{//非户主规则
            if(authDto.getVisitCode().equals("")){
                return ResponseEntity.createErrorJsonResponse("非户主请输入邀请码");
            }
            //根据邀请码获取户主物业信息
            ownParams = new OwnerModel();
            ownParams.setVisitCode(authDto.getVisitCode());
            List<OwnerModel> ownerList = this.findByCondition(ownParams);
            if(ownerList.size()==0){
                return ResponseEntity.createErrorJsonResponse("邀请码错误");
            }
            //设置物业信息
            OwnerModel owner = ownerList.get(0);
            comId = owner.getComId();
            budId = owner.getBudId();
            burId = owner.getBurId();
        }
        if(comId.equals("") || budId.equals("") || burId.equals("")){
            return ResponseEntity.createErrorJsonResponse("未获取到您的物业信息");
        }
        //获取小区信息
        CommunityModel community = communityInter.selectById(comId);
        //组织写入业主信息
        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setOwnType(authDto.getOwnType());
        ownerModel.setOwnName(authDto.getOwnName());
        ownerModel.setIdNumber(authDto.getIdNumber());
        ownerModel.setTelephone(authDto.getTelephone());
        ownerModel.setComId(comId);
        ownerModel.setBudId(budId);
        ownerModel.setBurId(burId);
        ownerModel.setTuId(authDto.getTuId());//用户微信编号
        ownerModel.setOwnState("1");//正常
        ownerModel.setAuthState("0");//申请认证
        ownerModel.setAuthCode(community.getAuthCode());//数据权限码对应到小区
        ownerModel.setCreateDate(new Date());
        ownerModel.setCreateUser(authDto.getTuId());
        int num = sqlManager.insert(ownerModel);
        if(num > 0){
            //业主类型为户主的锁定房号
            if(authDto.getOwnType().equals("owner")){
                BuildingRoomModel params = new BuildingRoomModel();
                params.setBurId(authDto.getBurId());
                params.setState("1");//锁定房号
                buildingRoomInter.update(params);
            }
            //修改微信用户表中的业务认证状态，同业务状态一致
            tUserClient.updateBussState(authDto.getTuId(),"0");
            //更新cache状态
            wechatCache.setBussId(ownerModel.getOwnId());
            wechatCache.setAuthState("0");//业务状态
            String msg = wechatUserCacheClient.updateUserCache(wechatCache);
            if(msg!=null && msg.equals("OK")){
                return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
            }else{
                return ResponseEntity.createErrorJsonResponse("未获取到微信信息");
            }
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 获取业主认证信息
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public WechatCache getOwnAuth(String token) throws Exception {
        if(token==null || token.equals("")){
            return null;
        }
        WechatCache wechatCache = wechatUserCacheClient.getUserCache(token);
        if(wechatCache==null){
            return null;
        }
        //根据tuId获取业主信息
        String tuId = wechatCache.getTuId();
        OwnerModel params = new OwnerModel();
        params.setTuId(tuId);
        List<OwnerModel> ownerList = this.findByCondition(params);
        if(ownerList.size() > 0){
            wechatCache.setBussId(ownerList.get(0).getOwnId());
        }
        return wechatCache;
    }

    /**
     * 获取业主的家庭成员
     * @param ownId
     * @return
     * @throws Exception
     */
    @Override
    public List<OwnerModel> findFamily(String ownId) throws Exception {
        if(ownId==null || ownId.equals("")){
            return null;
        }
        OwnerModel ownerModel = this.selectById(ownId);
        String burId = ownerModel.getBurId();//房号
        //根据房号查询房号下的业主信息
        Map<String,Object> params = new HashMap<>();
        params.put("burId",burId);
        return ownerDao.query(params);
    }
}
