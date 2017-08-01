package com.kerry.estate.owner.inter;

import com.kerry.estate.BaseInter;
import com.kerry.estate.dto.AuthDto;
import com.kerry.estate.owner.model.OwnerModel;

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
}
