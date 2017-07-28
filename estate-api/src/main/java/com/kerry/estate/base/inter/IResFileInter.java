package com.kerry.estate.base.inter;

import com.kerry.estate.BaseInter;
import com.kerry.estate.base.model.ResFileModel;
import com.kerry.estate.base.model.base.ResFile;

import java.util.List;

/**
 * 物业资源文件
 * Created by wangshen on 2017/7/27.
 */
public interface IResFileInter extends BaseInter<ResFileModel> {

    void insertBatch(List<ResFileModel> resFileList) throws Exception;

    int deleteByBussId(String bussId) throws Exception;

}
