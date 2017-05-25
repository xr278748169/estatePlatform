package com.kerry.system.inter;

import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.DictionaryModel;

/**
 * Created by wangshen on 2017/4/12.
 */
public interface IDictInter extends BaseInter<DictionaryModel> {

    String findDictJson() throws Exception;
}
