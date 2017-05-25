package com.kerry.system.inter;

import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.ResModel;

import java.util.List;

/**
 * 系统资源
 * Created by wangshen on 2017/4/12.
 */
public interface IResInter extends BaseInter<ResModel>{

    List<ResModel> findByParentId(String parentId) throws Exception;

}
