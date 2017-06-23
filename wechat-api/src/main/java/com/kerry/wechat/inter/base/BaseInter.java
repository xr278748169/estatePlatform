package com.kerry.wechat.inter.base;

import com.kerry.core.SearchParams;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by wangshen on 2017/6/19.
 */
public interface BaseInter<T> {

    String insert(T t) throws Exception;

    String update(T t) throws Exception;

    String delete(String id) throws Exception;

    T selectById(String id) throws Exception;

    PageQuery<T> findByPage(SearchParams params) throws Exception;
}
