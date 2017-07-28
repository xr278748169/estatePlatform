package com.kerry.estate.base.model;

import com.kerry.estate.base.model.base.ResFile;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 物业相关资源文件
 * Created by wangshen on 2017/7/27.
 */
@Table(name = "e_res_file")
public class ResFileModel extends ResFile implements Serializable {
}
