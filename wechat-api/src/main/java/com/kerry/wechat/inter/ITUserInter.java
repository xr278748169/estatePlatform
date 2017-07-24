package com.kerry.wechat.inter;

import com.kerry.wechat.inter.base.BaseInter;
import com.kerry.wechat.model.TUserModel;

import java.util.List;

/**
 * 微信用户管理
 * Created by wangshen on 2017/6/19.
 */
public interface ITUserInter extends BaseInter<TUserModel> {

    List<TUserModel> findByCondition(TUserModel params);

    
}
