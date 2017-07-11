package com.kerry.estate.base.inter;

import com.kerry.estate.BaseInter;
import com.kerry.estate.base.model.PropertyCoModel;

import java.util.List;

/**
 * 物业公司管理
 * Created by wangshen on 2017/7/3.
 */
public interface IPropertyCoInter extends BaseInter<PropertyCoModel> {

    List<PropertyCoModel> findAll() throws Exception;
}
