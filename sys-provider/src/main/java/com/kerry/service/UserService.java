package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.dao.UserDao;
import com.kerry.system.inter.IUserInter;
import com.kerry.system.model.UserModel;
import com.kerry.system.model.UserRoleModel;
import com.kerry.utils.MD5Util;
import com.kerry.utils.RandomStr;
import com.kerry.utils.SecurityUtil;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional("txManager")
public class UserService implements IUserInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 用户新增
     * @param userModel
     * @return
     */
    @Override
    public String insert(UserModel userModel) throws Exception {
        String slat = RandomStr.getRandomStr(16);//16位密码校验码
        String password = SecurityUtil.AESEncode(slat,Constant.DEFAULT_PASSWORD);//密码加密
        userModel.setSalt(slat);
        userModel.setPassword(password);
        //封装用户信息
        int num = sqlManager.insert(userModel);
        if(num > 0){
            String userId = userModel.getUserId();
            UserRoleModel userRoleModel = new UserRoleModel();
            userRoleModel.setUserId(userId);
            userRoleModel.setRoleId(userModel.getRoleId());
            userRoleService.insert(userRoleModel);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 用户信息更新
     * @param userModel
     * @return
     */
    @Override
    public String update(UserModel userModel) throws Exception {
        int num = sqlManager.updateTemplateById(userModel);
        if(num > 0){
            if(!userModel.getRoleId().isEmpty()){
                String userId = userModel.getUserId();
                UserRoleModel params = new UserRoleModel();
                params.setUserId(userId);
                List<UserRoleModel> userRoleList = userRoleService.select(params);
                for(UserRoleModel userRole : userRoleList){
                    userRoleService.delete(userRole.getRuId());
                }
                UserRoleModel userRoleModel = new UserRoleModel();
                userRoleModel.setUserId(userId);
                userRoleModel.setRoleId(userModel.getRoleId());
                userRoleService.insert(userRoleModel);
            }
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(UserModel.class,id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 根据编号查询
     * @param id
     * @return
     */
    @Override
    public UserModel selectById(String id) throws Exception {
        return userDao.selectByUserId(id);
    }

    /**
     * 用户信息分页查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<UserModel> findByPage(SearchParams params) throws Exception {
        PageQuery<UserModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("userModel.query",UserModel.class,query);
        return query;
    }

    /**
     * 根据用户参数查询
     * @param params
     * @return
     */
    @Override
    public List<UserModel> select(UserModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 查询用户名对应的信息
     * @param loginName
     * @return
     * @throws Exception
     */
    @Override
    public UserModel selectByLoginName(String loginName) throws Exception {
        return userDao.selectByLoginName(loginName);
    }
}
