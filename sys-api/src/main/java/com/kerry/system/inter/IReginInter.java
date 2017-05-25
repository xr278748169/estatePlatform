package com.kerry.system.inter;

import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.RegionModel;

import java.util.List;

/**
 * 地区代码
 * Created by wangshen on 2017/4/12.
 */
public interface IReginInter extends BaseInter<RegionModel>{

    List<RegionModel> findByLevel(String level);
}
