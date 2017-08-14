package com.kerry.estate.owner.inter;

import com.kerry.dto.WechatCache;
import com.kerry.estate.BaseInter;
import com.kerry.estate.dto.AuthDto;
import com.kerry.estate.owner.model.OwnerModel;

import java.util.List;

/**
 * 业主信息管理
 * Created by wangshen on 2017/7/20.
 */
public interface IOwnerInter extends BaseInter<OwnerModel> {

    /**
     * 业主认证处理
     * @param authDto
     * @return
     * @throws Exception
     */
    String ownAuth(AuthDto authDto) throws Exception;

    /**
     * 获取用户认证信息
     * @return
     * @throws Exception
     */
    WechatCache getOwnAuth(String token) throws Exception;

    /**
     * 获取业主的家庭成员
     * @return
     */
    List<OwnerModel> findFamily(String ownId) throws Exception;
}
