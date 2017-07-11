package com.kerry.estate;

import com.kerry.core.SearchParams;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * Created by wangshen on 2017/7/3.
 */
public interface BaseInter<T> {

    String insert(T t) throws Exception;

    String update(T t) throws Exception;

    String delete(String id) throws Exception;

    T selectById(String id) throws Exception;

    PageQuery<T> findByPage(SearchParams params) throws Exception;

    List<T> findByCondition(T params) throws Exception;
}
