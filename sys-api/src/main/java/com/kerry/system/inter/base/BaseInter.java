package com.kerry.system.inter.base;

import com.kerry.core.SearchParams;
import org.beetl.sql.core.engine.PageQuery;

/**
 * 增删改查返回具体的错误信息，方便处理
 * Created by wangshen on 2017/4/12.
 */
public interface BaseInter<T> {

    String insert(T t) throws Exception;

    String update(T t) throws Exception;

    String delete(String id) throws Exception;

    T selectById(String id) throws Exception;

    PageQuery<T> findByPage(SearchParams params) throws Exception;

}
