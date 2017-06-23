package com.kerry.wechat.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.inter.IAccountInter;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.redis.RedisUtil;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangshen on 2017/6/19.
 */
@Service
@Transactional("txManager")
public class AccountService implements IAccountInter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SQLManager sqlManager;

    /**
     * 微信公众号新增
     * @param accountModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(AccountModel accountModel) throws Exception {
        accountModel.setCreateDate(new Date());
        int num = sqlManager.insert(accountModel);
        if(num > 0){
            //保存完成后写入redis
            redisUtil.setHash(WxConstant.WECHAT_ACCOUNT_KEY,accountModel.getAccountId(),accountModel);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 微信公众号信息修改
     * @param accountModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(AccountModel accountModel) throws Exception {
        accountModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(accountModel);
        if(num > 0){
            //更新完成后写入redis
            redisUtil.setHash(WxConstant.WECHAT_ACCOUNT_KEY,accountModel.getAccountId(),accountModel);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 微信公众号删除
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(AccountModel.class, id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public AccountModel selectById(String id) throws Exception {
        return sqlManager.unique(AccountModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<AccountModel> findByPage(SearchParams params) throws Exception {
        PageQuery<AccountModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("accountModel.query",AccountModel.class,query);
        return query;
    }
}
